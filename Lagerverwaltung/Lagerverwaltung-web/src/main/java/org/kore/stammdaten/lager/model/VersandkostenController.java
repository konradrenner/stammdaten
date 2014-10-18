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

package org.kore.stammdaten.lager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Locale;
import java.util.Set;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.kore.stammdaten.core.adresse.Land;
import org.kore.stammdaten.lager.application.versandkosten.VersandkostenBean;

/**
 *
 * @author Konrad Renner
 */
@Named("versandkosten")
@SessionScoped
public class VersandkostenController implements Serializable {

    @Inject
    VersandkostenBean bean;

    @Inject
    DefaultVersandkostenAdapterFactory adapterFactory;

    private VersandkostenModel model;


    public Collection<VersandkostenModel> getAll() {
       
        return bean.getAll(adapterFactory);
    }

    public String loadLand(String land) {
        model = bean.getDetail(adapterFactory, new Land(land));

        return "versandkostenDetail";
    }

    public String loadUebersicht() {
        return "versandkostenUebersicht";
    }

    public String[] getCountries() {
        return Locale.getISOCountries();
    }

    public Collection<String> getCurrencies() {
        Set<Currency> availableCurrencies = Currency.getAvailableCurrencies();
        ArrayList<String> ret = new ArrayList<>(availableCurrencies.size());
        for (Currency curr : availableCurrencies) {
            ret.add(curr.getCurrencyCode());
        }
        
        Collections.sort(ret);
        return ret;
    }

    public String save() {
        bean.update(model);

        return "versandkostenUebersicht";
    }

    public String delete(String land) {
        bean.delete(new Land(land));

        return "versandkostenUebersicht";
    }

    public String create() {
        this.model = new VersandkostenModel();

        return "versandkostenDetail";
    }

    public VersandkostenModel getDetail() {
        return model;
    }
}
