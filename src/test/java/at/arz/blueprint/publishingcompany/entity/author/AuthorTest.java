/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.arz.blueprint.publishingcompany.entity.author;

import at.arz.blueprint.publishingcompany.entity.author.Price;
import at.arz.blueprint.publishingcompany.entity.author.Book;
import at.arz.blueprint.publishingcompany.entity.author.Author;
import at.arz.blueprint.publishingcompany.entity.author.Name;
import at.arz.blueprint.publishingcompany.entity.author.Currency;
import at.arz.blueprint.publishingcompany.entity.author.ISBN;
import at.arz.blueprint.publishingcompany.entity.author.BlogPost;
import at.arz.blueprint.publishingcompany.entity.author.PublicationID;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author rpri182
 */
public class AuthorTest {

    private Author underTest;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        Book testBook = new Book(new ISBN("9783866801929"), "Testbook", "Testdesc", LocalDateTime.now(), new Price(BigDecimal.ONE, Currency.GCRDT), (short) 2);
        BlogPost testBlog = new BlogPost(new PublicationID("3783866801929"), "Testblog", "Testing", LocalDateTime.now(), new URL("http://www.google.com"));

        Map<PublicationID, BlogPost> publishedBlogPosts = new HashMap<>();
        publishedBlogPosts.put(testBlog.getId(), testBlog);
        Map<ISBN, Book> publishedBooks = new HashMap<>();
        publishedBooks.put(testBook.getId(), testBook);

        underTest = new Author(UUID.randomUUID(), new Name("Max", "Mustermann"), publishedBooks, publishedBlogPosts);
    }

    @Test
    public void addBookWhichExists() {
        Book toAdd = new Book(new ISBN("9783866801929"), "Testbook", "Book with existing ISBN", LocalDateTime.now(), new Price(BigDecimal.ONE, Currency.GCRDT), (short) 2);
        Optional<Book> addedPublication = underTest.addPublication(toAdd);
        assertThrows(NoSuchElementException.class, addedPublication::get);
    }

    @Test
    public void addBookWhichNotExists() {
        Book toAdd = new Book(new ISBN("1113866801929"), "Testbook", "New Book", LocalDateTime.now(), new Price(BigDecimal.ONE, Currency.GCRDT), (short) 2);
        Optional<Book> addedPublication = underTest.addPublication(toAdd);

        assertEquals(toAdd, addedPublication.get());
    }

    @Test
    public void addBlogWhichExists() throws MalformedURLException {
        BlogPost toAdd = new BlogPost(new PublicationID("3783866801929"), "Testblog", "Blog with existing PublicationID", LocalDateTime.now(), new URL("http://www.google.com"));
        Optional<BlogPost> addedPublication = underTest.addPublication(toAdd);
        assertThrows(NoSuchElementException.class, addedPublication::get);
    }

    @Test
    public void addBlogWhichNotExists() throws MalformedURLException {
        BlogPost toAdd = new BlogPost(new PublicationID("1113866801929"), "Testblog", "New Blog", LocalDateTime.now(), new URL("http://www.google.com"));
        Optional<BlogPost> addedPublication = underTest.addPublication(toAdd);

        assertEquals(toAdd, addedPublication.get());
    }

}
