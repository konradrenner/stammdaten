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
package org.kore.stammdaten.lager.ui;

import java.math.BigDecimal;
import java.util.Currency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.kore.runtime.currency.Money;

/**
 *
 * @author Konrad Renner
 */
@FacesConverter(value = "MoneyConverter", forClass = Money.class)
public class MoneyConverter implements Converter {

    @Override
    public Money getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.toString().length() == 0) {
            return null;
        }

        String[] splitted = value.split(" ");
        return new Money(new BigDecimal(splitted[0]), Currency.getInstance(splitted[1]));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.toString().length() == 0) {
            return "";
        }

        Money money = (Money) value;
        return money.getAmount() + " " + money.getCurrency();
    }
}
