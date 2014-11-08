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
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.kore.runtime.currency.Money;
import org.kore.runtime.specifications.Description;
import org.kore.runtime.specifications.Identifier;
import org.kore.stammdaten.lager.domain.lager.Vorrat;

/**
 *
 * @author Konrad Renner
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Artikel.findAll", query = "SELECT a FROM Artikel a"),
    @NamedQuery(name = "Artikel.findByBezeichnung", query = "SELECT a FROM Artikel a WHERE a.bezeichnung = :bezeichnung"),
    @NamedQuery(name = "Artikel.findByArtikelgruppeTyp", query = "SELECT a FROM Artikel a, Artikelgruppe g WHERE a = g.artikel AND g.typ = :typ"),
    @NamedQuery(name = "Artikel.findByArtikelgruppe", query = "SELECT a FROM Artikel a, Artikelgruppe g WHERE a = g.artikel AND g.bezeichnung = :bezeichnung")
})
public class Artikel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "artikel_id")
    private Integer artikelId;
    @NotNull
    @Embedded
    private Identifier bezeichnung;
    @Embedded
    private Description beschreibung;
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "PREIS"))
    @NotNull
    private Money preis;
    @Lob
    private byte[] bild;
    @Version
    private int version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artikel")
    private Collection<Vorrat> vorraete;
    @ManyToMany
    @JoinTable(name = "ARTIKEL_ARTIKELGRUPPE")
    private Collection<Artikelgruppe> artikelGruppen;

    protected Artikel() {
    }

    protected Artikel(Identifier bezeichnung, Description beschreibung, Money preis) {
        this.bezeichnung = bezeichnung;
        this.beschreibung = beschreibung;
        this.preis = preis;
        this.vorraete = new ArrayList<>();
        this.artikelGruppen = new ArrayList<>();
    }


    public int getArtikelId() {
        return artikelId;
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

    public Description getBeschreibung() {
        return beschreibung;
    }

    public Identifier getBezeichnung() {
        return bezeichnung;
    }

    public Money getPreis() {
        return preis;
    }

    public Collection<Vorrat> getArtikelLagerraumCollection() {
        return vorraete;
    }

    public Collection<Artikelgruppe> getArtikelGruppen() {
        return artikelGruppen;
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
        return "Artikel{" + "artikelId=" + artikelId + ", bezeichnung=" + bezeichnung + ", beschreibung=" + beschreibung + ", preis=" + preis + ", bild=" + bild + ", version=" + version + ", vorraete=" + vorraete + ", artikelGruppen=" + artikelGruppen + '}';
    }

}
