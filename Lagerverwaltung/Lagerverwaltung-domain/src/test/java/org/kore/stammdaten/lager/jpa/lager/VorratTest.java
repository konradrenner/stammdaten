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
package org.kore.stammdaten.lager.jpa.lager;

import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.kore.stammdaten.lager.domain.lager.Vorrat;
import org.kore.stammdaten.lager.jpa.EntityTest;

/**
 *
 * @author Konrad Renner
 */
public class VorratTest extends EntityTest {

    @Test
    public void testFindAll() {
        List<Vorrat> alleVorrat = getEntityManager().createNamedQuery("Vorrat.findAll", Vorrat.class).getResultList();

        assertThat(alleVorrat.size(), is(3));
    }
}
