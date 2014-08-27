/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.kore.stammdaten.lager.fx;

/**
 *
 * @author Konrad Renner
 */
public class VersandkostenModel {
    private String land;
    private String betrag;
    private String freikosten;
    private String waehrung;

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getBetrag() {
        return betrag;
    }

    public void setBetrag(String betrag) {
        this.betrag = betrag;
    }

    public String getFreikosten() {
        return freikosten;
    }

    public void setFreikosten(String freikosten) {
        this.freikosten = freikosten;
    }

    public String getWaehrung() {
        return waehrung;
    }

    public void setWaehrung(String waehrung) {
        this.waehrung = waehrung;
    }

    @Override
    public String toString() {
        return "VersandkostenModel{" + "land=" + land + ", betrag=" + betrag + ", freikosten=" + freikosten + ", waehrung=" + waehrung + '}';
    }


}
