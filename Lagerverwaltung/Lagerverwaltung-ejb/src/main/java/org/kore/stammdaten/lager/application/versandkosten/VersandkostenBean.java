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
package org.kore.stammdaten.lager.application.versandkosten;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import org.kore.stammdaten.core.adresse.Land;
import org.kore.stammdaten.lager.adapter.VersandkostenAdapter;
import org.kore.stammdaten.lager.adapter.VersandkostenAdapterFactory;
import org.kore.stammdaten.lager.domain.versandkosten.AggregateVersandkosten;
import org.kore.stammdaten.lager.domain.versandkosten.Versandkosten;
import org.kore.stammdaten.lager.domain.versandkosten.VersandkostenFactory;
import org.kore.stammdaten.lager.domain.versandkosten.VersandkostenRepository;
import org.kore.stammdaten.lager.domain.versandkosten.VersandkostenService;

/**
 *
 * @author Konrad Renner
 */
@Stateless
@LocalBean
@Dependent
public class VersandkostenBean {

    @Inject
    @AggregateVersandkosten
    VersandkostenRepository repository;
    @Inject
    @AggregateVersandkosten
    VersandkostenFactory factory;
    @Inject
    @AggregateVersandkosten
    VersandkostenService service;

    public <T extends VersandkostenAdapter> Collection<T> getAll(VersandkostenAdapterFactory<T> factory) {
        List<Versandkosten> vkosten = repository.find();

        Collection<T> response = new ArrayList<>();
        for (Versandkosten kost : vkosten) {
            T dto = factory.createBuilder()
                    .land(kost.getLand())
                    .freibetrag(kost.getFreibetrag())
                    .betrag(kost.getBetrag())
                    .build();

            response.add(dto);
        }
        return response;
    }

    public <T extends VersandkostenAdapter> T getDetail(VersandkostenAdapterFactory<T> factory, Land land) {
        Versandkosten kost = repository.find(land);

        return factory.createBuilder()
                .land(kost.getLand())
                .freibetrag(kost.getFreibetrag())
                .betrag(kost.getBetrag())
                .build();
    }

    public void update(VersandkostenAdapter currentDTO) {
        Versandkosten kto = repository.find(currentDTO.getLand());

        if (kto == null) {
            kto = factory.create(currentDTO.getLand(), currentDTO.getBetrag());
            service.changeFreibetrag(kto, currentDTO.getFreibetrag());
        } else {
        
            service.changeBetrag(kto, currentDTO.getBetrag());
            service.changeFreibetrag(kto, currentDTO.getFreibetrag());
        }
        
        repository.save(kto);
    }
    
    public void delete(Land land) {
        repository.delete(repository.find(land));
    }
}
