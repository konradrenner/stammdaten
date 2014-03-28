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

import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.kore.runtime.currency.MoneyTranslator;
import org.kore.stammdaten.lager.domain.versandkosten.VersandkostenRepository;
import org.kore.stammdaten.lager.domain.versandkosten.VersandkostenService;

/**
 *
 * @author Konrad Renner
 */
@Singleton
@ApplicationScoped
public class VersandkostenController {

    @PersistenceContext(name = "lager")
    EntityManager em;
    @Inject
    MoneyTranslator translator;

    @Produces
    @RequestScoped
    public VersandkostenRepository getVersandkostenRepository() {
        return new VersandkostenRepository(em);
    }

    @Produces
    @RequestScoped
    public VersandkostenService getVersandkostenService() {
        return new VersandkostenService(translator);
    }
}
