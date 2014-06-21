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

import java.io.Serializable;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.kore.runtime.currency.MoneyTranslator;
import org.kore.stammdaten.lager.domain.versandkosten.AggregateVersandkosten;
import org.kore.stammdaten.lager.domain.versandkosten.VersandkostenFactory;
import org.kore.stammdaten.lager.domain.versandkosten.VersandkostenRepository;
import org.kore.stammdaten.lager.domain.versandkosten.VersandkostenService;

/**
 *
 * @author Konrad Renner
 */
@Stateful
@LocalBean
@ConversationScoped
public class VersandkostenProducer implements Serializable {

    @PersistenceContext(name = "lager")
    EntityManager em;
    @Inject
    MoneyTranslator translator;

    @Produces
    @AggregateVersandkosten
    @RequestScoped
    public VersandkostenRepository getVersandkostenRepository() {
        return new VersandkostenRepository(em);
    }

    @Produces
    @AggregateVersandkosten
    @RequestScoped
    public VersandkostenService getVersandkostenService() {
        return new VersandkostenService(translator);
    }

    @Produces
    @AggregateVersandkosten
    @RequestScoped
    public VersandkostenFactory getVersandkostenFactory() {
        return new VersandkostenFactory();
    }
}
