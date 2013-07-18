/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.lager.ejb.versandkosten;

import java.io.Serializable;
import org.kore.runtime.currency.Money;
import org.kore.stammdaten.core.adresse.Land;

/**
 *
 * @author koni
 */
public class VersandkostenDTO implements Serializable {

    Land land;
    Money betrag;
    Money freibetrag;

    public Land getLand() {
        return land;
    }

    public void setLand(Land land) {
        this.land = land;
    }

    public Money getBetrag() {
        return betrag;
    }

    public void setBetrag(Money betrag) {
        this.betrag = betrag;
    }

    public Money getFreibetrag() {
        return freibetrag;
    }

    public void setFreibetrag(Money freibetrag) {
        this.freibetrag = freibetrag;
    }

    @Override
    public String toString() {
        return "VersandkostenDTO{" + "land=" + land + ", betrag=" + betrag + ", freibetrag=" + freibetrag + '}';
    }
}
