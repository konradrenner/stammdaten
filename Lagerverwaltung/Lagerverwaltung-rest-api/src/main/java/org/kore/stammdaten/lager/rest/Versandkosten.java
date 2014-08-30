/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.kore.stammdaten.lager.rest;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.kore.runtime.currency.Money;

/**
 * @author Konrad Renner
 */
@XmlRootElement
@XmlAccessorType(value = XmlAccessType.FIELD)
public class Versandkosten {

    @XmlElement(required = true)
    @NotNull
    @Size(min = 2, max = 2)
    private String land;

    @XmlElement(required = true)
    @NotNull
    @Min(0)
    private BigDecimal betrag;

    @XmlElement
    @Min(0)
    private BigDecimal freibetrag;

    @XmlElement(required = true)
    @NotNull
    @Size(min = 3, max = 3)
    private String waehrung;

    Versandkosten() {
        //for frameworks
    }

    public String getLand() {
        return land;
    }

    public Money getBetrag() {
        return new Money(betrag, Currency.getInstance(waehrung));
    }

    public Money getFreibetrag() {
        if (freibetrag == null) {
            return null;
        }
        return new Money(freibetrag, Currency.getInstance(waehrung));
    }

    @Override
    public String toString() {
        return "Versandkosten{" + "land=" + land + ", betrag=" + betrag + ", freibetrag=" + freibetrag + ", waehrung=" + waehrung + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.land);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Versandkosten other = (Versandkosten) obj;
        if (!Objects.equals(this.land, other.land)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        public Builder(String land, Money betrag) {
            Objects.requireNonNull(land, "land must not be null");
            Objects.requireNonNull(betrag, "betrag must not be null");
            this.item = new Versandkosten();
            this.item.betrag = betrag.getAmount();
            this.item.waehrung = betrag.getCurrency().getCurrencyCode();
            this.item.land = land;
        }
        private Versandkosten item;

        public Builder withFreibetrag(final BigDecimal freibetrag) {
            Objects.requireNonNull(freibetrag, "freibetrag must not be null");
            this.item.freibetrag = freibetrag;
            return this;
        }

        public Versandkosten build() {
            return this.item;
        }
    }

}
