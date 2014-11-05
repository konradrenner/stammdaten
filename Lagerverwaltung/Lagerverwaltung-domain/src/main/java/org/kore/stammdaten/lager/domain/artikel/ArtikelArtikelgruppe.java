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
package org.kore.stammdaten.lager.domain.artikel;

import java.io.Serializable;
import javax.persistence.Basic;
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
@Table(name = "artikel_artikelgruppe")
@NamedQueries({
    @NamedQuery(name = "ArtikelArtikelgruppe.findAll", query = "SELECT a FROM ArtikelArtikelgruppe a"),
    @NamedQuery(name = "ArtikelArtikelgruppe.findByArtikelId", query = "SELECT a FROM ArtikelArtikelgruppe a WHERE a.artikelArtikelgruppePK.artikelId = :artikelId"),
    @NamedQuery(name = "ArtikelArtikelgruppe.findByBezeichnung", query = "SELECT a FROM ArtikelArtikelgruppe a WHERE a.artikelArtikelgruppePK.bezeichnung = :bezeichnung"),
    @NamedQuery(name = "ArtikelArtikelgruppe.findByVersion", query = "SELECT a FROM ArtikelArtikelgruppe a WHERE a.version = :version")})
public class ArtikelArtikelgruppe implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArtikelArtikelgruppePK artikelArtikelgruppePK;
    @Basic(optional = false)
    @NotNull
    private int version;
    @JoinColumn(name = "artikel_id", referencedColumnName = "artikel_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Artikel artikel;
    @JoinColumn(name = "bezeichnung", referencedColumnName = "bezeichnung", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Artikelgruppe artikelgruppe;

    public ArtikelArtikelgruppe() {
    }

    public ArtikelArtikelgruppe(ArtikelArtikelgruppePK artikelArtikelgruppePK) {
        this.artikelArtikelgruppePK = artikelArtikelgruppePK;
    }

    public ArtikelArtikelgruppe(ArtikelArtikelgruppePK artikelArtikelgruppePK, int version) {
        this.artikelArtikelgruppePK = artikelArtikelgruppePK;
        this.version = version;
    }

    public ArtikelArtikelgruppe(int artikelId, String bezeichnung) {
        this.artikelArtikelgruppePK = new ArtikelArtikelgruppePK(artikelId, bezeichnung);
    }

    public ArtikelArtikelgruppePK getArtikelArtikelgruppePK() {
        return artikelArtikelgruppePK;
    }

    public void setArtikelArtikelgruppePK(ArtikelArtikelgruppePK artikelArtikelgruppePK) {
        this.artikelArtikelgruppePK = artikelArtikelgruppePK;
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

    public Artikelgruppe getArtikelgruppe() {
        return artikelgruppe;
    }

    public void setArtikelgruppe(Artikelgruppe artikelgruppe) {
        this.artikelgruppe = artikelgruppe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artikelArtikelgruppePK != null ? artikelArtikelgruppePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtikelArtikelgruppe)) {
            return false;
        }
        ArtikelArtikelgruppe other = (ArtikelArtikelgruppe) object;
        if ((this.artikelArtikelgruppePK == null && other.artikelArtikelgruppePK != null) || (this.artikelArtikelgruppePK != null && !this.artikelArtikelgruppePK.equals(other.artikelArtikelgruppePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.stammdaten.lager.domain.lager.ArtikelArtikelgruppe[ artikelArtikelgruppePK=" + artikelArtikelgruppePK + " ]";
    }

}
