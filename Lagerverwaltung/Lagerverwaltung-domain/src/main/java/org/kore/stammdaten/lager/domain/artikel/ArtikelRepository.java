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
package org.kore.stammdaten.lager.domain.artikel;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Konrad Renner
 */
public class ArtikelRepository {
    private EntityManager em;

    public ArtikelRepository(EntityManager em) {
        this.em = em;
    }

    public List<Artikel> findAll() {
        List<Artikel> resultList = em.createNamedQuery("Artikel.findAll", Artikel.class).getResultList();
        return Collections.unmodifiableList(resultList);
    }

    public Artikel find(@NotNull Integer artikelId) {
        return em.find(Artikel.class, artikelId);
    }
}
