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

import java.text.MessageFormat;
import java.util.HashMap;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.kore.menu.api.DefaultEntryGroup;
import org.kore.menu.api.Entry;
import org.kore.menu.api.EntryGroup;
import org.kore.menu.api.EntryUID;
import org.kore.menu.api.Menu;
import org.kore.menu.api.Namespace;
import org.kore.menu.ri.EntryImpl;
import org.kore.menu.ri.NamespaceImpl;
import org.kore.stammdaten.lager.ejb.menu.versandkosten.VersandkostenEntries;

/**
 *
 * @author Konrad Renner
 */
@ApplicationScoped
public class LagerverwaltungMenu implements Menu {

    @Inject
    LagerverwaltungEntryUIDFactory uidFactory;
    private Namespace namespace;
    private EntryGroup mainGroup;

    public void init() {
        if (namespace == null) {
            namespace = new NamespaceImpl("LAGERVERWALTUNG");

            HashMap<EntryUID, Entry> entries = new HashMap<>();
            EntryUID createUID = uidFactory.createUID("VERSANDKOSTEN", "1");

            mainGroup = new DefaultEntryGroup.Builder(createUID, entries).addDisplayKey("versandkosten").build();
        }
    }

    private Entry createVersandkostenItems(Entry parent) {
        EntryUID uidUebersicht = uidFactory.createUID(VersandkostenEntries.UEBERSICHT.getUIDString(), "2");
        EntryUID uidDetail = uidFactory.createUID(VersandkostenEntries.DETAIL.getUIDString(), "4");

        Entry detail = new EntryImpl.Builder(uidDetail).displayKey("versandkosten.detail").path("versandkostenDetail").build();

        HashMap<EntryUID, Entry> group = new HashMap<>(1);
        group.put(uidDetail, detail);
        EntryGroup versandkostenDetails = new DefaultEntryGroup.Builder(uidFactory.createUID("VERSANDKOSTEN_DETAILS", "3"), group).build();

        Entry uebersicht = new EntryImpl.Builder(uidUebersicht).children(versandkostenDetails).displayKey("versandkosten.uebersicht").path("versandkostenUebersicht").build();

        return uebersicht;
    }

    @Override
    public EntryGroup getMainGroup(Namespace nmspc) {
        //TODO implement remote namespaces
        return mainGroup;
    }

    @Override
    public EntryGroup getGroup(Namespace nmspc, EntryUID euid) {
        //TODO implement remote namespaces
        Entry entry = getEntry(nmspc, euid);

        if (Entry.Type.GROUP.equals(entry.getType())) {
            return (EntryGroup) entry;
        }
        throw new IllegalStateException(MessageFormat.format("Entry with UID {0} in Namespace {1} is not Group", euid, nmspc));
    }

    @Override
    public Entry getEntry(Namespace nmspc, EntryUID euid) {
        //TODO implement remote namespaces
        return mainGroup.getEntry(euid);
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
