/*
 * Copyright (C) 2013 Free Software Foundation.
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

package org.kore.stammdaten.core.lieferant;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Konrad Renner
 */
@Embeddable
public class Lieferantnummer implements Serializable {

    private long liefnr;

    public Lieferantnummer() {
    }

    public Lieferantnummer(long liefnr) {
        if (liefnr <= 0) {
            throw new IllegalArgumentException("Artikelnummer muss groesser 0 sein");
        }
        this.liefnr = liefnr;
    }

    public long getValue() {
        return liefnr;
    }

    //TODO hashcode/equals
    @Override
    public String toString() {
        return "Lieferantnummer{" + "liefnr=" + liefnr + '}';
    }
}
