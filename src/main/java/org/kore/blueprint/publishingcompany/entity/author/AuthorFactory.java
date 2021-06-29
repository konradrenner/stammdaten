/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.entity.author;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

/**
 *
 * @author Konrad Renner
 */
public class AuthorFactory {

    private final Validator validator;

    public AuthorFactory(Validator validator) {
        this.validator = validator;
    }

    public WithFirstnameBuilder createAuthor(UUID authorId) {
        return new Builder(authorId);
    }

    public WithFirstnameBuilder createAuthor(String authorId) {
        return new Builder(UUID.fromString(authorId));
    }

    public interface WithFirstnameBuilder {

        WithLastnameBuilder firstname(String value);
    }

    public interface WithLastnameBuilder {

        WithPublicationBuilder lastname(String value);
    }

    public interface WithPublicationBuilder {

        WithPublicationBuilder addBook(Book book);

        WithPublicationBuilder addBlogPost(BlogPost post);

        Author build();
    }

    final class Builder implements WithFirstnameBuilder, WithLastnameBuilder, WithPublicationBuilder {

        private final UUID id;
        private String firstname;
        private String lastname;
        private final Map<ISBN, Book> publishedBooks;
        private final Map<PublicationID, BlogPost> publishedBlogPosts;

        public Builder(UUID id) {
            this.id = id;
            this.publishedBooks = new HashMap<>();
            this.publishedBlogPosts = new HashMap<>();
        }

        @Override
        public WithLastnameBuilder firstname(String value) {
            this.firstname = value;
            return this;
        }

        @Override
        public WithPublicationBuilder lastname(String value) {
            this.lastname = value;
            return this;
        }

        @Override
        public WithPublicationBuilder addBook(Book book) {
            this.publishedBooks.put(book.getId(), book);
            return this;
        }

        @Override
        public WithPublicationBuilder addBlogPost(BlogPost post) {
            this.publishedBlogPosts.put(post.getId(), post);
            return this;
        }

        @Override
        public Author build() {
            Author author = new Author(id, new Name(firstname, lastname), publishedBooks, publishedBlogPosts);
            Set<ConstraintViolation<Author>> result = validator.validate(author);
            if (result.isEmpty()) {
                return author;
            }
            throw new ConstraintViolationException(result);
        }

    }
}
