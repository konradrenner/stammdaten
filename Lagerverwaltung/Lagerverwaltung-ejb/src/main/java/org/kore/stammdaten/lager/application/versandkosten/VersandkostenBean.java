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
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.kore.stammdaten.core.adresse.Land;
import org.kore.stammdaten.lager.domain.versandkosten.AggregateVersandkosten;
import org.kore.stammdaten.lager.domain.versandkosten.Versandkosten;
import org.kore.stammdaten.lager.domain.versandkosten.VersandkostenFactory;
import org.kore.stammdaten.lager.domain.versandkosten.VersandkostenRepository;
import org.kore.stammdaten.lager.domain.versandkosten.VersandkostenService;
import org.kore.stammdaten.lager.dto.versandkosten.VersandkostenDTO;

/**
 *
 * @author Konrad Renner
 */
@Stateful
@LocalBean
@SessionScoped
@Named("versandkosten")
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
    private VersandkostenDTO currentDTO;

    public Collection<VersandkostenDTO> getAll() {
        List<Versandkosten> vkosten = repository.find();

        ArrayList<VersandkostenDTO> response = new ArrayList<>();
        for (Versandkosten kost : vkosten) {
            VersandkostenDTO dto = new VersandkostenDTO();

            dto.setBetrag(kost.getBetrag());

            dto.setFreibetrag(kost.getFreibetrag());

            dto.setLand(kost.getLand().getIso3166Code());

            response.add(dto);
        }
        return response;
    }

    public String loadLand(String land) {
        Versandkosten kost = repository.find(new Land(land));

        VersandkostenDTO dto = new VersandkostenDTO();

        dto.setBetrag(kost.getBetrag());

        dto.setFreibetrag(kost.getFreibetrag());

        dto.setLand(kost.getLand().getIso3166Code());

        currentDTO = dto;

        return "versandkostenDetail";
    }

    public VersandkostenDTO getDetail() {
        return currentDTO;
    }

    public String loadUebersicht() {
        return "versandkostenUebersicht";
    }
    
    public String save() {
        Versandkosten kto = repository.find(new Land(currentDTO.getLand()));

        if (kto == null) {
            kto = factory.create(new Land(currentDTO.getLand()), currentDTO.getBetrag());
        } else {
        
        service.changeBetrag(kto, currentDTO.getBetrag());
            service.changeFreibetrag(kto, currentDTO.getFreibetrag());
        }
        
        repository.save(kto);
        
        return "versandkostenUebersicht";
    }
    
    public String delete(String land) {
        repository.delete(repository.find(new Land(land)));

        return "versandkostenUebersicht";
    }

    public String create() {
        this.currentDTO = new VersandkostenDTO();

        return "versandkostenDetail";
    }
}
