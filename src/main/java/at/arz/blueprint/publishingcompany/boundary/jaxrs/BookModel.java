/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.arz.blueprint.publishingcompany.boundary.jaxrs;

import at.arz.blueprint.publishingcompany.entity.author.Book;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author rpri182
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
    @Past
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
        price = origin.getPrice().getValue();
        currency = origin.getPrice().getCurrency().name();
        pages = origin.getPages();
    }
}
