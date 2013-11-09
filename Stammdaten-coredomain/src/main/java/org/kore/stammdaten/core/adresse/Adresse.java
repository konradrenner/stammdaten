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
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.kore.runtime.format.NumericFormatter;
import org.kore.runtime.format.StringFormatter;

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
@Table(name = "ADRESSE", schema = "STAMMDATEN")
@NamedQueries({
    @NamedQuery(name = Adresse.FIND_ALL, query = "SELECT a FROM Adresse a"),
    @NamedQuery(name = Adresse.SEARCH_BY_ADRESSZEILE1, query = "SELECT a FROM Adresse a WHERE a.adresszeile1 LIKE :adresszeile1")})
public class Adresse implements Serializable {

    public static final String FIND_ALL = "FIND_ALL_ADRESSEN";
    public static final String SEARCH_BY_ADRESSZEILE1 = "FIND_BY_ADRESSEZEILE1";
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @AttributeOverride(name = "value", column =
            @Column(name = "ADRESSID"))
    private AdresseID adressId;
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ORT")
    private String ort;
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STRASSE")
    private String strasse;
    @NotNull
    @Column(name = "POSTLEITZAHL")
    private int postleitzahl;
    @NotNull
    @Column(name = "HAUSNUMMER")
    private short hausnummer;
    @Column(name = "STIEGE")
    private Short stiege;
    @Column(name = "TUER")
    private Short tuer;
    
    @NotNull
    @Embedded
    @AttributeOverride(name = "iso3166Code", column = @Column(name = "LAND"))
    private Land land;
    
    @Size(min = 1, max = 100)
    @Column(name = "ADRESSZEILE1")
    private String adresszeile1;
    @Size(max = 100)
    @Column(name = "ADRESSZEILE2")
    private String adresszeile2;

    protected Adresse() {
        //JPA
    }

    protected Adresse(AdresseID adressid, String ort, String strasse, int postleitzahl, short hausnummer, Short stiege, Short tuer, Land land, String adresszeile1, String adresszeile2) {
        this.adressId = adressid;
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
        private final Land land;
        private final String adresszeile1;
        private String adresszeile2;

        public Builder(String ort, String strasse, int postleitzahl, short hausnummer, Land land, String adresszeile1) {

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

            StringBuilder adressId = new StringBuilder(land.getIso3166Code())
                    .append(new NumericFormatter(this.postleitzahl).withForerunZeros(4))
                    .append(new NumericFormatter(this.hausnummer).withForerunZeros(4))
                    .append(new NumericFormatter(this.stiege).withForerunZeros(3))
                    .append(new NumericFormatter(this.tuer).withForerunZeros(3))
                    .append(new StringFormatter(this.ort).appendBlanks(10))
                    .append(new StringFormatter(this.ort).appendBlanks(9));
            return new Adresse(new AdresseID(adressId.toString()), ort, strasse, postleitzahl, hausnummer, stiege, tuer, land, adresszeile1, adresszeile2);
        }
    }


    public AdresseID getAdressid() {
        return this.adressId;
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

    public Land getLand() {
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
        hash += (this.adressId != null ? this.adressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.adressId == null && other.adressId != null) || (this.adressId != null && !this.adressId.equals(other.adressId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Adresse{" + "adressid=" + adressId + ", ort=" + ort + ", strasse=" + strasse + ", postleitzahl=" + postleitzahl + ", hausnummer=" + hausnummer + ", stiege=" + stiege + ", tuer=" + tuer + ", land=" + land + ", adresszeile1=" + adresszeile1 + ", adresszeile2=" + adresszeile2 + '}';
    }
}
