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

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.kore.stammdaten.domain.lager.Lagerraumbestand;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "ARTIKEL")
@NamedQueries({
    @NamedQuery(name = "Artikel.findAll", query = "SELECT a FROM Artikel a"),
    @NamedQuery(name = "Artikel.findByArtikelId", query = "SELECT a FROM Artikel a WHERE a.artikelId = :artikelId"),
    @NamedQuery(name = "Artikel.findByBezeichnung", query = "SELECT a FROM Artikel a WHERE a.bezeichnung = :bezeichnung"),
    @NamedQuery(name = "Artikel.findByBeschreibung", query = "SELECT a FROM Artikel a WHERE a.beschreibung = :beschreibung"),
    @NamedQuery(name = "Artikel.findByPreis", query = "SELECT a FROM Artikel a WHERE a.preis = :preis"),
    @NamedQuery(name = "Artikel.findByWaehrung", query = "SELECT a FROM Artikel a WHERE a.waehrung = :waehrung")})
public class Artikel implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artikel")
    private Collection<Lagerraumbestand> lagerraumbestandCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "ARTIKEL_ID")
    private Integer artikelId;
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BEZEICHNUNG")
    private String bezeichnung;
    @Size(max = 300)
    @Column(name = "BESCHREIBUNG")
    private String beschreibung;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @NotNull
    @Column(name = "PREIS")
    private BigDecimal preis;
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "WAEHRUNG")
    private String waehrung;
    @Lob
    @Column(name = "BILD")
    private Serializable bild;
    @JoinTable(name = "ARTIKEL_ARTIKELGRUPPE", joinColumns = {
        @JoinColumn(name = "ARTIKEL_ID", referencedColumnName = "ARTIKEL_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "BEZEICHNUNG", referencedColumnName = "BEZEICHNUNG")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Artikelgruppe> artikelgruppeSet;

    public Artikel() {
    }

    public Artikel(Integer artikelId) {
        this.artikelId = artikelId;
    }

    public Artikel(Integer artikelId, String bezeichnung, BigDecimal preis, String waehrung) {
        this.artikelId = artikelId;
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.waehrung = waehrung;
    }

    public Integer getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(Integer artikelId) {
        this.artikelId = artikelId;
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

    public BigDecimal getPreis() {
        return preis;
    }

    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    public String getWaehrung() {
        return waehrung;
    }

    public void setWaehrung(String waehrung) {
        this.waehrung = waehrung;
    }

    public Serializable getBild() {
        return bild;
    }

    public void setBild(Serializable bild) {
        this.bild = bild;
    }

    public Set<Artikelgruppe> getArtikelgruppeSet() {
        return artikelgruppeSet;
    }

    public void setArtikelgruppeSet(Set<Artikelgruppe> artikelgruppeSet) {
        this.artikelgruppeSet = artikelgruppeSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (artikelId != null ? artikelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artikel)) {
            return false;
        }
        Artikel other = (Artikel) object;
        if ((this.artikelId == null && other.artikelId != null) || (this.artikelId != null && !this.artikelId.equals(other.artikelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.stammdaten.domain.lager.Artikel[ artikelId=" + artikelId + " ]";
    }

    public Collection<Lagerraumbestand> getLagerraumbestandCollection() {
        return lagerraumbestandCollection;
    }

    public void setLagerraumbestandCollection(Collection<Lagerraumbestand> lagerraumbestandCollection) {
        this.lagerraumbestandCollection = lagerraumbestandCollection;
    }

}
