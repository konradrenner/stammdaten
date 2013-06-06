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

package org.kore.stammdaten.domain.lager;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "LAGER")
@NamedQueries({
    @NamedQuery(name = "Lager.findAll", query = "SELECT l FROM Lager l"),
    @NamedQuery(name = "Lager.findByLagerId", query = "SELECT l FROM Lager l WHERE l.lagerId = :lagerId"),
    @NamedQuery(name = "Lager.findByAdressid", query = "SELECT l FROM Lager l WHERE l.adressid = :adressid"),
    @NamedQuery(name = "Lager.findByBezeichnung", query = "SELECT l FROM Lager l WHERE l.bezeichnung = :bezeichnung"),
    @NamedQuery(name = "Lager.findByBeschreibung", query = "SELECT l FROM Lager l WHERE l.beschreibung = :beschreibung"),
    @NamedQuery(name = "Lager.findByAdressidTelefon", query = "SELECT l FROM Lager l WHERE l.adressidTelefon = :adressidTelefon"),
    @NamedQuery(name = "Lager.findByAdressidFax", query = "SELECT l FROM Lager l WHERE l.adressidFax = :adressidFax"),
    @NamedQuery(name = "Lager.findByEmail", query = "SELECT l FROM Lager l WHERE l.email = :email")})
public class Lager implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @NotNull
    @Column(name = "LAGER_ID")
    private Short lagerId;
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "ADRESSID")
    private String adressid;
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BEZEICHNUNG")
    private String bezeichnung;
    @Size(max = 50)
    @Column(name = "BESCHREIBUNG")
    private String beschreibung;
    @Size(max = 35)
    @Column(name = "ADRESSID_TELEFON")
    private String adressidTelefon;
    @Size(max = 35)
    @Column(name = "ADRESSID_FAX")
    private String adressidFax;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 35)
    @Column(name = "EMAIL")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lagerId", fetch = FetchType.EAGER)
    private Set<Lagerraum> lagerraumSet;

    public Lager() {
    }

    public Lager(Short lagerId) {
        this.lagerId = lagerId;
    }

    public Lager(Short lagerId, String adressid, String bezeichnung) {
        this.lagerId = lagerId;
        this.adressid = adressid;
        this.bezeichnung = bezeichnung;
    }

    public Short getLagerId() {
        return lagerId;
    }

    public void setLagerId(Short lagerId) {
        this.lagerId = lagerId;
    }

    public String getAdressid() {
        return adressid;
    }

    public void setAdressid(String adressid) {
        this.adressid = adressid;
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

    public String getAdressidTelefon() {
        return adressidTelefon;
    }

    public void setAdressidTelefon(String adressidTelefon) {
        this.adressidTelefon = adressidTelefon;
    }

    public String getAdressidFax() {
        return adressidFax;
    }

    public void setAdressidFax(String adressidFax) {
        this.adressidFax = adressidFax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Lagerraum> getLagerraumSet() {
        return lagerraumSet;
    }

    public void setLagerraumSet(Set<Lagerraum> lagerraumSet) {
        this.lagerraumSet = lagerraumSet;
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
        return "org.kore.stammdaten.domain.lager.Lager[ lagerId=" + lagerId + " ]";
    }

}
