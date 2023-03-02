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
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private static final Logger LOG = Logger.getLogger(BookService.class.getName());

    @Inject
    CurrencyService service;

    @Inject
    AuthorRepository repo;

    @NotNull
    @Valid
    public Book changeBookPrice(@NotNull @Valid ChangeBookPriceCommand command) {

        LOG.log(Level.INFO, "got change book price command:{0}", command);

        Author author = repo.find(command.getAuthorId()).orElseThrow(AuthorNotFound::new);

        // Example business logic, strictly speaking, this should be in Author class (because it is the aggregate root), so it is just here for showcasing a service
        Book book = author.getBook(command.getISBN()).orElseThrow(BookNotFound::new);
        Price newPrice = command.getNewPrice();

        LOG.log(Level.INFO, "calling currency-service");

        BigDecimal exchangeRatio = service.evaluateExchangeRatio(newPrice.currency(), book.getPrice().currency());

        LOG.log(Level.INFO, "got exchangeRation:{0}", exchangeRatio);

        BigDecimal valueToAdd = newPrice.value().multiply(exchangeRatio).subtract(book.getPrice().value());

        book.addValueToPrice(valueToAdd);

        LOG.log(Level.INFO, "updated book price:{0}", book.getPrice());

        //Um die Datenkonsistenz immer sicherstellen zu koennen, wird das gesamte Aggregate persistiert
        Optional<Author> update = repo.update(author);
        return book;
    }
}
