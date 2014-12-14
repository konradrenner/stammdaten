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
package org.kore.stammdaten.lager.adapter;

import org.kore.runtime.currency.Money;
import org.kore.runtime.specifications.Description;
import org.kore.runtime.specifications.Identifier;

/**
 *
 * @author Konrad Renner
 * @param <T>
 */
public interface ArtikelAdapterBuilder<T extends ArtikelAdapter> {

    Properties<T> newInstance(int artikelid, Identifier bezeichnung, Money preis);

    interface Properties<T extends ArtikelAdapter> {

        default Properties<T> beschreibung(Description beschreibung) {
            return this;
        }

        default Properties<T> bild(byte[] bild) {
            return this;
        }

        default Properties<T> addArtikelGruppe(ArtikelAdapter.Artikelgruppe gruppe) {
            return this;
        }

        default Properties<T> addArtikelGruppe(Identifier bezeichnung, Description beschreibung, String typ) {
            return this;
        }

        default Properties<T> addArtikelGruppe(Identifier bezeichnung, String typ) {
            return addArtikelGruppe(bezeichnung, null, typ);
        }

        T build();
    }
}
