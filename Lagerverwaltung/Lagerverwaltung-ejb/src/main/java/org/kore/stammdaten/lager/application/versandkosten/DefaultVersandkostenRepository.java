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

import java.util.Collections;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.kore.stammdaten.core.adresse.Land;
import org.kore.stammdaten.lager.domain.versandkosten.AggregateVersandkosten;
import org.kore.stammdaten.lager.domain.versandkosten.Versandkosten;
import org.kore.stammdaten.lager.domain.versandkosten.VersandkostenRepository;

/**
 *
 * @author Konrad Renner
 */
@Stateless
@Local(VersandkostenRepository.class)
@Dependent
@AggregateVersandkosten
public class DefaultVersandkostenRepository implements VersandkostenRepository {

    @PersistenceContext(name = "lager")
    EntityManager em;

    @Override
    public Versandkosten find(@NotNull Land land) {
        return em.find(Versandkosten.class, land);
    }
    
    @Override
    public List<Versandkosten> find() {
        TypedQuery<Versandkosten> query = em.createNamedQuery("Versandkosten.findAll", Versandkosten.class);
        return Collections.unmodifiableList(query.getResultList());
    }

    @Override
    public void save(Versandkosten kosten) {
        em.merge(kosten);
    }

    @Override
    public void delete(Versandkosten kosten) {
        em.remove(kosten);
    }
}
