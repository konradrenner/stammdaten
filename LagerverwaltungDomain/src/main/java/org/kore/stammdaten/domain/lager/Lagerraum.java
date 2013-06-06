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

package org.kore.stammdaten.domain.lager;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "LAGERRAUM")
@NamedQueries({
    @NamedQuery(name = "Lagerraum.findAll", query = "SELECT l FROM Lagerraum l"),
    @NamedQuery(name = "Lagerraum.findByRaumId", query = "SELECT l FROM Lagerraum l WHERE l.raumId = :raumId"),
    @NamedQuery(name = "Lagerraum.findByTyp", query = "SELECT l FROM Lagerraum l WHERE l.typ = :typ"),
    @NamedQuery(name = "Lagerraum.findByBezeichnung", query = "SELECT l FROM Lagerraum l WHERE l.bezeichnung = :bezeichnung"),
    @NamedQuery(name = "Lagerraum.findByVolumen", query = "SELECT l FROM Lagerraum l WHERE l.volumen = :volumen")})
public class Lagerraum implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lagerraum")
    private Collection<Lagerraumbestand> lagerraumbestandCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "RAUM_ID")
    private Short raumId;
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "TYP")
    private String typ;
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BEZEICHNUNG")
    private String bezeichnung;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @NotNull
    @Column(name = "VOLUMEN")
    private BigDecimal volumen;
    @JoinColumn(name = "LAGER_ID", referencedColumnName = "LAGER_ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Lager lagerId;

    public Lagerraum() {
    }

    public Lagerraum(Short raumId) {
        this.raumId = raumId;
    }

    public Lagerraum(Short raumId, String typ, String bezeichnung, BigDecimal volumen) {
        this.raumId = raumId;
        this.typ = typ;
        this.bezeichnung = bezeichnung;
        this.volumen = volumen;
    }

    public Short getRaumId() {
        return raumId;
    }

    public void setRaumId(Short raumId) {
        this.raumId = raumId;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public BigDecimal getVolumen() {
        return volumen;
    }

    public void setVolumen(BigDecimal volumen) {
        this.volumen = volumen;
    }

    public Lager getLagerId() {
        return lagerId;
    }

    public void setLagerId(Lager lagerId) {
        this.lagerId = lagerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (raumId != null ? raumId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lagerraum)) {
            return false;
        }
        Lagerraum other = (Lagerraum) object;
        if ((this.raumId == null && other.raumId != null) || (this.raumId != null && !this.raumId.equals(other.raumId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.stammdaten.domain.lager.Lagerraum[ raumId=" + raumId + " ]";
    }

    public Collection<Lagerraumbestand> getLagerraumbestandCollection() {
        return lagerraumbestandCollection;
    }

    public void setLagerraumbestandCollection(Collection<Lagerraumbestand> lagerraumbestandCollection) {
        this.lagerraumbestandCollection = lagerraumbestandCollection;
    }

}
