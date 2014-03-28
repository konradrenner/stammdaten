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
package org.kore.stammdaten.lager.dto.versandkosten;

import java.math.BigDecimal;

/**
 *
 * @author Konrad Renner
 */
public class VersandkostenDTO {

    private String land;
    private BigDecimal betrag;
    private String waehrung;
    private BigDecimal freibetrag;
    private String waehrungFreibetrag;

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public BigDecimal getBetrag() {
        return betrag;
    }

    public void setBetrag(BigDecimal betrag) {
        this.betrag = betrag;
    }

    public String getWaehrung() {
        return waehrung;
    }

    public void setWaehrung(String waehrung) {
        this.waehrung = waehrung;
    }

    public BigDecimal getFreibetrag() {
        return freibetrag;
    }

    public void setFreibetrag(BigDecimal freibetrag) {
        this.freibetrag = freibetrag;
    }

    public String getWaehrungFreibetrag() {
        return waehrungFreibetrag;
    }

    public void setWaehrungFreibetrag(String waehrungFreibetrag) {
        this.waehrungFreibetrag = waehrungFreibetrag;
    }

    @Override
    public String toString() {
        return "VersandkostenDTO{" + "land=" + land + ", betrag=" + betrag + ", waehrung=" + waehrung + ", freibetrag=" + freibetrag + ", waehrungFreibetrag=" + waehrungFreibetrag + '}';
    }
}
