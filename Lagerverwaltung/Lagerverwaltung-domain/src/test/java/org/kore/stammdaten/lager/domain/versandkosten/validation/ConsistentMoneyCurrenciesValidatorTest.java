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
package org.kore.stammdaten.lager.domain.versandkosten.validation;

import java.math.BigDecimal;
import java.util.Currency;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.kore.runtime.currency.Money;
import org.kore.stammdaten.core.adresse.Land;

/**
 *
 * @author Konrad Renner
 */
public class ConsistentMoneyCurrenciesValidatorTest {

    private ConsistentMoneyCurrenciesValidator validator;

    @Before
    public void setUp() {
        validator = new ConsistentMoneyCurrenciesValidator();
    }

    @Test
    public void testIsValid() {
        Money money1 = new Money(BigDecimal.TEN, Currency.getInstance("EUR"));
        Money money2 = new Money(BigDecimal.ONE, Currency.getInstance("EUR"));
        Money money3 = new Money(BigDecimal.ZERO, Currency.getInstance("EUR"));

        assertThat(validator.isValid(new Object[]{money1, money2, money3}, null), is(true));
    }

    @Test
    public void testIsNotValid() {
        Money money1 = new Money(BigDecimal.TEN, Currency.getInstance("EUR"));
        Money money2 = new Money(BigDecimal.ONE, Currency.getInstance("EUR"));
        Money money3 = new Money(BigDecimal.ZERO, Currency.getInstance("USD"));

        assertThat(validator.isValid(new Object[]{money1, money2, money3}, null), is(false));
    }

    @Test
    public void testNoParameters() {
        assertThat(validator.isValid(new Object[0], null), is(true));
    }

    @Test()
    public void testWrongInstance() {
        Money money1 = new Money(BigDecimal.TEN, Currency.getInstance("EUR"));
        Money money2 = new Money(BigDecimal.ONE, Currency.getInstance("EUR"));
        Currency currency = Currency.getInstance("USD");
        Land land = new Land("AT");

        assertThat(validator.isValid(new Object[]{land, money1, money2, currency}, null), is(true));
    }
}
