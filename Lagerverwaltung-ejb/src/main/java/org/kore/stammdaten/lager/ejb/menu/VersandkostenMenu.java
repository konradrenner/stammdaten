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

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import org.kore.menu.api.Entry;
import org.kore.menu.api.EntryGroup;
import org.kore.menu.api.EntryUID;
import org.kore.menu.api.Menu;
import org.kore.menu.api.Namespace;
import org.kore.menu.ri.NamespaceImpl;

/**
 *
 * @author Konrad Renner
 */
@ApplicationScoped
public class VersandkostenMenu implements Menu {

    private Namespace namespace;

    @PostConstruct
    void init() {
        namespace = new NamespaceImpl("VERSANDKOSTEN");
    }

    @Override
    public EntryGroup getMainGroup(Namespace nmspc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntryGroup getGroup(Namespace nmspc, EntryUID euid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entry getEntry(Namespace nmspc, EntryUID euid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EntryGroup getMainGroup() {
        return getMainGroup(getCurrentNamespace());
    }

    @Override
    public EntryGroup getGroup(EntryUID euid) {
        return getGroup(getCurrentNamespace(), euid);
    }

    @Override
    public Entry getEntry(EntryUID euid) {
        return getEntry(getCurrentNamespace(), euid);
    }


    @Override
    public Namespace getCurrentNamespace() {
        return this.namespace;
    }
}
