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

package org.kore.stammdaten.core.adresse;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Konrad Renner
 */
@Embeddable
public class AdresseID {

    private String value;

    protected AdresseID() {
        //JPA
    }

    protected AdresseID(@NotNull(message = "org.kore.stammdaten.core.messages.notnull.AdressID") String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "AdresseID{" + "value=" + value + '}';
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdresseID)) {
            return false;
        }
        AdresseID other = (AdresseID) object;
        if ((this.value == null && other.value != null) || (this.value != null && !this.value.equals(other.value))) {
            return false;
        }
        return true;
    }
}
