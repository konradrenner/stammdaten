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
package org.kore.stammdaten.lager.model.artikel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.kore.stammdaten.lager.adapter.ArtikelAdapter;
import org.kore.stammdaten.lager.application.artikel.ArtikelBean;

/**
 *
 * @author Konrad Renner
 */
@SessionScoped
@Named
public class ArtikelController implements Serializable {
    @Inject
    ArtikelBean bean;

    @Inject
    DefaultArtikelAdapterBuilder builder;

    private ArtikelModel aktuellesDetail;

    public Collection<ArtikelModel> getAll() {
        Collection<ArtikelAdapter> daten = bean.getAll(builder);

        ArrayList<ArtikelModel> ret = new ArrayList<>(daten.size());
        for (ArtikelAdapter dt : daten) {
            ret.add(new ArtikelModel(dt));
        }
        return ret;
    }

    public String loadDetail(Short lagerid) {
        aktuellesDetail = new ArtikelModel(bean.getDetail(builder, lagerid));
        return "lagerDetail";
    }

    public String loadUebersicht() {
        return "lagerUebersicht";
    }

    public ArtikelModel getDetail() {
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
