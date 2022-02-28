/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.control;

import org.kore.blueprint.publishingcompany.entity.author.Author;
import org.kore.blueprint.publishingcompany.entity.author.Book;
import org.kore.blueprint.publishingcompany.entity.author.Currency;
import org.kore.blueprint.publishingcompany.entity.author.ISBN;
import org.kore.blueprint.publishingcompany.entity.author.Price;
import org.kore.blueprint.publishingcompany.entity.author.events.AuthorNotFound;
import org.kore.blueprint.publishingcompany.entity.author.events.BookNotFound;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Konrad Renner
 */
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    CurrencyService currencyService;

    @Mock
    AuthorRepository repo;

    @Mock
    ChangeBookPriceCommand command;

    @Mock
    Author testAuthor;

    BookService underTest;

    @BeforeEach
    public void setUp() {
        underTest = new BookService();
        underTest.repo = this.repo;
        underTest.service = this.currencyService;

        UUID someAuthorID = UUID.randomUUID();

        when(command.getAuthorId()).thenReturn(someAuthorID);
    }

    @Test
    public void authorNotFound() {
        when(repo.find(command.getAuthorId())).thenReturn(Optional.empty());

        assertThrows(AuthorNotFound.class, () -> underTest.changeBookPrice(command));
    }

    @Test
    public void bookNotFound() {
        when(testAuthor.getBook(command.getISBN())).thenReturn(Optional.empty());
        when(repo.find(command.getAuthorId())).thenReturn(Optional.of(testAuthor));

        assertThrows(BookNotFound.class, () -> underTest.changeBookPrice(command));
    }

    @Test
    public void bookPriceUpdated() {
        when(currencyService.evaluateExchangeRatio(Currency.GCRDT, Currency.STONE)).thenReturn(BigDecimal.TEN);
        when(command.getISBN()).thenReturn(new ISBN("ABC"));
        when(command.getNewPrice()).thenReturn(new Price(BigDecimal.ONE, Currency.GCRDT));

        Book book = new Book(command.getISBN(), "Test", "Testdescription", LocalDateTime.now(), new Price(BigDecimal.ONE, Currency.STONE), (short) 10);
        when(testAuthor.getBook(command.getISBN())).thenReturn(Optional.of(book));
        when(repo.find(command.getAuthorId())).thenReturn(Optional.of(testAuthor));

        Book bookWithChangedPrice = underTest.changeBookPrice(command);

        assertEquals(BigDecimal.TEN.setScale(2), bookWithChangedPrice.getPrice().value());
    }
}
