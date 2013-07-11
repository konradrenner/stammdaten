/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.domain.versandkosten;

import java.math.BigDecimal;
import java.util.Currency;
import org.kore.runtime.currency.Money;
import org.kore.runtime.currency.MoneyTranslater;

/**
 * Fuegt dem Betrag des Moneys immer genau 1 hinzu
 *
 * @author koni
 */
public class TestMoneyTranslator implements MoneyTranslater {

    @Override
    public Money translate(Money money, Currency crnc) {
        return new Money(money.getAmount().add(BigDecimal.ONE), crnc);
    }
}
