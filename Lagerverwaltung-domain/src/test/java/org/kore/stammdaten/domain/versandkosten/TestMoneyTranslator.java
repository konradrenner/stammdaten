/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.domain.versandkosten;

import java.math.BigDecimal;
import java.util.Currency;
import org.kore.runtime.currency.Money;
import org.kore.runtime.currency.MoneyTranslator;

/**
 * Fuegt dem Betrag des Moneys immer genau 1 hinzu
 *
 * @author koni
 */
public class TestMoneyTranslator implements MoneyTranslator {

    @Override
    public Money translate(Money money, Currency crnc) {
        if (money.getCurrency().equals(crnc)) {
            return money;
        }
        return new Money(money.getAmount().add(BigDecimal.ONE), crnc);
    }
}
