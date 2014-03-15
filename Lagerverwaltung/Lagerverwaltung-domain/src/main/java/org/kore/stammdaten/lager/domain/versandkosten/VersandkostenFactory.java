/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.lager.domain.versandkosten;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import org.kore.runtime.currency.Money;
import org.kore.stammdaten.core.adresse.Land;

/**
 *
 * @author koni
 */
public class VersandkostenFactory implements Serializable {

    public Versandkosten create(@NotNull Land land, @NotNull Money betrag) {
        return new Versandkosten(land, betrag);
    }

    public Versandkosten create(@NotNull Land land, @NotNull Money betrag, @NotNull Money freibetrag) {
        //TODO mit Bean-Validation ersetzen
        if (!betrag.getCurrency().equals(freibetrag.getCurrency())) {
            throw new IllegalStateException("Die Betraege haben verschiedene Waehrungen");
        }

        Versandkosten kosten = new Versandkosten(land, betrag);
        kosten.setFreibetrag(freibetrag.getAmount());

        return kosten;
    }
}
