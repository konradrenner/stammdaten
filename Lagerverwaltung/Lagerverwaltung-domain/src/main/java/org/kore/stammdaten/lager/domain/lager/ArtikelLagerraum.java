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

import org.kore.stammdaten.lager.domain.artikel.Artikel;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "artikel_lagerraum")
@NamedQueries({
    @NamedQuery(name = "ArtikelLagerraum.findAll", query = "SELECT a FROM ArtikelLagerraum a"),
    @NamedQuery(name = "ArtikelLagerraum.findByArtikelId", query = "SELECT a FROM ArtikelLagerraum a WHERE a.artikelLagerraumPK.artikelId = :artikelId"),
    @NamedQuery(name = "ArtikelLagerraum.findByRaumId", query = "SELECT a FROM ArtikelLagerraum a WHERE a.artikelLagerraumPK.raumId = :raumId"),
    @NamedQuery(name = "ArtikelLagerraum.findByVolumenVerbrauch", query = "SELECT a FROM ArtikelLagerraum a WHERE a.volumenVerbrauch = :volumenVerbrauch"),
    @NamedQuery(name = "ArtikelLagerraum.findByVersion", query = "SELECT a FROM ArtikelLagerraum a WHERE a.version = :version")})
public class ArtikelLagerraum implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArtikelLagerraumPK artikelLagerraumPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "volumen_verbrauch")
    private BigDecimal volumenVerbrauch;
    @Basic(optional = false)
    @NotNull
    private int version;
    @JoinColumn(name = "artikel_id", referencedColumnName = "artikel_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Artikel artikel;
    @JoinColumn(name = "raum_id", referencedColumnName = "raum_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Lagerraum lagerraum;

    public ArtikelLagerraum() {
    }

    public ArtikelLagerraum(ArtikelLagerraumPK artikelLagerraumPK) {
        this.artikelLagerraumPK = artikelLagerraumPK;
    }

    public ArtikelLagerraum(ArtikelLagerraumPK artikelLagerraumPK, BigDecimal volumenVerbrauch, int version) {
        this.artikelLagerraumPK = artikelLagerraumPK;
        this.volumenVerbrauch = volumenVerbrauch;
        this.version = version;
    }

    public ArtikelLagerraum(int artikelId, short raumId) {
        this.artikelLagerraumPK = new ArtikelLagerraumPK(artikelId, raumId);
    }

    public ArtikelLagerraumPK getArtikelLagerraumPK() {
        return artikelLagerraumPK;
    }

    public void setArtikelLagerraumPK(ArtikelLagerraumPK artikelLagerraumPK) {
        this.artikelLagerraumPK = artikelLagerraumPK;
    }

    public BigDecimal getVolumenVerbrauch() {
        return volumenVerbrauch;
    }

    public void setVolumenVerbrauch(BigDecimal volumenVerbrauch) {
        this.volumenVerbrauch = volumenVerbrauch;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public Lagerraum getLagerraum() {
        return lagerraum;
    }

    public void setLagerraum(Lagerraum lagerraum) {
        this.lagerraum = lagerraum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artikelLagerraumPK != null ? artikelLagerraumPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtikelLagerraum)) {
            return false;
        }
        ArtikelLagerraum other = (ArtikelLagerraum) object;
        if ((this.artikelLagerraumPK == null && other.artikelLagerraumPK != null) || (this.artikelLagerraumPK != null && !this.artikelLagerraumPK.equals(other.artikelLagerraumPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.stammdaten.lager.domain.lager.ArtikelLagerraum[ artikelLagerraumPK=" + artikelLagerraumPK + " ]";
    }

}
