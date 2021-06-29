/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.entity.author;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Konrad Renner
 */
public class Book extends Publication {

    @NotNull
    @Valid
    private Price price;
    @Min(1)
    private final short pages;

    public Book(ISBN id, String title, String description, LocalDateTime publishingDate, Price price, short pages) {
        super(id, title, description, publishingDate);
        this.pages = pages;
        this.price = price;
    }

    @Override
    public ISBN getId() {
        return (ISBN) super.getId();
    }

    public Price getPrice() {
        return price;
    }

    public short getPages() {
        return pages;
    }

    public Price addValueToPrice(BigDecimal summand) {
        Objects.requireNonNull(summand);
        this.price = new Price(this.price.getValue().add(summand), this.price.getCurrency());
        return this.price;
    }

    @Override
    public String toString() {
        return "Book{" + "price=" + price + ", pages=" + pages + super.toString() + '}';
    }

}
