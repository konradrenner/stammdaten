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

import javax.enterprise.context.RequestScoped;
import org.kore.runtime.specifications.Description;
import org.kore.runtime.specifications.Identifier;
import org.kore.stammdaten.core.adresse.EMail;
import org.kore.stammdaten.lager.adapter.LagerAdapter;
import org.kore.stammdaten.lager.adapter.LagerAdapterBuilder;

/**
 *
 * @author Konrad Renner
 */
@RequestScoped
public class DefaultLagerAdapterBuilder implements LagerAdapterBuilder<DefaultLagerAdapterBuilder.DefaultLagerAdapter> {

    @Override
    public DefaultLagerAdapter newInstance(Short lagerid, Identifier bezeichnung) {
        return new DefaultLagerAdapter(lagerid, bezeichnung);
    }

    public DefaultLagerAdapter newInstance(LagerModel model) {
        DefaultLagerAdapter adapter = new DefaultLagerAdapter(model.getLagerid(), new Identifier(model.getBezeichnung()));

        if (model.getBeschreibung() != null) {
            adapter.beschreibung(new Description(model.getBeschreibung()));
        }

        if (model.getEmail() != null) {
            adapter.email(new EMail(model.getEmail()));
        }

        return adapter;
    }

    public class DefaultLagerAdapter implements LagerAdapter, LagerAdapterBuilder.Properties {

        private final Identifier bezeichnung;
        private final Short lagerid;
        private EMail email;
        private Description beschreibung;

        public DefaultLagerAdapter(Short lagerid, Identifier bezeichnung) {
            this.bezeichnung = bezeichnung;
            this.lagerid = lagerid;
        }

        
        @Override
        public Description getBeschreibung() {
            return beschreibung;
        }
        
        @Override
        public Identifier getBezeichnung() {
            return bezeichnung;
        }
        
        @Override
        public EMail getEmail() {
            return email;
        }
        
        @Override
        public Short getLagerId() {
            return lagerid;
        }
        
        @Override
        public LagerAdapterBuilder.Properties beschreibung(Description beschreibung) {
            this.beschreibung = beschreibung;
            return this;
        }
        
        @Override
        public LagerAdapterBuilder.Properties email(EMail mail) {
            this.email = mail;
            return this;
        }
        
        @Override
        public DefaultLagerAdapter build() {
            return this;
        }
        
    }
}
