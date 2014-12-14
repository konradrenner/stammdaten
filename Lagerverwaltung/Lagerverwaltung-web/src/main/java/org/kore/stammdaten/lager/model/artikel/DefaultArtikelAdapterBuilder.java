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

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import javax.enterprise.context.RequestScoped;
import org.kore.runtime.currency.Money;
import org.kore.runtime.specifications.Description;
import org.kore.runtime.specifications.Identifier;
import org.kore.stammdaten.lager.adapter.ArtikelAdapter;
import org.kore.stammdaten.lager.adapter.ArtikelAdapterBuilder;

/**
 *
 * @author Konrad Renner
 */
@RequestScoped
public class DefaultArtikelAdapterBuilder implements ArtikelAdapterBuilder<ArtikelAdapter> {
    
    @Override
    public Properties<ArtikelAdapter> newInstance(int artikelid, Identifier bezeichnung, Money preis) {
        return new DefaultArtikelAdapter(artikelid, bezeichnung, preis);
    }

    public class DefaultArtikelAdapter implements ArtikelAdapter, ArtikelAdapterBuilder.Properties {

        private final int artikelId;
        private final Set<ArtikelAdapter.Artikelgruppe> gruppen;
        private final Identifier bezeichnung;
        private final Money preis;

        private Description beschreibung;
        private byte[] bild;

        public DefaultArtikelAdapter(int artikelid, Identifier bezeichnung, Money preis) {
            this.artikelId = artikelid;
            this.gruppen = new TreeSet<>();
            this.bezeichnung = bezeichnung;
            this.preis = preis;
        }

        @Override
        public Properties beschreibung(Description beschreibung) {
            this.beschreibung = beschreibung;
            return this;
        }

        @Override
        public Properties bild(byte[] bild) {
            this.bild = bild;
            return this;
        }

        @Override
        public Properties addArtikelGruppe(Identifier bezeichnung, Description beschreibung, String typ) {
            gruppen.add(new DefaultArtikelgruppe(bezeichnung, beschreibung, typ));
            return this;
        }

        @Override
        public ArtikelAdapter build() {
            return this;
        }

        @Override
        public int getArtikelId() {
            return artikelId;
        }

        @Override
        public byte[] getBild() {
            return bild;
        }

        @Override
        public Optional<Description> getBeschreibung() {
            return Optional.ofNullable(beschreibung);
        }

        @Override
        public Identifier getBezeichnung() {
            return bezeichnung;
        }

        @Override
        public Money getPreis() {
            return preis;
        }

        @Override
        public Set<Artikelgruppe> getArtikelGruppen() {
            return gruppen;
        }

    }

    public class DefaultArtikelgruppe implements ArtikelAdapter.Artikelgruppe {

        private final Identifier bezeichnung;
        private final Description beschreibung;
        private final String typ;

        public DefaultArtikelgruppe(Identifier bezeichnung, Description beschreibung, String typ) {
            this.bezeichnung = bezeichnung;
            this.beschreibung = beschreibung;
            this.typ = typ;
        }


        @Override
        public Identifier getBezeichnung() {
            return bezeichnung;
        }

        @Override
        public Description getBeschreibung() {
            return beschreibung;
        }

        @Override
        public String getTyp() {
            return typ;
        }

        @Override
        public int compareTo(ArtikelAdapter.Artikelgruppe o) {
            return getBezeichnung().compareTo(o.getBezeichnung());
        }

    }
}
