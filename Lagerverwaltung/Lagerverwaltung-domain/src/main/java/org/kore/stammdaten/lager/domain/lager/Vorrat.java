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
package org.kore.stammdaten.lager.domain.lager;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "artikel_lagerraum")
@NamedQueries({
    @NamedQuery(name = "Vorrat.findAll", query = "SELECT v FROM Vorrat v")})
public class Vorrat implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VorratKey vorratKey;
    @NotNull
    @Column(name = "volumen_verbrauch")
    //Verbrauchtes volumen im Lagerraum, pro Einheit
    private BigDecimal volumenVerbrauch;
    @Version
    private int version;
    @NotNull
    @Enumerated(EnumType.STRING)
    //Die Einheit des Artikels mit der er im Vorrat liegt (sollte mit der Einheit des Lagers korrelieren)
    private Masseinheit masseinheit;
    @Column
    @NotNull
    private BigDecimal einheiten;

    protected Vorrat() {
    }

    protected Vorrat(VorratKey vorratKey, BigDecimal volumenVerbrauch, Masseinheit masseinheit, BigDecimal einheiten) {
        this.vorratKey = vorratKey;
        this.volumenVerbrauch = volumenVerbrauch;
        this.masseinheit = masseinheit;
        this.einheiten = einheiten;
    }

    public VorratKey getVorratPK() {
        return vorratKey;
    }

    public BigDecimal getVolumenVerbrauch() {
        return volumenVerbrauch;
    }

    public int getVersion() {
        return version;
    }

    public Masseinheit getMasseinheit() {
        return masseinheit;
    }

    public BigDecimal getEinheiten() {
        return einheiten;
    }

    void setEinheiten(BigDecimal einheiten) {
        this.einheiten = einheiten;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vorratKey != null ? vorratKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vorrat)) {
            return false;
        }
        Vorrat other = (Vorrat) object;
        if ((this.vorratKey == null && other.vorratKey != null) || (this.vorratKey != null && !this.vorratKey.equals(other.vorratKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.stammdaten.lager.domain.lager.ArtikelLagerraum[ vorratKey=" + vorratKey + " ]";
    }

}
