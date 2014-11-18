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
package org.kore.stammdaten.lager.application.artikel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import org.kore.stammdaten.lager.adapter.ArtikelAdapter;
import org.kore.stammdaten.lager.adapter.ArtikelAdapterBuilder;
import org.kore.stammdaten.lager.domain.artikel.AggregateArtikel;
import org.kore.stammdaten.lager.domain.artikel.Artikel;
import org.kore.stammdaten.lager.domain.artikel.Artikelgruppe;

/**
 *
 * @author Konrad Renner
 */
@Stateful
@ConversationScoped
@LocalBean
public class ArtikelBean {

    @Inject
    @AggregateArtikel
    DefaultArtikelRepository repository;

    public <T extends ArtikelAdapter> Collection<T> getAll(ArtikelAdapterBuilder<T> builder) {
        List<Artikel> findAll = repository.findAll();

        ArrayList<T> ret = new ArrayList<>(findAll.size());

        for (Artikel artikel : findAll) {
            ArtikelAdapterBuilder.Properties<T> properties = builder.newInstance(artikel.getArtikelId(), artikel.getBezeichnung(), artikel.getPreis()).beschreibung(artikel.getBeschreibung()).bild(artikel.getBild());

//            for (Artikelgruppe gruppe : artikel.getArtikelGruppen()) {
//                properties.addArtikelGruppe(gruppe.getBezeichnung());
//            }

            ret.add(properties.build());
        }

        return ret;
    }

    public <T extends ArtikelAdapter> T getDetail(ArtikelAdapterBuilder<T> builder, int artikelid) {
        Artikel detail = repository.find(artikelid);

        ArtikelAdapterBuilder.Properties<T> properties = builder.newInstance(detail.getArtikelId(), detail.getBezeichnung(), detail.getPreis());

        if (detail.getBeschreibung() != null) {
            properties.beschreibung(detail.getBeschreibung());
        }

        if (detail.getBild() != null) {
            properties.bild(detail.getBild());
        }

        for (Artikelgruppe gruppe : detail.getArtikelGruppen()) {
            properties.addArtikelGruppe(gruppe.getBezeichnung());
        }

        return properties.build();
    }
}
