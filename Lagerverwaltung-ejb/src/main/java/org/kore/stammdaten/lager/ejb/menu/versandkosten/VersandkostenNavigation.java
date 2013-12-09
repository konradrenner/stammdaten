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
package org.kore.stammdaten.lager.ejb.menu.versandkosten;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.kore.stammdaten.lager.ejb.menu.LagerverwaltungEntryUIDFactory;
import org.kore.stammdaten.lager.ejb.menu.LagerverwaltungMenu;

/**
 *
 * @author Konrad Renner
 */
@RequestScoped
@Named
public class VersandkostenNavigation {

    @Inject
    LagerverwaltungMenu menu;
    @Inject
    LagerverwaltungEntryUIDFactory uidFactory;

    public String getUebersicht() {
        return menu.getEntry(uidFactory.createUID(VersandkostenEntries.UEBERSICHT.getUIDString())).getNavigationPath().asString();
    }

    public String getDetail() {
        return menu.getEntry(uidFactory.createUID(VersandkostenEntries.DETAIL.getUIDString())).getNavigationPath().asString();
    }
}
