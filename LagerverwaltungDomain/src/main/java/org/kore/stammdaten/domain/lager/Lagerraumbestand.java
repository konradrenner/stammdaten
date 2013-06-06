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
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.kore.stammdaten.domain.artikel.Artikel;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "ARTIKEL_LAGERRAUM")
@NamedQueries({
    @NamedQuery(name = "Lagerraumbestand.findAll", query = "SELECT l FROM Lagerraumbestand l")})
public class Lagerraumbestand implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LagerraumbestandPK lagerraumbestandPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @NotNull
    @Column(name = "VOLUMEN_VERBRAUCH")
    private BigDecimal volumenVerbrauch;
    @JoinColumn(name = "RAUM_ID", referencedColumnName = "RAUM_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Lagerraum lagerraum;
    @JoinColumn(name = "ARTIKEL_ID", referencedColumnName = "ARTIKEL_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Artikel artikel;

    public Lagerraumbestand() {
    }

    public Lagerraumbestand(LagerraumbestandPK lagerraumbestandPK) {
        this.lagerraumbestandPK = lagerraumbestandPK;
    }

    public Lagerraumbestand(LagerraumbestandPK lagerraumbestandPK, BigDecimal volumenVerbrauch) {
        this.lagerraumbestandPK = lagerraumbestandPK;
        this.volumenVerbrauch = volumenVerbrauch;
    }

    public Lagerraumbestand(int artikelId, short raumId) {
        this.lagerraumbestandPK = new LagerraumbestandPK(artikelId, raumId);
    }

    public LagerraumbestandPK getLagerraumbestandPK() {
        return lagerraumbestandPK;
    }

    public void setLagerraumbestandPK(LagerraumbestandPK lagerraumbestandPK) {
        this.lagerraumbestandPK = lagerraumbestandPK;
    }

    public BigDecimal getVolumenVerbrauch() {
        return volumenVerbrauch;
    }

    public void setVolumenVerbrauch(BigDecimal volumenVerbrauch) {
        this.volumenVerbrauch = volumenVerbrauch;
    }

    public Lagerraum getLagerraum() {
        return lagerraum;
    }

    public void setLagerraum(Lagerraum lagerraum) {
        this.lagerraum = lagerraum;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lagerraumbestandPK != null ? lagerraumbestandPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lagerraumbestand)) {
            return false;
        }
        Lagerraumbestand other = (Lagerraumbestand) object;
        if ((this.lagerraumbestandPK == null && other.lagerraumbestandPK != null) || (this.lagerraumbestandPK != null && !this.lagerraumbestandPK.equals(other.lagerraumbestandPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.stammdaten.domain.lager.Lagerraumbestand[ lagerraumbestandPK=" + lagerraumbestandPK + " ]";
    }

}
