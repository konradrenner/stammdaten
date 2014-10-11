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
package org.kore.stammdaten.lager.rest;

import org.kore.runtime.currency.Money;
import org.kore.stammdaten.lager.adapter.VersandkostenAdapter;
import org.kore.stammdaten.lager.adapter.VersandkostenAdapterFactory;

/**
 *
 * @author Konrad Renner
 */
public class VersandkostenDTO implements VersandkostenAdapter, VersandkostenAdapterFactory.AdapterBuilder<VersandkostenDTO> {

    private String land;
    private Money betrag;
    private Money freibetrag;

    @Override
    public String getLand() {
        return land;
    }

    @Override
    public VersandkostenAdapterFactory.AdapterBuilder<VersandkostenDTO> land(String land) {
        this.land = land;
        return this;
    }

    @Override
    public Money getBetrag() {
        return betrag;
    }

    @Override
    public VersandkostenAdapterFactory.AdapterBuilder<VersandkostenDTO> betrag(Money betrag) {
        this.betrag = betrag;
        return this;
    }

    @Override
    public Money getFreibetrag() {
        return freibetrag;
    }

    @Override
    public VersandkostenAdapterFactory.AdapterBuilder<VersandkostenDTO> freibetrag(Money freibetrag) {
        this.freibetrag = freibetrag;
        return this;
    }

    @Override
    public String toString() {
        return "VersandkostenDTO{" + "land=" + land + ", betrag=" + betrag + ", freibetrag=" + freibetrag + '}';
    }

    @Override
    public VersandkostenDTO build() {
        return this;
    }

}
