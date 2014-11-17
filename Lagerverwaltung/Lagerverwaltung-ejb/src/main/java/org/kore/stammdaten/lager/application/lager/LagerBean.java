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
package org.kore.stammdaten.lager.application.lager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import org.kore.stammdaten.lager.adapter.LagerAdapter;
import org.kore.stammdaten.lager.adapter.LagerAdapterBuilder;
import org.kore.stammdaten.lager.domain.lager.AggregateLager;
import org.kore.stammdaten.lager.domain.lager.Lager;
import org.kore.stammdaten.lager.domain.lager.LagerRepository;

/**
 *
 * @author Konrad Renner
 */
@Stateful
@ConversationScoped
@LocalBean
public class LagerBean {

    @AggregateLager
    @Inject
    LagerRepository repository;

    public <T extends LagerAdapter> Collection<T> getAll(LagerAdapterBuilder<T> builder) {
        List<Lager> findAll = repository.findAll();

        ArrayList<T> ret = new ArrayList<>(findAll.size());

        for (Lager lager : findAll) {
            LagerAdapterBuilder.Properties<T> build = builder.newInstance(lager.getLagerId(), lager.getBezeichnung());

            if (lager.getEmail() != null) {
                build.email(lager.getEmail());
            }

            if (lager.getBeschreibung() != null) {
                build.beschreibung(lager.getBeschreibung());
            }

            ret.add(build.build());
        }

        return ret;
    }

    public <T extends LagerAdapter> T getLager(LagerAdapterBuilder<T> builder, Short lagerid) {
        Lager lager = repository.find(lagerid);

        LagerAdapterBuilder.Properties<T> build = builder.newInstance(lager.getLagerId(), lager.getBezeichnung());

        if (lager.getEmail() != null) {
            build.email(lager.getEmail());
        }

        if (lager.getBeschreibung() != null) {
            build.beschreibung(lager.getBeschreibung());
        }

        return build.build();
    }
}
