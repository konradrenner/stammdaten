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
package org.kore.stammdaten.lager.adapter;

import org.kore.runtime.currency.Money;
import org.kore.stammdaten.core.adresse.Land;

/**
 *
 * @author Konrad Renner
 * @param <T>
 */
public interface VersandkostenAdapterBuilder<T extends VersandkostenAdapter> {

    public Properties<T> newInstance(Land value, Money betrag);

    interface Properties<T extends VersandkostenAdapter> {

        default Properties<T> freibetrag(Money value) {
            return this;
        }

        T build();
    }
}
