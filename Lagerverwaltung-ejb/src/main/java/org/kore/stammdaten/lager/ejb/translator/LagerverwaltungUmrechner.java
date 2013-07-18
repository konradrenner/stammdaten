/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.lager.ejb.translator;

import java.util.Currency;
import org.kore.runtime.currency.Money;
import org.kore.runtime.currency.MoneyTranslator;

/**
 *
 * @author koni
 */
public class LagerverwaltungUmrechner implements MoneyTranslator {

    @Override
    public Money translate(Money money, Currency crnc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
