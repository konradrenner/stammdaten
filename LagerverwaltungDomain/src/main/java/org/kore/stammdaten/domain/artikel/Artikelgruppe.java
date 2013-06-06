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

package org.kore.stammdaten.domain.artikel;

import org.kore.stammdaten.domain.artikel.Artikel;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "ARTIKELGRUPPE")
@NamedQueries({
    @NamedQuery(name = "Artikelgruppe.findAll", query = "SELECT a FROM Artikelgruppe a"),
    @NamedQuery(name = "Artikelgruppe.findByBezeichnung", query = "SELECT a FROM Artikelgruppe a WHERE a.bezeichnung = :bezeichnung"),
    @NamedQuery(name = "Artikelgruppe.findByBeschreibung", query = "SELECT a FROM Artikelgruppe a WHERE a.beschreibung = :beschreibung"),
    @NamedQuery(name = "Artikelgruppe.findByTyp", query = "SELECT a FROM Artikelgruppe a WHERE a.typ = :typ")})
public class Artikelgruppe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BEZEICHNUNG")
    private String bezeichnung;
    @Size(max = 300)
    @Column(name = "BESCHREIBUNG")
    private String beschreibung;
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "TYP")
    private String typ;
    @ManyToMany(mappedBy = "artikelgruppeSet", fetch = FetchType.EAGER)
    private Set<Artikel> artikelSet;

    public Artikelgruppe() {
    }

    public Artikelgruppe(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public Artikelgruppe(String bezeichnung, String typ) {
        this.bezeichnung = bezeichnung;
        this.typ = typ;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Set<Artikel> getArtikelSet() {
        return artikelSet;
    }

    public void setArtikelSet(Set<Artikel> artikelSet) {
        this.artikelSet = artikelSet;
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
        return "org.kore.stammdaten.domain.lager.Artikelgruppe[ bezeichnung=" + bezeichnung + " ]";
    }

}
