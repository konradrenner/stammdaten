/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.lager.domain.versandkosten;

import java.io.Serializable;
import java.util.Currency;
import javax.validation.constraints.NotNull;
import org.kore.runtime.currency.Money;
import org.kore.runtime.currency.MoneyTranslator;

/**
 *
 * @author koni
 */
public class VersandkostenService implements Serializable {

    private MoneyTranslator umrechner;

    VersandkostenService() {
        //CDI
    }


    public VersandkostenService(MoneyTranslator translator) {
        umrechner = translator;
    }

    /**
     * Setzt den Versandkostenbetrag, falls der neue Betrag in einer anderen
     * Waehrung ist, wird der Betrag in die Waehrung der Versandkosten
     * umgerechnet
     *
     * @param object
     * @param newBetrag
     * @param umrechner
     */
    public void changeBetrag(@NotNull Versandkosten object, @NotNull Money newBetrag) {
        Money correctBetrag = newBetrag;
        Currency actualCurrency = object.getBetrag().getCurrency();

        if (actualCurrency.equals(correctBetrag.getCurrency())) {
            object.setBetrag(correctBetrag.getAmount());
            return;
        }

        correctBetrag = umrechner.translate(newBetrag, actualCurrency);

        object.setBetrag(correctBetrag.getAmount());
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
    public void changeFreibetrag(@NotNull Versandkosten object, @NotNull Money newBetrag) {
        Money correctBetrag = newBetrag;
        Currency actualCurrency = object.getBetrag().getCurrency();

        if (actualCurrency.equals(correctBetrag.getCurrency())) {
            object.setFreibetrag(correctBetrag.getAmount());
            return;
        }

        correctBetrag = umrechner.translate(newBetrag, actualCurrency);

        object.setFreibetrag(correctBetrag.getAmount());
    }

    /**
     * Aendert die Waehrung von Versandkosten. Zusaetzlich zur Waehrung werden
     * auch die Betraege in die neue Waehrung umgerechnet
     *
     * @param object
     * @param newCurrency
     * @param umrechner
     */
    public void changeCurrency(@NotNull Versandkosten object, @NotNull Currency newCurrency) {
        Money newBetrag = umrechner.translate(object.getBetrag(), newCurrency);

        Money newFreibetrag = object.getFreibetrag();
        if (newFreibetrag != null) {
            newFreibetrag = umrechner.translate(object.getFreibetrag(), newCurrency);
            object.setFreibetrag(newFreibetrag.getAmount());
        }
        
        object.setWaehrung(newCurrency);
        object.setBetrag(newBetrag.getAmount());
    }
}
