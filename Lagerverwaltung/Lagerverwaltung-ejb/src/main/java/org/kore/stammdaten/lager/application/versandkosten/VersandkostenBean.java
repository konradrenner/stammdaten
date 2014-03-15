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
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.kore.runtime.currency.MoneyTranslator;
import org.kore.stammdaten.lager.application.DefaultMoneyTranslator;
import org.kore.stammdaten.lager.domain.versandkosten.DomainVersandkosten;
import org.kore.stammdaten.lager.domain.versandkosten.Versandkosten;
import org.kore.stammdaten.lager.domain.versandkosten.VersandkostenRepository;
import org.kore.stammdaten.lager.dto.versandkosten.VersandkostenDTO;

/**
 *
 * @author Konrad Renner
 */
@Stateful
@LocalBean
@ConversationScoped
@Named( "versandkosten")
public class VersandkostenBean {

    @PersistenceContext(name = "lager")
    EntityManager em;
    @Inject
    DefaultMoneyTranslator translator;
    @Inject
    VersandkostenRepository repository;

    public Collection<VersandkostenDTO> getAll() {
        List<Versandkosten> vkosten = repository.find();

        ArrayList<VersandkostenDTO> dto = new ArrayList<>();
        for (Versandkosten kost : vkosten) {
            dto.add(new VersandkostenDTO(kost));
        }
        return dto;
    }

    @Produces
    @DomainVersandkosten
    public EntityManager getEntityManager() {
        return this.em;
    }

    @Produces
    @DomainVersandkosten
    public MoneyTranslator getMoneyTranslator() {
        return translator;
    }
}
