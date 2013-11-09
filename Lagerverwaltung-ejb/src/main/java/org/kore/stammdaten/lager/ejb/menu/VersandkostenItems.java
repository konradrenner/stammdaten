/*
 * Copyright (C) 2013 Konrad Renner.
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
package org.kore.stammdaten.lager.ejb.menu;

import org.kore.menu.api.Entry;
import org.kore.menu.api.EntryUIDFactory;
import org.kore.menu.api.Menu;

/**
 *
 * @author Konrad Renner
 */
public enum VersandkostenItems {

    SAVE_VERSANDKOSTEN,
    CANCEL_VERSANDKOSTEN,
    UEBERSICHT_VERSANDKOSTEN,
    DETAIL_VERSANDKOSTEN;

    public static Entry getEntry(VersandkostenItems item, EntryUIDFactory factory, Menu menu) {
        return menu.getEntry(VersandkostenMenu.VERSANDKOSTEN_NAMESPACE, factory.createUID(VersandkostenMenu.VERSANDKOSTEN_NAMESPACE, item.toString(), item.toString()));
    }
}
