/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.entity.author;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Konrad Renner
 */
public class Author {

    @NotNull
    private final UUID id;
    @NotNull
    @Valid
    private final Name name;
    private final Map<@Valid ISBN, @Valid Book> publishedBooks;
    private final Map<@Valid PublicationID, @Valid BlogPost> publishedBlogPosts;

    Author(UUID id, Name name, Map<ISBN, Book> publishedBooks, Map<PublicationID, BlogPost> publishedBlogPosts) {
        this.id = id;
        this.name = name;
        this.publishedBooks = publishedBooks;
        this.publishedBlogPosts = publishedBlogPosts;
    }

    public UUID getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Optional<Book> getBook(@NotNull @Valid ISBN isbn) {
        return Optional.ofNullable(publishedBooks.get(isbn));
    }

    public Optional<BlogPost> getBlogPost(@NotNull @Valid PublicationID id) {
        return Optional.ofNullable(publishedBlogPosts.get(id));
    }

    public Set<Book> getAllBooks() {
        LinkedHashSet<Book> ret = new LinkedHashSet<>(publishedBooks.size());

        publishedBooks.values().stream().forEach(ret::add);

        return ret;
    }

    public Set<BlogPost> getAllBlogPost() {
        LinkedHashSet<BlogPost> ret = new LinkedHashSet<>(publishedBlogPosts.size());

        publishedBlogPosts.values().stream().forEach(ret::add);

        return ret;
    }

    public Set<? extends Publication> getAllPublications() {
        LinkedHashSet<Publication> ret = new LinkedHashSet<>();

        ret.addAll(publishedBooks.values());
        ret.addAll(publishedBlogPosts.values());

        return ret;
    }

    public Optional<Book> addPublication(@NotNull @Valid Book book) {
        if (publishedBooks.containsKey(book.getId())) {
            return Optional.empty();
        }
        publishedBooks.put(book.getId(), book);
        return Optional.of(book);
    }

    public Optional<BlogPost> addPublication(@NotNull @Valid BlogPost post) {
        if (publishedBlogPosts.containsKey(post.getId())) {
            return Optional.empty();
        }
        publishedBlogPosts.put(post.getId(), post);
        return Optional.of(post);
    }

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", name=" + name + ", publishedBooks=" + publishedBooks + ", publishedBlogPosts=" + publishedBlogPosts + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
