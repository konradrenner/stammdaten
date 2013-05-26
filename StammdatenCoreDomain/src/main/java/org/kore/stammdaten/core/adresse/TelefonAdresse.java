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

package org.kore.stammdaten.core.adresse;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
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
@Table(name = "TELEFON_ADRESSE")
@DiscriminatorColumn(name = "TYP")
@Inheritance
@NamedQueries({
    @NamedQuery(name = "TelefonAdresse.findAll", query = "SELECT t FROM TelefonAdresse t"),
    @NamedQuery(name = "TelefonAdresse.findByAdressid", query = "SELECT t FROM TelefonAdresse t WHERE t.adressid = :adressid"),
    @NamedQuery(name = "TelefonAdresse.findByBezeichnung", query = "SELECT t FROM TelefonAdresse t WHERE t.bezeichnung = :bezeichnung"),
    @NamedQuery(name = "TelefonAdresse.findByTyp", query = "SELECT t FROM TelefonAdresse t WHERE t.typ = :typ"),
    @NamedQuery(name = "TelefonAdresse.findByVorwahlLand", query = "SELECT t FROM TelefonAdresse t WHERE t.vorwahlLand = :vorwahlLand"),
    @NamedQuery(name = "TelefonAdresse.findByVorwahl", query = "SELECT t FROM TelefonAdresse t WHERE t.vorwahl = :vorwahl"),
    @NamedQuery(name = "TelefonAdresse.findByNummer", query = "SELECT t FROM TelefonAdresse t WHERE t.nummer = :nummer"),
    @NamedQuery(name = "TelefonAdresse.findByDurchwahl", query = "SELECT t FROM TelefonAdresse t WHERE t.durchwahl = :durchwahl")})
public abstract class TelefonAdresse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "ADRESSID")
    private String adressid;
    @Size(max = 50)
    @Column(name = "BEZEICHNUNG")
    private String bezeichnung;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VORWAHL_LAND")
    private short vorwahlLand;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VORWAHL")
    private short vorwahl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMMER")
    private short nummer;
    @Column(name = "DURCHWAHL")
    private Short durchwahl;

    protected TelefonAdresse() {
        //JPA
    }

    protected TelefonAdresse(String adressid, String bezeichnung, short vorwahlLand, short vorwahl, short nummer, Short durchwahl) {
        this.adressid = adressid;
        this.bezeichnung = bezeichnung;
        this.vorwahlLand = vorwahlLand;
        this.vorwahl = vorwahl;
        this.nummer = nummer;
        this.durchwahl = durchwahl;
    }

    public String getAdressid() {
        return adressid;
    }


    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }



    public short getVorwahlLand() {
        return vorwahlLand;
    }


    public short getVorwahl() {
        return vorwahl;
    }

    public short getNummer() {
        return nummer;
    }


    public Short getDurchwahl() {
        return durchwahl;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adressid != null ? adressid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TelefonAdresse)) {
            return false;
        }
        TelefonAdresse other = (TelefonAdresse) object;
        if ((this.adressid == null && other.adressid != null) || (this.adressid != null && !this.adressid.equals(other.adressid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + "adressid=" + adressid + ", bezeichnung=" + bezeichnung + ", vorwahlLand=" + vorwahlLand + ", vorwahl=" + vorwahl + ", nummer=" + nummer + ", durchwahl=" + durchwahl + '}';
    }
}
