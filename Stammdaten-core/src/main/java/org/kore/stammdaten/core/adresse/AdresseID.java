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

import java.util.Formatter;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import org.kore.runtime.base.Scalar;

/**
 *
 * @author Konrad Renner
 */
@Embeddable
public class AdresseID extends Scalar<String> {

    private String value;

    protected AdresseID() {
        //JPA
    }

    protected AdresseID(@NotNull(message = "org.kore.stammdaten.core.messages.notnull.AdressID") String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    public static class Builder {

        private final String ort;
        private final String strasse;
        private final int postleitzahl;
        private final short hausnummer;
        private Short stiege;
        private Short tuer;
        private final Land land;

        public Builder(Land land, String ort, String strasse, int postleitzahl, short hausnummer) {
            Objects.requireNonNull(land, "land must not be null");
            Objects.requireNonNull(ort, "ort must not be null");
            Objects.requireNonNull(strasse, "strasse must not be null");

            this.ort = ort;
            this.strasse = strasse;
            this.postleitzahl = postleitzahl;
            this.hausnummer = hausnummer;
            this.land = land;
        }

        public Builder stiege(Short var) {
            Objects.requireNonNull(var, "stiege must not be null");
            this.stiege = var;
            return this;
        }

        public Builder tuer(Short var) {
            Objects.requireNonNull(var, "tuer must not be null");
            this.tuer = var;
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
         * @return Adresse
         */
        public AdresseID build() {

            short stiegeNonNull = this.stiege == null ? 0 : this.stiege;
            short tuerNonNull = this.tuer == null ? 0 : this.tuer;
            String strasseCorrectLength = this.strasse.length() > 9 ? this.strasse.substring(0, 9) : this.strasse;
            String ortCorrectLength = this.ort.length() > 9 ? this.ort.substring(0, 9) : this.ort;

            Formatter formattedID = new Formatter(new StringBuilder(land.getValue()));
            formattedID.format("%04d", this.postleitzahl)
                    .format("%04d", this.hausnummer)
                    .format("%03d", stiegeNonNull)
                    .format("%03d", tuerNonNull)
                    .format("%-10s", ortCorrectLength)
                    .format("%-9s", strasseCorrectLength);

            return new AdresseID(formattedID.toString());
        }
    }
}
