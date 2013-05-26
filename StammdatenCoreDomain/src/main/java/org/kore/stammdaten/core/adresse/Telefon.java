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

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Konrad Renner
 */
@Entity
@DiscriminatorValue("TELEFON")
public class Telefon extends TelefonAdresse {

    protected Telefon() {
        super();
    }

    protected Telefon(String adressid, String bezeichnung, short vorwahlLand, short vorwahl, short nummer, Short durchwahl) {
        super(adressid, bezeichnung, vorwahlLand, vorwahl, nummer, durchwahl);
    }

    public static class Builder {

        private String bezeichnung;
        private final short vorwahlLand;
        private final short vorwahl;
        private final short nummer;
        private Short durchwahl;

        public Builder(short vorwahlLand, short nummer, short vorwahl) {
            this.vorwahlLand = vorwahlLand;
            this.vorwahl = vorwahl;
            this.nummer = nummer;

        }

        public Builder bezeichnung(String value) {
            this.bezeichnung = value;
            return this;
        }

        public Builder durchwahl(short value) {
            this.durchwahl = Short.valueOf(value);
            return this;
        }

        public Fax build() {
            String adressId = vorwahlLand + vorwahl + nummer + (durchwahl == null ? "" : durchwahl.toString());
            return new Fax(adressId, bezeichnung, vorwahlLand, vorwahl, nummer, durchwahl);
        }
    }

    public String toString() {
        return "Telefon" + super.toString();
    }
}
