/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.domain.versandkosten;

import javax.validation.constraints.NotNull;
import org.kore.runtime.currency.Money;
import org.kore.stammdaten.core.adresse.Land;

/**
 *
 * @author koni
 */
public class VersandkostenFactory {

    public Versandkosten create(@NotNull Land land, @NotNull Money betrag) {
        return new Versandkosten(land, betrag);
    }

    public Versandkosten create(@NotNull Land land, @NotNull Money betrag, @NotNull Money freibetrag) {
        Versandkosten kosten = new Versandkosten(land, betrag);
        kosten.setFreibetrag(freibetrag);

        return kosten;
    }
}
