/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.entity.author;

import org.kore.blueprint.publishingcompany.entity.constraints.ValueObject;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Konrad Renner
 */
@ValueObject
public final class Price {

    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 18, fraction = 2)
    private final BigDecimal value;
    @NotNull
    private final Currency currency;

    public Price(BigDecimal value, Currency currency) {
        Objects.requireNonNull(value);
        Objects.requireNonNull(currency);
        this.value = value.setScale(2, RoundingMode.HALF_UP);
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Price{" + "value=" + value + ", currency=" + currency + '}';
    }

}
