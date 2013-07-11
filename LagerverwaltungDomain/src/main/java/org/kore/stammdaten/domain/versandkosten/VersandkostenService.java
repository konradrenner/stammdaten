/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.domain.versandkosten;

import java.util.Currency;
import org.kore.runtime.currency.Money;
import org.kore.runtime.currency.MoneyTranslater;

/**
 *
 * @author koni
 */
public class VersandkostenService {

    /**
     * Setzt den Versandkostenbetrag, falls der neue Betrag in einer anderen
     * Waehrung ist, wird der Betrag in die Waehrung der Versandkosten
     * umgerechnet
     *
     * @param object
     * @param newBetrag
     * @param umrechner
     */
    public void changeBetrag(Versandkosten object, Money newBetrag, MoneyTranslater umrechner) {
        Money correctBetrag = newBetrag;
        Currency actualCurrency = object.getBetrag().getCurrency();

        if (actualCurrency.equals(correctBetrag.getCurrency())) {
            object.setBetrag(correctBetrag);
            return;
        }

        correctBetrag = umrechner.translate(newBetrag, actualCurrency);

        object.setBetrag(correctBetrag);
    }

    /**
     * Setzt den Versandkostenfreibetrag, falls der neue Betrag in einer anderen
     * Waehrung ist, wird der Betrag in die Waehrung der Versandkosten
     * umgerechnet
     *
     * @param object
     * @param newBetrag
     * @param umrechner
     */
    public void changeFreibetrag(Versandkosten object, Money newBetrag, MoneyTranslater umrechner) {
        Money correctBetrag = newBetrag;
        Currency actualCurrency = object.getBetrag().getCurrency();

        if (actualCurrency.equals(correctBetrag.getCurrency())) {
            object.setFreibetrag(correctBetrag);
            return;
        }

        correctBetrag = umrechner.translate(newBetrag, actualCurrency);

        object.setFreibetrag(correctBetrag);
    }

    /**
     * Aendert die Waehrung von Versandkosten. Zusaetzlich zur Waehrung werden
     * auch die Betraege in die neue Waehrung umgerechnet
     *
     * @param object
     * @param newCurrency
     * @param umrechner
     */
    public void changeCurrency(Versandkosten object, Currency newCurrency, MoneyTranslater umrechner) {
        Money newBetrag = umrechner.translate(object.getBetrag(), newCurrency);
        Money newFreibetrag = umrechner.translate(object.getFreibetrag(), newCurrency);
        
        object.setWaehrung(newCurrency);
        object.setBetrag(newBetrag);
        object.setFreibetrag(newFreibetrag);
    }
}
