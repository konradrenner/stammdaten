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
package org.kore.stammdaten.lager.model.lager;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.kore.stammdaten.lager.adapter.LagerAdapter;

/**
 *
 * @author Konrad Renner
 */
public class LagerModel {

    @NotNull
    private Short lagerid;
    @NotNull
    private String bezeichnung;
    private String beschreibung;
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email")
    private String email;

    public LagerModel(LagerAdapter adapter) {
        lagerid = adapter.getLagerId();
        bezeichnung = adapter.getBezeichnung().getValue();
        if (adapter.getBeschreibung() != null) {
            beschreibung = adapter.getBeschreibung().getValue();
        }
        if (adapter.getEmail() != null) {
            email = adapter.getEmail().getValue();
        }
    }

    public Short getLagerid() {
        return lagerid;
    }

    public void setLagerid(Short lagerid) {
        this.lagerid = lagerid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "LagerModel{" + "lagerid=" + lagerid + ", bezeichnung=" + bezeichnung + ", beschreibung=" + beschreibung + ", email=" + email + '}';
    }

}
