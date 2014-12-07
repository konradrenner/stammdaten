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
import java.util.Collection;
import java.util.Map;
import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.kore.runtime.specifications.Description;
import org.kore.runtime.specifications.Identifier;
import org.kore.stammdaten.core.adresse.EMail;

/**
 *
 * @author Konrad Renner
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Lager.findAll", query = "SELECT l FROM Lager l"),
    @NamedQuery(name = "Lager.findByArtikel", query = "SELECT l FROM Lager l, Vorrat v WHERE v.vorratKey.artikelId = :artikelId AND l.lagerId = v.vorratKey.lagerId ORDER BY l.lagerId")})
public class Lager implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "lager_id")
    private Short lagerId;
    @Column
    @NotNull
    @Size(min = 1, max = 35)
    private String adressid;
    @NotNull
    @Embedded
    private Identifier bezeichnung;
    @Embedded
    private Description beschreibung;
    @Size(max = 35)
    @Column(name = "adressid_telefon")
    private String adressidTelefon;
    @Size(max = 35)
    @Column(name = "adressid_fax")
    private String adressidFax;
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "email", nullable = false))
    private EMail email;
    @Version
    private int version;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lager")
    @MapKeyColumn(name = "raum_id")
    private Map<Short, Lagerraum> lagerraeume;

    public Lager() {
    }

    public Lager(String adressid, Identifier bezeichnung, Description beschreibung, String adressidTelefon, String adressidFax, EMail email) {
        this.adressid = adressid;
        this.bezeichnung = bezeichnung;
        this.beschreibung = beschreibung;
        this.adressidTelefon = adressidTelefon;
        this.adressidFax = adressidFax;
        this.email = email;
    }

    public Short getLagerId() {
        return lagerId;
    }

    public String getAdressid() {
        return adressid;
    }

    public String getAdressidTelefon() {
        return adressidTelefon;
    }

    public String getAdressidFax() {
        return adressidFax;
    }

    public EMail getEmail() {
        return email;
    }

    public int getVersion() {
        return version;
    }

    public Collection<Lagerraum> getLagerraeume() {
        return lagerraeume.values();
    }

    public Lagerraum getLagerraum(short raumid) {
        return lagerraeume.get(raumid);
    }

    public Identifier getBezeichnung() {
        return bezeichnung;
    }

    public Description getBeschreibung() {
        return beschreibung;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lagerId != null ? lagerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lager)) {
            return false;
        }
        Lager other = (Lager) object;
        if ((this.lagerId == null && other.lagerId != null) || (this.lagerId != null && !this.lagerId.equals(other.lagerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.kore.stammdaten.lager.domain.lager.Lager[ lagerId=" + lagerId + " ]";
    }

}
