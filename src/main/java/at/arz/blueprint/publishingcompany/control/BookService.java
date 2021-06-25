/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.arz.blueprint.publishingcompany.control;

import at.arz.blueprint.publishingcompany.entity.author.Author;
import at.arz.blueprint.publishingcompany.entity.author.events.BookNotFound;
import at.arz.blueprint.publishingcompany.entity.author.Book;
import at.arz.blueprint.publishingcompany.entity.author.Price;
import at.arz.blueprint.publishingcompany.entity.author.events.AuthorNotFound;
import java.math.BigDecimal;
import java.util.Optional;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author rpri182
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

        BigDecimal exchangeRatio = service.evaluateExchangeRatio(newPrice.getCurrency(), book.getPrice().getCurrency());

        BigDecimal valueToAdd = newPrice.getValue().multiply(exchangeRatio).subtract(book.getPrice().getValue());

        book.addValueToPrice(valueToAdd);

        //Um die Datenkonsistenz immer sicherstellen zu koennen, wird das gesamte Aggregate persistiert
        Optional<Author> update = repo.update(author);
        return book;
    }
}
