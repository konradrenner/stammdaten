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
package org.kore.stammdaten.lager.model.artikel;

import java.math.BigDecimal;
import java.util.Set;
import java.util.TreeSet;
import javax.validation.constraints.NotNull;
import org.kore.stammdaten.lager.adapter.ArtikelAdapter;

/**
 *
 * @author Konrad Renner
 */
public class ArtikelModel {

    private int artikelid;
    @NotNull
    private String bezeichnung;
    private String beschreibung;
    @NotNull
    private BigDecimal preis;
    @NotNull
    private String waehrung;
    private byte[] bild;
    private final Set<String> gruppen;

    public ArtikelModel(ArtikelAdapter adapter) {
        artikelid = adapter.getArtikelId();
        bezeichnung = adapter.getBezeichnung().getValue();
        preis = adapter.getPreis().getAmount();
        waehrung = adapter.getPreis().getCurrency().getCurrencyCode();
        bild = adapter.getBild();

        if (adapter.getBeschreibung() != null) {
            beschreibung = adapter.getBeschreibung().getValue();
        }

        gruppen = new TreeSet<>();
        for (ArtikelAdapter.Artikelgruppe gruppe : adapter.getArtikelGruppen()) {
            gruppen.add(gruppe.getBezeichnung().getValue());
        }
    }

    public int getArtikelid() {
        return artikelid;
    }

    public void setArtikelid(int artikelId) {
        this.artikelid = artikelId;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public BigDecimal getPreis() {
        return preis;
    }

    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }

    public String getWaehrung() {
        return waehrung;
    }

    public void setWaehrung(String waehrung) {
        this.waehrung = waehrung;
    }

    public byte[] getBild() {
        return bild;
    }

    public void setBild(byte[] bild) {
        this.bild = bild;
    }

    public Set<String> getGruppen() {
        return gruppen;
    }

    @Override
    public String toString() {
        return "ArtikelModel{" + "artikelid=" + artikelid + ", bezeichnung=" + bezeichnung + ", beschreibung=" + beschreibung + ", preis=" + preis + ", waehrung=" + waehrung + ", bild=" + bild + ", gruppen=" + gruppen + '}';
    }

}
