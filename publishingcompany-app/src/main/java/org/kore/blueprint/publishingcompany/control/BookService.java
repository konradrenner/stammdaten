/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.control;

import org.kore.blueprint.publishingcompany.entity.author.Author;
import org.kore.blueprint.publishingcompany.entity.author.events.BookNotFound;
import org.kore.blueprint.publishingcompany.entity.author.Book;
import org.kore.blueprint.publishingcompany.entity.author.Price;
import org.kore.blueprint.publishingcompany.entity.author.events.AuthorNotFound;
import java.math.BigDecimal;
import java.util.Optional;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Konrad Renner
 */
@Dependent
public class BookService {

    @Inject
    CurrencyService service;

    @Inject
    AuthorRepository repo;

    @NotNull
    @Valid
    public Book changeBookPrice(@NotNull @Valid ChangeBookPriceCommand command) {
        Author author = repo.find(command.getAuthorId()).orElseThrow(AuthorNotFound::new);

        // Eine exemplarische 'komplizierte' Business Logik
        Book book = author.getBook(command.getISBN()).orElseThrow(BookNotFound::new);
        Price newPrice = command.getNewPrice();

        BigDecimal exchangeRatio = service.evaluateExchangeRatio(newPrice.currency(), book.getPrice().currency());

        BigDecimal valueToAdd = newPrice.value().multiply(exchangeRatio).subtract(book.getPrice().value());

        book.addValueToPrice(valueToAdd);

        //Um die Datenkonsistenz immer sicherstellen zu koennen, wird das gesamte Aggregate persistiert
        Optional<Author> update = repo.update(author);
        return book;
    }
}
