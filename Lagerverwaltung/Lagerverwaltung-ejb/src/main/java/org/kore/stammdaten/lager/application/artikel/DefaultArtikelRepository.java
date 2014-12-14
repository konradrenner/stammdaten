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

import java.util.Collections;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import org.kore.runtime.specifications.Identifier;
import org.kore.stammdaten.lager.domain.artikel.AggregateArtikel;
import org.kore.stammdaten.lager.domain.artikel.Artikel;
import org.kore.stammdaten.lager.domain.artikel.ArtikelId;
import org.kore.stammdaten.lager.domain.artikel.ArtikelRepository;
import org.kore.stammdaten.lager.domain.artikel.Artikelgruppe;

/**
 *
 * @author Konrad Renner
 */
@Dependent
@AggregateArtikel
public class DefaultArtikelRepository implements ArtikelRepository {

    @PersistenceContext(name = "lager")
    EntityManager em;

    DefaultArtikelRepository() {
        //CDI
    }

    @Override
    public List<Artikel> findAll() {
        List<Artikel> resultList = em.createNamedQuery("Artikel.findAll", Artikel.class).getResultList();
        return Collections.unmodifiableList(resultList);
    }

    @Override
    public Artikel find(@NotNull Integer artikelId) {
        return em.find(Artikel.class, artikelId);
    }
    
    @Override
    public Artikel find(@NotNull ArtikelId artikelId) {
        return find(artikelId.getValue());
    }

    @Override
    public Artikel findByBezeichnung(@NotNull Identifier bezeichnung) {
        return em.createNamedQuery("Artikel.findByBezeichnung", Artikel.class).setParameter("bezeichnung", bezeichnung).getSingleResult();
    }

    @Override
    public List<Artikel> findByArtikelgruppenTyp(Artikelgruppe.Typ typ) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Artikel> findByArtikelgruppenBezeichnung(@NotNull Identifier bezeichnung) {
        throw new UnsupportedOperationException("not implemented yet");
    }
    
    @Override
    public void delete(Artikel artikel) {
        em.remove(artikel);
    }
}
