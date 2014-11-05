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
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Konrad Renner
 */
@Embeddable
public class ArtikelArtikelgruppePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "artikel_id")
    private int artikelId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String bezeichnung;

    public ArtikelArtikelgruppePK() {
    }

    public ArtikelArtikelgruppePK(int artikelId, String bezeichnung) {
        this.artikelId = artikelId;
        this.bezeichnung = bezeichnung;
    }

    public int getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(int artikelId) {
        this.artikelId = artikelId;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) artikelId;
        hash += (bezeichnung != null ? bezeichnung.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArtikelArtikelgruppePK)) {
            return false;
        }
        ArtikelArtikelgruppePK other = (ArtikelArtikelgruppePK) object;
        if (this.artikelId != other.artikelId) {
            return false;
        }
        if ((this.bezeichnung == null && other.bezeichnung != null) || (this.bezeichnung != null && !this.bezeichnung.equals(other.bezeichnung))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.stammdaten.lager.domain.lager.ArtikelArtikelgruppePK[ artikelId=" + artikelId + ", bezeichnung=" + bezeichnung + " ]";
    }

}
