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
package org.kore.stammdaten.lager.domain.artikel;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.NotNull;
import org.kore.runtime.specifications.Description;
import org.kore.runtime.specifications.Identifier;

/**
 *
 * @author Konrad Renner
 */
@AggregateArtikel
@RequestScoped
public class ArtikelService implements Serializable {

    public void changeArtikelgruppeTyp(@NotNull Artikelgruppe gruppe, @NotNull Artikelgruppe.Typ typ) {
        gruppe.setTyp(typ);
    }

    public void changeArtikelgruppe(@NotNull Artikel artikel, @NotNull Identifier bezeichnung, @NotNull Description beschreibung, @NotNull Artikelgruppe.Typ typ) {
        Artikelgruppe artikelgruppe = new Artikelgruppe(bezeichnung, typ);
        artikelgruppe.setBeschreibung(beschreibung);

        artikel.addArtikelGruppen(artikelgruppe);
    }

    public void changeArtikelgruppe(@NotNull Artikel artikel, @NotNull Identifier bezeichnung, @NotNull Artikelgruppe.Typ typ) {
        Artikelgruppe artikelgruppe = new Artikelgruppe(bezeichnung, typ);

        artikel.addArtikelGruppen(artikelgruppe);
    }

    public void changeArtikelgruppe(@NotNull Artikel artikel, @NotNull Artikelgruppe... gruppe) {
        artikel.addArtikelGruppen(gruppe);
    }
}
