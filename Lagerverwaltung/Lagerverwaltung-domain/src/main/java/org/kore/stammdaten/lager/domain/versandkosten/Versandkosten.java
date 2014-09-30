/*
 * Copyright (C) 2014 Konrad Renner.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package org.kore.stammdaten.lager.domain.versandkosten;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.kore.runtime.currency.Money;
import org.kore.stammdaten.core.adresse.Land;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "VERSANDKOSTEN")
@NamedQuery(name = "Versandkosten.findAll", query = "SELECT v FROM Versandkosten v")
public class Versandkosten implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @AttributeOverride(name = "iso3166Code", column = @Column(name = "LAND"))
    private Land land;
    @Min(0)
    @Column(name = "BETRAG")
    @NotNull
    private BigDecimal betrag;
    @Column(name = "FREIBETRAG")
    private BigDecimal freibetrag;
    @NotNull
    @Column(name = "WAEHRUNG")
    private String waehrung;

    protected Versandkosten() {
        //JPA
    }


    public Versandkosten(Land land, Money betrag) {
        this.land = land;
        this.betrag = betrag.getAmount();
        this.waehrung = betrag.getCurrency().getCurrencyCode();
    }

    public Land getLand() {
        return this.land;
    }

    public Money getBetrag() {
        return new Money(this.betrag, Currency.getInstance(this.waehrung.trim()));
    }

    public Money getFreibetrag() {
        if (this.freibetrag == null) {
            return null;
        }
        return new Money(this.freibetrag, Currency.getInstance(this.waehrung.trim()));
    }

    void setFreibetrag(BigDecimal freibetrag) {
        this.freibetrag = freibetrag;
    }

    void setWaehrung(Currency curr) {
        this.waehrung = curr.getCurrencyCode();
    }

    void setBetrag(BigDecimal betr) {
        this.betrag = betr;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (land != null ? land.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Versandkosten)) {
            return false;
        }
        Versandkosten other = (Versandkosten) object;
        if ((this.land == null && other.land != null) || (this.land != null && !this.land.equals(other.land))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.stammdaten.lager.domain.versandkosten.Versandkosten[ land=" + land + " ]";
    }
}
