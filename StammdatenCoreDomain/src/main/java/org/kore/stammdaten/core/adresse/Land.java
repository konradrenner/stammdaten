/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.core.adresse;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 *
 * @author koni
 */
@Embeddable
public class Land {
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
