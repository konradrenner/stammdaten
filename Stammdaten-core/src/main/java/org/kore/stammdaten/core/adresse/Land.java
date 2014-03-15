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
package org.kore.stammdaten.core.adresse;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 *
 * @author koni
 */
@Embeddable
public class Land implements Serializable {
    @Size(min = 2, max = 2)
    private String iso3166Code;
    
    protected Land(){
        //JPA
    }
 
    public Land(String iso3166Code) {
        this.iso3166Code = iso3166Code;
    }

    public String getIso3166Code() {
        return iso3166Code;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.iso3166Code != null ? this.iso3166Code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Land other = (Land) obj;
        if ((this.iso3166Code == null) ? (other.iso3166Code != null) : !this.iso3166Code.equals(other.iso3166Code)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Land{" + "iso3166Code=" + iso3166Code + '}';
    }
    
    
}
