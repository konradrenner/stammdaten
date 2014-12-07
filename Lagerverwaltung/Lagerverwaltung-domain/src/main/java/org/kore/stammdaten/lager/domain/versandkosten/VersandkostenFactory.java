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
package org.kore.stammdaten.lager.domain.versandkosten;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.NotNull;
import org.kore.runtime.currency.Money;
import org.kore.stammdaten.core.adresse.Land;
import org.kore.stammdaten.lager.domain.versandkosten.validation.ConsistentMoneyCurrencies;

/**
 *
 * @author Konrad Renner
 */
@AggregateVersandkosten
@RequestScoped
public class VersandkostenFactory implements Serializable {

    public Versandkosten create(@NotNull Land land, @NotNull Money betrag) {
        return new Versandkosten(land, betrag);
    }

    @ConsistentMoneyCurrencies
    public Versandkosten create(@NotNull Land land, @NotNull Money betrag, @NotNull Money freibetrag) {
        Versandkosten kosten = new Versandkosten(land, betrag);
        kosten.setFreibetrag(freibetrag.getAmount());

        return kosten;
    }
}
