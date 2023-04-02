/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import org.kore.blueprint.publishingcompany.entity.author.Book;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Konrad Renner
 */
public class BookModel {

    @NotNull
    @NotBlank
    @Size(min = 13, max = 13)
    public String isbn;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    public String title;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 300)
    public String description;
    @NotNull
    @PastOrPresent
    public LocalDateTime publishingDate;
    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 18, fraction = 2)
    public BigDecimal price;
    @NotNull
    @Pattern(regexp = "(\\bGCRDT\\b)|(\\bSTONE\\b)")
    public String currency;
    @Min(1)
    public short pages;

    public BookModel() {
        //tool
    }

    public BookModel(Book origin) {
        isbn = origin.getId().getValue();
        title = origin.getTitle();
        description = origin.getDescription();
        publishingDate = origin.getPublishingDate();
        price = origin.getPrice().value();
        currency = origin.getPrice().currency().name();
        pages = origin.getPages();
    }
}
