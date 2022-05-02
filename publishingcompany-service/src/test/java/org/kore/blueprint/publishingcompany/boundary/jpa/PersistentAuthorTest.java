/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.boundary.jpa;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Konrad Renner
 */
public class PersistentAuthorTest {

    PersistentAuthor underTest;

    @BeforeEach
    public void setUp() {
        Map<String, PersistentPublication> publications = new HashMap<>();

        PersistentBook book = mock(PersistentBook.class);
        when(book.getId()).thenReturn("BOOK");
        when(book.getPublicationtype()).thenReturn('1');
        when(book.getPrice()).thenReturn(BigDecimal.TEN);
        PersistentBlogPost post = mock(PersistentBlogPost.class);
        when(post.getId()).thenReturn("BLOGPOST");
        when(post.getPublicationtype()).thenReturn('2');

        publications.put(book.getId(), book);
        publications.put(post.getId(), post);

        underTest = new PersistentAuthor(publications);
    }

    @Test
    public void updateBook() {
        LinkedHashMap<String, PersistentPublication> initialPublications = new LinkedHashMap<>();
        LinkedHashSet<PersistentBook> initialBooks = new LinkedHashSet<>();
        createBooks(initialBooks::add, book -> initialPublications.put(book.getId(), book));

        underTest = new PersistentAuthor(initialPublications);

        PersistentBook changedBook = new PersistentBook();
        changedBook.setId("BOOK");
        changedBook.setPublicationType('1');
        changedBook.setPrice(BigDecimal.ONE);

        underTest.updateBook(changedBook);

        Set<PersistentBook> books = underTest.getBooks();
        assertTrue(books.size() == 1);
        assertEquals(BigDecimal.ONE, books.iterator().next().getPrice());
    }

    @Test
    public void addBook() {
        LinkedHashMap<String, PersistentPublication> initialPublications = new LinkedHashMap<>();
        createBooks(book -> initialPublications.put(book.getId(), book));

        underTest = new PersistentAuthor(initialPublications);

        PersistentBook changedBook = new PersistentBook();
        changedBook.setId("BOOK2");
        changedBook.setPublicationType('1');
        changedBook.setPrice(BigDecimal.ONE);

        underTest.updateBook(changedBook);

        Set<PersistentBook> books = underTest.getBooks();
        assertTrue(books.size() == 2);
        Iterator<PersistentBook> iterator = books.iterator();
        assertEquals(BigDecimal.TEN, iterator.next().getPrice());
        assertEquals(BigDecimal.ONE, iterator.next().getPrice());
    }

    private void createBooks(Consumer<PersistentBook>... consumers) {
        final PersistentBook originalBook = new PersistentBook();
        originalBook.setId("BOOK");
        originalBook.setPrice(BigDecimal.TEN);
        originalBook.setPublicationType('1');

        Arrays.stream(consumers).forEach(consumer -> consumer.accept(originalBook));
    }
}
