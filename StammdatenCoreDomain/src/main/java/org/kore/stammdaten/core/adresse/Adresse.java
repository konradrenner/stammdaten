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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.kore.runtime.format.NumericFormatter;
import org.kore.runtime.format.StringFormatter;
import org.kore.runtime.validation.ConstructorValidator;

/**
 * Adresse
 *
 * Die AdressID der Adresse ist eine generierte Id und ist wie folgt
 * aufgebaut:
 *
 * 2 Stellen Laendercode, 4 Stellen Postleitzahl, 4 Stellen Hausnummer, 3
 * Stellen * Stiege, 3 Stellen Tuer, 10 Stellen Ort, 9 Stellen Strasse
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "ADRESSE")
@NamedQueries({
    @NamedQuery(name = "Adresse.findAll", query = "SELECT a FROM Adresse a"),
    @NamedQuery(name = "Adresse.findByOrt", query = "SELECT a FROM Adresse a WHERE a.ort = :ort"),
    @NamedQuery(name = "Adresse.findByStrasse", query = "SELECT a FROM Adresse a WHERE a.strasse = :strasse"),
    @NamedQuery(name = "Adresse.findByPostleitzahl", query = "SELECT a FROM Adresse a WHERE a.postleitzahl = :postleitzahl"),
    @NamedQuery(name = "Adresse.findByHausnummer", query = "SELECT a FROM Adresse a WHERE a.hausnummer = :hausnummer"),
    @NamedQuery(name = "Adresse.findByStiege", query = "SELECT a FROM Adresse a WHERE a.stiege = :stiege"),
    @NamedQuery(name = "Adresse.findByTuer", query = "SELECT a FROM Adresse a WHERE a.tuer = :tuer"),
    @NamedQuery(name = "Adresse.findByLand", query = "SELECT a FROM Adresse a WHERE a.land = :land"),
    @NamedQuery(name = "Adresse.findByAdresszeile1", query = "SELECT a FROM Adresse a WHERE a.adresszeile1 = :adresszeile1"),
    @NamedQuery(name = "Adresse.findByAdresszeile2", query = "SELECT a FROM Adresse a WHERE a.adresszeile2 = :adresszeile2")})
public class Adresse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "ADRESSID")
    private String adressid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ORT")
    private String ort;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STRASSE")
    private String strasse;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POSTLEITZAHL")
    private int postleitzahl;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HAUSNUMMER")
    private short hausnummer;
    @Column(name = "STIEGE")
    private Short stiege;
    @Column(name = "TUER")
    private Short tuer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "LAND")
    private String land;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ADRESSZEILE1")
    private String adresszeile1;
    @Size(max = 100)
    @Column(name = "ADRESSZEILE2")
    private String adresszeile2;

    protected Adresse() {
        //JPA
    }

    protected Adresse(String adressid, String ort, String strasse, int postleitzahl, short hausnummer, Short stiege, Short tuer, String land, String adresszeile1, String adresszeile2) {
        this.adressid = adressid;
        this.ort = ort;
        this.strasse = strasse;
        this.postleitzahl = postleitzahl;
        this.hausnummer = hausnummer;
        this.stiege = stiege;
        this.tuer = tuer;
        this.land = land;
        this.adresszeile1 = adresszeile1;
        this.adresszeile2 = adresszeile2;
    }


    public static class Builder {

        private final String ort;
        private final String strasse;
        private final int postleitzahl;
        private final short hausnummer;
        private Short stiege;
        private Short tuer;
        private final String land;
        private final String adresszeile1;
        private String adresszeile2;

        public Builder(String ort, String strasse, int postleitzahl, short hausnummer, String land, String adresszeile1) {

            if (new ConstructorValidator(ort, strasse, land, adresszeile1).nullCheckFails()) {
                throw new IllegalArgumentException("Es duerfen keine NULL-Values dem Konstruktor uebergeben werden");
            }

            this.ort = ort;
            this.strasse = strasse;
            this.postleitzahl = postleitzahl;
            this.hausnummer = hausnummer;
            this.land = land;
            this.adresszeile1 = adresszeile1;
        }

        public Builder stiege(Short var) {
            this.stiege = var;
            return this;
        }

        public Builder tuer(Short var) {
            this.tuer = var;
            return this;
        }

        public Builder adresszeile2(String var) {
            this.adresszeile2 = var;
            return this;
        }

        /**
         * build
         *
         * Die AdressID der Adresse wird generiert und ist wie folgt aufgebaut:
         *
         * 2 Stellen Laendercode, 4 Stellen Postleitzahl, 4 Stellen Hausnummer,
         * 3 Stellen Stiege, 3 Stellen Tuer, 10 Stellen Ort, 9 Stellen Strasse
         *
         * @author Konrad Renner
         */
        public Adresse build() {

            StringBuilder adressId = new StringBuilder(land)
                    .append(new NumericFormatter(this.postleitzahl).withForerunZeros(4))
                    .append(new NumericFormatter(this.hausnummer).withForerunZeros(4))
                    .append(new NumericFormatter(this.stiege).withForerunZeros(3))
                    .append(new NumericFormatter(this.tuer).withForerunZeros(3))
                    .append(new StringFormatter(this.ort).appendBlanks(10))
                    .append(new StringFormatter(this.ort).appendBlanks(9));
            return new Adresse(adressId.toString(), ort, strasse, postleitzahl, hausnummer, stiege, tuer, land, adresszeile1, adresszeile2);
        }
    }


    public String getAdressid() {
        return adressid;
    }


    public String getOrt() {
        return ort;
    }


    public String getStrasse() {
        return strasse;
    }


    public int getPostleitzahl() {
        return postleitzahl;
    }


    public short getHausnummer() {
        return hausnummer;
    }


    public Short getStiege() {
        return stiege;
    }

    public Short getTuer() {
        return tuer;
    }

    public String getLand() {
        return land;
    }

    public String getAdresszeile1() {
        return adresszeile1;
    }


    public String getAdresszeile2() {
        return adresszeile2;
    }

    public void setAdresszeile1(String adresszeile1) {
        this.adresszeile1 = adresszeile1;
    }

    public void setAdresszeile2(String adresszeile2) {
        this.adresszeile2 = adresszeile2;
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
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.adressid == null && other.adressid != null) || (this.adressid != null && !this.adressid.equals(other.adressid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Adresse{" + "adressid=" + adressid + ", ort=" + ort + ", strasse=" + strasse + ", postleitzahl=" + postleitzahl + ", hausnummer=" + hausnummer + ", stiege=" + stiege + ", tuer=" + tuer + ", land=" + land + ", adresszeile1=" + adresszeile1 + ", adresszeile2=" + adresszeile2 + '}';
    }
}
