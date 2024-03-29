/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.boundary.jpa;

import org.kore.blueprint.publishingcompany.entity.author.Book;
import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 *
 * @author Konrad Renner
 */
@Entity
@DiscriminatorValue("1")
public class PersistentBook extends PersistentPublication {

    @Column
    @Min(1)
    private short pages;

    @Column
    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 18, fraction = 2)
    private BigDecimal price;

    @Column
    @NotNull
    @Pattern(regexp = "(\\bGCRDT\\b)|(\\bSTONE\\b)")
    private String currency;

    public short getPages() {
        return pages;
    }

    public PersistentBook() {
    }

    public PersistentBook(Book origin) {
        super(origin);
        price = origin.getPrice().value();
        currency = origin.getPrice().currency().name();
        pages = origin.getPages();
    }

    public void setPages(short pages) {
        this.pages = pages;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "PersistentBook{" + "pages=" + pages + ", price=" + price + ", currency=" + currency + ", " + super.toString() + '}';
    }

}
