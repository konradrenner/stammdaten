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
package org.kore.stammdaten.lager.model.lager;

import java.util.ArrayList;
import java.util.Collection;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.kore.stammdaten.lager.application.lager.LagerBean;

/**
 *
 * @author Konrad Renner
 */
@Named("lager")
@SessionScoped
public class LagerController {

    @Inject
    LagerBean bean;

    @Inject
    DefaultLagerAdapterBuilder builder;

    private LagerModel aktuellesDetail;

    public Collection<LagerModel> getAll() {
        Collection<DefaultLagerAdapterBuilder.DefaultLagerAdapter> daten = bean.getAll(builder);

        ArrayList<LagerModel> ret = new ArrayList<>(daten.size());
        for (DefaultLagerAdapterBuilder.DefaultLagerAdapter dt : daten) {
            ret.add(new LagerModel(dt));
        }
        return ret;
    }
    
    public String loadDetail(Short lagerid) {
        aktuellesDetail = new LagerModel(bean.getLager(builder, lagerid));
        return "lagerDetail";
    }

    public String loadUebersicht() {
        return "lagerUebersicht";
    }

    public LagerModel getDetail() {
        return aktuellesDetail;
    }

    public String delete(Short lagerid) {
        return "lagerUebersicht";
    }

    public String create() {
        return "lagerDetail";
    }

    public String save() {
        return "lagerUebersicht";
    }
}
