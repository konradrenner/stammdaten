/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.boundary.jaxrs;

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
 * @author Konrad Renner
 */
public class JsonBook {

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

    public JsonBook() {
        //tool
    }

    @Override
    public String toString() {
        return "BookModel{" + "isbn=" + isbn + ", title=" + title + ", description=" + description + ", publishingDate=" + publishingDate + ", price=" + price + ", currency=" + currency + ", pages=" + pages + '}';
    }
    
    
}
