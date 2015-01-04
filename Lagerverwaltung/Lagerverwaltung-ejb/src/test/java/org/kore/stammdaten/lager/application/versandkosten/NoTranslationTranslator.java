/*
 * Copyright (C) 2015 Konrad Renner.
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

import java.util.Currency;
import javax.enterprise.context.RequestScoped;
import org.kore.runtime.currency.Money;
import org.kore.runtime.currency.MoneyTranslator;

/**
 * Alternative Implementierung des MoneyTranslator, kann aktiviert werden, falls
 * keine Umrechnung gewuenscht ist, oder fuer Tests
 *
 * @author Konrad Renner
 */
@RequestScoped
public class NoTranslationTranslator implements MoneyTranslator {
    
    @Override
    public Money translate(Money money, Currency crnc) {
        return new Money(money.getAmount(), crnc);
    }
    
}
