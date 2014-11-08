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
package org.kore.stammdaten.lager.domain.lager;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import org.kore.runtime.specifications.Identifier;

/**
 *
 * @author Konrad Renner
 */
public class LagerRepository {
    private EntityManager em;

    public LagerRepository(EntityManager em) {
        this.em = em;
    }
    
    public List<Lager> findAll() {
        List resultList = em.createNamedQuery("Lager.findAll").getResultList();
        return Collections.unmodifiableList(resultList);
    }

    public Lager find(@NotNull Short lagerid) {
        return em.find(Lager.class, lagerid);
    }

    public Lager findByBezeichnung(@NotNull Identifier bezeichnung) {
        return em.createNamedQuery("Lager.findByBezeichnung", Lager.class).setParameter("bezeichnung", bezeichnung).getSingleResult();
    }

}
