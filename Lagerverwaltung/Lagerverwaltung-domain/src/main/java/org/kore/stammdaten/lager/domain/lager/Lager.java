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
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
    @NamedQuery(name = "Lager.findAll", query = "SELECT l FROM Lager l"),
    @NamedQuery(name = "Lager.findByLagerId", query = "SELECT l FROM Lager l WHERE l.lagerId = :lagerId"),
    @NamedQuery(name = "Lager.findByAdressid", query = "SELECT l FROM Lager l WHERE l.adressid = :adressid"),
    @NamedQuery(name = "Lager.findByBezeichnung", query = "SELECT l FROM Lager l WHERE l.bezeichnung = :bezeichnung"),
    @NamedQuery(name = "Lager.findByBeschreibung", query = "SELECT l FROM Lager l WHERE l.beschreibung = :beschreibung"),
    @NamedQuery(name = "Lager.findByAdressidTelefon", query = "SELECT l FROM Lager l WHERE l.adressidTelefon = :adressidTelefon"),
    @NamedQuery(name = "Lager.findByAdressidFax", query = "SELECT l FROM Lager l WHERE l.adressidFax = :adressidFax"),
    @NamedQuery(name = "Lager.findByEmail", query = "SELECT l FROM Lager l WHERE l.email = :email"),
    @NamedQuery(name = "Lager.findByVersion", query = "SELECT l FROM Lager l WHERE l.version = :version")})
public class Lager implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "lager_id")
    private Short lagerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    private String adressid;
    @Basic(optional = false)
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
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 35)
    private String email;
    @Basic(optional = false)
    @NotNull
    private int version;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lager")
    private Collection<Lagerraum> lagerraumCollection;

    public Lager() {
    }

    public Lager(String adressid, Identifier bezeichnung, Description beschreibung, String adressidTelefon, String adressidFax, String email) {
        this.adressid = adressid;
        this.bezeichnung = bezeichnung;
        this.beschreibung = beschreibung;
        this.adressidTelefon = adressidTelefon;
        this.adressidFax = adressidFax;
        this.email = email;
        this.lagerraumCollection = new ArrayList<>();
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

    public String getEmail() {
        return email;
    }

    public int getVersion() {
        return version;
    }

    public Collection<Lagerraum> getLagerraumCollection() {
        return lagerraumCollection;
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
