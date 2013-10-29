/*
 * Copyright (C) 2013 Free Software Foundation.
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

package org.kore.stammdaten.domain.versandkosten;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@NamedQueries({
    @NamedQuery(name = "Versandkosten.findAll", query = "SELECT v FROM Versandkosten v"),
    @NamedQuery(name = "Versandkosten.findByLand", query = "SELECT v FROM Versandkosten v WHERE v.land = :land")})
public class Versandkosten implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @AttributeOverride(name = "iso3166Code", column =
            @Column(name = "LAND"))
    private Land land;
    @NotNull
    @Min(0)
    @Column(name = "BETRAG")
    private BigDecimal betrag;
    @Min(0)
    @Column(name = "FREIBETRAG")
    private BigDecimal freibetrag;
    @NotNull
    @Column(name = "WAEHRUNG")
    private String waehrung;
    @Transient
    private Money cachedBetrag;
    @Transient
    private Money cachedFreibetrag;

    protected Versandkosten() {
        //JPA
    }


    Versandkosten(Land land, Money betrag) {
        this.land = land;
        this.cachedBetrag = betrag;
        this.betrag = betrag.getAmount();
        this.waehrung = betrag.getCurrency().getCurrencyCode();
    }

    public Land getLand() {
        return land;
    }

    public Money getBetrag() {
        if (this.cachedBetrag == null) {
            this.cachedBetrag = new Money(betrag, Currency.getInstance(waehrung));
        }
        return this.cachedBetrag;
    }

    void setBetrag(Money betrag) {
        this.cachedBetrag = betrag;
        this.betrag = betrag.getAmount();
    }

    public Money getFreibetrag() {
        if (this.cachedFreibetrag == null && freibetrag != null) {
            this.cachedFreibetrag = new Money(freibetrag, Currency.getInstance(waehrung));
        }
        return this.cachedFreibetrag;
    }

    void setFreibetrag(Money freibetrag) {
        this.cachedFreibetrag = freibetrag;
        this.freibetrag = freibetrag.getAmount();
    }

    void setWaehrung(Currency waehrung) {
        this.waehrung = waehrung.getCurrencyCode();
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
        return "org.kore.stammdaten.domain.lager.Versandkosten[ land=" + land + " ]";
    }

}
