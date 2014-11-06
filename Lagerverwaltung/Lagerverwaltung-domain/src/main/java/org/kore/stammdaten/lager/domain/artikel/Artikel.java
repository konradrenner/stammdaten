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
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.kore.stammdaten.lager.domain.lager.Vorrat;

/**
 *
 * @author Konrad Renner
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Artikel.findAll", query = "SELECT a FROM Artikel a"),
    @NamedQuery(name = "Artikel.findByArtikelId", query = "SELECT a FROM Artikel a WHERE a.artikelId = :artikelId"),
    @NamedQuery(name = "Artikel.findByBezeichnung", query = "SELECT a FROM Artikel a WHERE a.bezeichnung = :bezeichnung"),
    @NamedQuery(name = "Artikel.findByBeschreibung", query = "SELECT a FROM Artikel a WHERE a.beschreibung = :beschreibung"),
    @NamedQuery(name = "Artikel.findByPreis", query = "SELECT a FROM Artikel a WHERE a.preis = :preis"),
    @NamedQuery(name = "Artikel.findByWaehrung", query = "SELECT a FROM Artikel a WHERE a.waehrung = :waehrung"),
    @NamedQuery(name = "Artikel.findByVersion", query = "SELECT a FROM Artikel a WHERE a.version = :version")})
public class Artikel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "artikel_id")
    private Integer artikelId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String bezeichnung;
    @Size(max = 300)
    private String beschreibung;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    private BigDecimal preis;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    private String waehrung;
    @Lob
    private byte[] bild;
    @Basic(optional = false)
    @NotNull
    private int version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artikel")
    private Collection<Vorrat> artikelLagerraumCollection;
    @ManyToMany()
    @JoinTable(name = "ARTIKEL_ARTIKELGRUPPE")
    private Collection<Artikelgruppe> artikelGruppen;

    public Artikel() {
    }

    public Artikel(Integer artikelId) {
        this.artikelId = artikelId;
    }

    public Artikel(Integer artikelId, String bezeichnung, BigDecimal preis, String waehrung, int version) {
        this.artikelId = artikelId;
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.waehrung = waehrung;
        this.version = version;
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

    public byte[] getBild() {
        return bild;
    }

    public void setBild(byte[] bild) {
        this.bild = bild;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Collection<Vorrat> getArtikelLagerraumCollection() {
        return artikelLagerraumCollection;
    }

    public void setArtikelLagerraumCollection(Collection<Vorrat> artikelLagerraumCollection) {
        this.artikelLagerraumCollection = artikelLagerraumCollection;
    }

    public Collection<Artikelgruppe> getArtikelGruppen() {
        return artikelGruppen;
    }

    public void setArtikelGruppen(Collection<Artikelgruppe> artikelGruppen) {
        this.artikelGruppen = artikelGruppen;
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
        return "org.kore.stammdaten.lager.domain.lager.Artikel[ artikelId=" + artikelId + " ]";
    }

}
