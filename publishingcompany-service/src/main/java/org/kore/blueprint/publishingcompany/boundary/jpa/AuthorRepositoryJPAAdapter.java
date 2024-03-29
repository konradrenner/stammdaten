/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.boundary.jpa;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.kore.blueprint.publishingcompany.control.AuthorRepository;
import org.kore.blueprint.publishingcompany.entity.author.Author;
import org.kore.blueprint.publishingcompany.entity.author.AuthorFactory;
import org.kore.blueprint.publishingcompany.entity.author.BlogPost;
import org.kore.blueprint.publishingcompany.entity.author.Book;
import org.kore.blueprint.publishingcompany.entity.author.Currency;
import org.kore.blueprint.publishingcompany.entity.author.ISBN;
import org.kore.blueprint.publishingcompany.entity.author.Price;
import org.kore.blueprint.publishingcompany.entity.author.PublicationID;
import org.kore.blueprint.publishingcompany.entity.author.events.AuthorAlreadyCreated;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

/**
 *
 * @author Konrad Renner
 */
@Dependent
public class AuthorRepositoryJPAAdapter implements AuthorRepository {

    private static final Logger LOG = Logger.getLogger(AuthorRepositoryJPAAdapter.class.getName());

    @Inject
    EntityManager entityManager;

    @Inject
    AuthorFactory authorFactory;

    @Override
    public Set<Author> findAll() {
        List<PersistentAuthor> resultList = entityManager.createNamedQuery(PersistentAuthor.FIND_ALL, PersistentAuthor.class).getResultList();
        LinkedHashSet<Author> ret = new LinkedHashSet<>(resultList.size());
        resultList.stream().map(this::mapAuthor).forEach(ret::add);
        return ret;
    }

    private Optional<PersistentAuthor> loadPersistentAuthor(UUID id) {
        return Optional.ofNullable(entityManager.find(PersistentAuthor.class, id));
    }

    @Override
    public Optional<Author> find(UUID id) {
        LOG.log(Level.INFO, "searching author:{0}", id);

        return loadPersistentAuthor(id).map(this::mapAuthor);
    }

    @Override
    @Transactional
    @WithSpan
    public Author insert(Author author) {
        LOG.log(Level.INFO, "inserting author:{0}", author);

        PersistentAuthor newone = new PersistentAuthor();
        newone.mapFrom(author);

        try {
            entityManager.persist(newone);
            return author;
        } catch (EntityExistsException e) {
            throw new AuthorAlreadyCreated();
        }
    }

    @Override
    @Transactional
    @WithSpan
    public Optional<Author> update(Author origin) {
        LOG.log(Level.INFO, "updating author:{0}", origin);

        Optional<PersistentAuthor> persistentAuthor = loadPersistentAuthor(origin.getId())
                .map(author -> author.mapFrom(origin))
                .map(entityManager::merge);

        persistentAuthor.ifPresent(this::updatePublications);

        return persistentAuthor.map(this::mapAuthor);
    }

    void updatePublications(PersistentAuthor author) {
        author.getPublications().stream().forEach(entityManager::merge);
    }

    Author mapAuthor(PersistentAuthor author) {
        AuthorFactory.WithPublicationBuilder builder = authorFactory.createAuthor(author.getId()).firstname(author.getFirstname()).lastname(author.getLastname());
        author.getBlogPosts().stream().map(this::mapBlogPost).forEach(builder::addBlogPost);

        author.getBooks().stream().map(this::mapBook).forEach(builder::addBook);
        return builder.build();
    }

    Book mapBook(PersistentBook book) {
        return new Book(new ISBN(book.getId()), book.getTitle(), book.getDescription(), book.getPublishingDate(), new Price(book.getPrice(), Currency.valueOf(book.getCurrency())), book.getPages());
    }

    BlogPost mapBlogPost(PersistentBlogPost post) {
        try {
            return new BlogPost(new PublicationID(post.getId()), post.getTitle(), post.getDescription(), post.getPublishingDate(), new URL(post.getUrl()));
        } catch (MalformedURLException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
