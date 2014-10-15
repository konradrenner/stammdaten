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
package org.kore.stammdaten.lager.domain.versandkosten;

import java.io.Serializable;
import java.util.Currency;
import javax.validation.constraints.NotNull;
import org.kore.runtime.currency.Money;
import org.kore.runtime.currency.MoneyTranslator;

/**
 *
 * @author Konrad Renner
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
     */
    public void changeBetrag(@NotNull Versandkosten object, @NotNull Money newBetrag) {
        Money correctBetrag = newBetrag;
        Currency actualCurrency = object.getBetrag().getCurrency();

        if (actualCurrency.equals(correctBetrag.getCurrency())) {
            object.setBetrag(correctBetrag);
            return;
        }

        correctBetrag = umrechner.translate(newBetrag, actualCurrency);

        object.setBetrag(correctBetrag);
        
        Money freibetrag = object.getFreibetrag();
        if (freibetrag != null) {
            correctBetrag = umrechner.translate(freibetrag, actualCurrency);
            object.setFreibetrag(correctBetrag.getAmount());
        }
    }

    /**
     * Setzt den Versandkostenfreibetrag, falls der neue Betrag in einer anderen
     * Waehrung ist, wird der Betrag in die Waehrung der Versandkosten
     * umgerechnet
     *
     * @param object
     * @param newBetrag
     */
    public void changeFreibetrag(@NotNull Versandkosten object, Money newBetrag) {
        if (newBetrag == null) {
            object.setFreibetrag(null);
            return;
        }
        
        Money correctBetrag = newBetrag;
        Currency actualCurrency = object.getBetrag().getCurrency();

        if (actualCurrency.equals(correctBetrag.getCurrency())) {
            object.setFreibetrag(correctBetrag.getAmount());
            return;
        }

        correctBetrag = umrechner.translate(newBetrag, actualCurrency);

        object.setFreibetrag(correctBetrag.getAmount());

        Money betrag = object.getBetrag();
        correctBetrag = umrechner.translate(betrag, actualCurrency);
        object.setBetrag(correctBetrag);
    }

    /**
     * Aendert die Waehrung von Versandkosten. Zusaetzlich zur Waehrung werden
     * auch die Betraege in die neue Waehrung umgerechnet
     *
     * @param object
     * @param newCurrency
     */
    public void changeCurrency(@NotNull Versandkosten object, @NotNull Currency newCurrency) {
        Money newBetrag = umrechner.translate(object.getBetrag(), newCurrency);

        Money newFreibetrag = object.getFreibetrag();
        if (newFreibetrag != null) {
            newFreibetrag = umrechner.translate(object.getFreibetrag(), newCurrency);
            object.setFreibetrag(newFreibetrag.getAmount());
        }
        
        object.setBetrag(newBetrag);
    }
}
