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
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.kore.runtime.specifications.Description;
import org.kore.runtime.specifications.Identifier;

/**
 *
 * @author Konrad Renner
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Artikelgruppe.findAll", query = "SELECT a FROM Artikelgruppe a")
})
public class Artikelgruppe implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @NotNull
    private Identifier bezeichnung;
    @Embedded
    private Description beschreibung;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    private String typ;
    @Basic(optional = false)
    @NotNull
    private int version;
    @ManyToMany(mappedBy = "artikelGruppen")
    private Collection<Artikel> artikel;

    protected Artikelgruppe() {
    }

    protected Artikelgruppe(Identifier bezeichnung, Description beschreibung, String typ) {
        this.bezeichnung = bezeichnung;
        this.beschreibung = beschreibung;
        this.typ = typ;
        this.artikel = new ArrayList<>();
    }

    public Description getBeschreibung() {
        return beschreibung;
    }

    public Identifier getBezeichnung() {
        return bezeichnung;
    }

    public String getTyp() {
        return typ;
    }

    public int getVersion() {
        return version;
    }

    public Collection<Artikel> getArtikel() {
        return artikel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bezeichnung != null ? bezeichnung.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artikelgruppe)) {
            return false;
        }
        Artikelgruppe other = (Artikelgruppe) object;
        if ((this.bezeichnung == null && other.bezeichnung != null) || (this.bezeichnung != null && !this.bezeichnung.equals(other.bezeichnung))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.stammdaten.lager.domain.lager.Artikelgruppe[ bezeichnung=" + bezeichnung + " ]";
    }

}
