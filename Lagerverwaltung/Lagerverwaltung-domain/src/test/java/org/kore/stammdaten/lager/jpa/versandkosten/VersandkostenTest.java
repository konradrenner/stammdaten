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
package org.kore.stammdaten.lager.jpa.versandkosten;

import java.math.BigDecimal;
import java.util.Currency;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.kore.runtime.currency.Money;
import org.kore.stammdaten.core.adresse.Land;
import org.kore.stammdaten.lager.domain.versandkosten.Versandkosten;
import org.kore.stammdaten.lager.jpa.EntityTest;

/**
 *
 * @author Konrad Renner
 */
public class VersandkostenTest extends EntityTest {

    @Test
    public void testFindByPrimaryKey() throws Exception {
        Land land = new Land("AT");
        Versandkosten expected = new Versandkosten(land, new Money(BigDecimal.ZERO, Currency.getInstance("EUR")));
        
        assertThat(getEntityManager().find(Versandkosten.class, land), is(expected));
    }

    @Test
    public void testFindByPrimaryKeyNotFound() throws Exception {
        Land land = new Land("DE");

        assertThat(getEntityManager().find(Versandkosten.class, land), is(nullValue()));
    }

    @Test
    public void testFindAll() throws Exception {

        assertThat(getEntityManager().createNamedQuery("Versandkosten.findAll", Versandkosten.class).getResultList().size(), is(2));
    }

}
