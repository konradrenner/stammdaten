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
package org.kore.stammdaten.lager.model.versandkosten;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.kore.runtime.currency.Money;
import org.kore.stammdaten.core.adresse.Land;
import org.kore.stammdaten.lager.adapter.VersandkostenAdapter;
import org.kore.stammdaten.lager.adapter.VersandkostenAdapterBuilder;

/**
 *
 * @author Konrad Renner
 */
public class VersandkostenModel implements VersandkostenAdapter, VersandkostenAdapterBuilder.Properties<VersandkostenModel> {

    @NotNull
    private String landforView;
    @NotNull
    @Min(0)
    @Max(999999)
    private BigDecimal betragForView;
    @Min(1)
    @Max(999999)
    private BigDecimal freibetragForView;

    @NotNull
    private String waehrungForView;

    @Override
    public Land getLand() {
        return new Land(landforView);
    }

    public String getWaehrungForView() {
        return waehrungForView;
    }

    public void setWaehrungForView(String waehrungForView) {
        this.waehrungForView = waehrungForView;
    }

    public String getLandforView() {
        return landforView;
    }

    public void setLandforView(String landforView) {
        this.landforView = landforView;
    }

    public BigDecimal getBetragForView() {
        return betragForView;
    }

    public void setBetragForView(BigDecimal betragForView) {
        this.betragForView = betragForView;
    }

    public BigDecimal getFreibetragForView() {
        return freibetragForView;
    }

    public void setFreibetragForView(BigDecimal freibetragForView) {
        this.freibetragForView = freibetragForView;
    }

    @Override
    public Money getBetrag() {
        return new Money(betragForView, Currency.getInstance(this.waehrungForView));
    }

    @Override
    public Optional<Money> getFreibetrag() {
        if (this.freibetragForView == null) {
            return null;
        }
        return Optional.of(new Money(freibetragForView, Currency.getInstance(this.waehrungForView)));
    }

    @Override
    public VersandkostenAdapterBuilder.Properties<VersandkostenModel> freibetrag(Money freibetrag) {
        if (freibetrag != null) {
            setFreibetragForView(freibetrag.getAmount());
        }
        
        return this;
    }

    @Override
    public String toString() {
        return "VersandkostenModel{" + "landforView=" + landforView + ", betragForView=" + betragForView + ", freibetragForView=" + freibetragForView + ", waehrungForView=" + waehrungForView + '}';
    }

    @Override
    public VersandkostenModel build() {
        return this;
    }

}
