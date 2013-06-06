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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "VERSANDKOSTEN")
@NamedQueries({
    @NamedQuery(name = "Versandkosten.findAll", query = "SELECT v FROM Versandkosten v"),
    @NamedQuery(name = "Versandkosten.findByLand", query = "SELECT v FROM Versandkosten v WHERE v.land = :land"),
    @NamedQuery(name = "Versandkosten.findByBetrag", query = "SELECT v FROM Versandkosten v WHERE v.betrag = :betrag"),
    @NamedQuery(name = "Versandkosten.findByFreibetrag", query = "SELECT v FROM Versandkosten v WHERE v.freibetrag = :freibetrag")})
public class Versandkosten implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "LAND")
    private String land;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @NotNull
    @Column(name = "BETRAG")
    private BigDecimal betrag;
    @Column(name = "FREIBETRAG")
    private BigDecimal freibetrag;

    public Versandkosten() {
    }

    public Versandkosten(String land) {
        this.land = land;
    }

    public Versandkosten(String land, BigDecimal betrag) {
        this.land = land;
        this.betrag = betrag;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }

    public void setBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }

    public BigDecimal getFreibetrag() {
        return freibetrag;
    }

    public void setFreibetrag(BigDecimal freibetrag) {
        this.freibetrag = freibetrag;
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
