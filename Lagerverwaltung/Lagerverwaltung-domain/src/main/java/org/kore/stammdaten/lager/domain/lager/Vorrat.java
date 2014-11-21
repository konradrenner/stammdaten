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
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "artikel_lagerraum")
public class Vorrat implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VorratKey vorratKey;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @NotNull
    @Column(name = "volumen_verbrauch")
    //Verbrauchtes volumen im Lagerraum, pro Einheit
    private BigDecimal volumenVerbrauch;
    @Version
    private int version;
    @Column
    @NotNull
    @Size(min = 1, max = 3)
    private String masseinheit;
    @Column
    @NotNull
    private BigDecimal einheiten;
//    @Id
//    @JoinColumn(name = "artikel_id")
//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    private Artikel artikel;

    protected Vorrat() {
    }

    protected Vorrat(VorratKey vorratKey, BigDecimal volumenVerbrauch, int version, String masseinheit, BigDecimal einheiten) {
        this.vorratKey = vorratKey;
        this.volumenVerbrauch = volumenVerbrauch;
        this.version = version;
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

    public String getMasseinheit() {
        return masseinheit;
    }

    public BigDecimal getEinheiten() {
        return einheiten;
    }

//    public Artikel getArtikel() {
//        return artikel;
//    }

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
