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
package org.kore.stammdaten.lager.domain.lager;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.NotNull;
import org.kore.runtime.specifications.Identifier;

/**
 *
 * @author Konrad Renner
 */
@AggregateLager
@RequestScoped
public class LagerFactory implements Serializable {

    public Lager createLager(@NotNull String adressid, @NotNull Identifier bezeichnung, Lagerraum... lagerraeume) {
        //muss noch ueberprueft werden bei neuanlage, wegen sequence
        LagerId id = new LagerId(0);
        Lager lager = new Lager(adressid, bezeichnung);
        lager.addLagerraeume(lagerraeume);
        return lager;
    }
}
