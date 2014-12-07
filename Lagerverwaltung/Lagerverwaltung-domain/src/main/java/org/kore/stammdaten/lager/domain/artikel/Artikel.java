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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.kore.runtime.currency.Money;
import org.kore.runtime.specifications.Description;
import org.kore.runtime.specifications.Identifier;

/**
 *
 * @author Konrad Renner
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Artikel.findAll", query = "SELECT a FROM Artikel a"),
    @NamedQuery(name = "Artikel.findByBezeichnung", query = "SELECT a FROM Artikel a WHERE a.bezeichnung = :bezeichnung")
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
    @ManyToMany
    @JoinTable(name = "ARTIKEL_ARTIKELGRUPPE",
            joinColumns = {
                @JoinColumn(name = "artikel_id", referencedColumnName = "artikel_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "bezeichnung", referencedColumnName = "bezeichnung")})
    private Collection<Artikelgruppe> artikelGruppen;

    @Transient
    private ArtikelId id;

    protected Artikel() {
    }

    protected Artikel(ArtikelId id, Identifier bezeichnung, Money preis) {
        //TODO bei einer neuen entity eventuell auch artikelId setzen
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.preis = preis;
        this.artikelGruppen = new ArrayList<>();
    }

    @PostLoad
    protected void initId() {
        this.id = new ArtikelId(artikelId);
    }


    public ArtikelId getArtikelId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public byte[] getBild() {
        return bild;
    }

    public void setBild(byte[] bild) {
        this.bild = bild;
    }

    public Optional<Description> getBeschreibung() {
        return Optional.ofNullable(beschreibung);
    }

    public void setBeschreibung(Description beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Identifier getBezeichnung() {
        return bezeichnung;
    }

    public Money getPreis() {
        return preis;
    }

    public Collection<Artikelgruppe> getArtikelGruppen() {
        return Collections.unmodifiableCollection(artikelGruppen);
    }

    public void addArtikelGruppen(Artikelgruppe... gruppen) {
        this.artikelGruppen.addAll(Arrays.asList(gruppen));
    }

    public void removeArtikelGruppen(Identifier... gruppenBezeichnung) {
        //TODO Java 8 Streams benutzen
        for (Artikelgruppe gruppe : this.artikelGruppen) {
            for (Identifier bez : gruppenBezeichnung) {
                if (gruppe.getBezeichnung().equals(bez)) {
                    this.artikelGruppen.remove(gruppe);
                }
            }
        }
    }

    public void removeArtikelGruppen(Artikelgruppe.Typ... typen) {
        //TODO Java 8 Streams benutzen
        for (Artikelgruppe gruppe : this.artikelGruppen) {
            for (Artikelgruppe.Typ typ : typen) {
                if (gruppe.getTyp().equals(typ)) {
                    this.artikelGruppen.remove(gruppe);
                }
            }
        }
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
        return "Artikel{" + "artikelId=" + artikelId + ", bezeichnung=" + bezeichnung + ", beschreibung=" + beschreibung + ", preis=" + preis + ", bild=" + bild + ", version=" + version + ", artikelGruppen=" + artikelGruppen + '}';
    }

}
