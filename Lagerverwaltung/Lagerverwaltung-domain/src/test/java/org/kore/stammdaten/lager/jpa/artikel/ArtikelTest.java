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
package org.kore.stammdaten.lager.jpa.artikel;

import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.kore.runtime.specifications.Identifier;
import org.kore.stammdaten.lager.domain.artikel.Artikel;
import org.kore.stammdaten.lager.jpa.EntityTest;

/**
 *
 * @author Konrad Renner
 */
public class ArtikelTest extends EntityTest {

    @Test
    public void testFindAll() {
        List<Artikel> alleArtikel = getEntityManager().createNamedQuery("Artikel.findAll", Artikel.class).getResultList();

        assertThat(alleArtikel.size(), is(2));
    }
    
    @Test
    public void testFindNone() {
        Artikel artikel = getEntityManager().find(Artikel.class, 10);
        
        assertThat(artikel, is(nullValue()));
    }
    
    @Test
    public void testFindArtikel() {
        Artikel artikel = getEntityManager().find(Artikel.class, 2);

        assertThat(artikel, is(notNullValue()));
        assertThat(artikel.getBezeichnung(), is(new Identifier("Elektroauto 5000")));
        assertThat(artikel.getArtikelGruppen().size(), is(2));
    }
}
