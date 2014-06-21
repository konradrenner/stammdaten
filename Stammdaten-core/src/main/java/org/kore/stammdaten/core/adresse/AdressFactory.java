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

/**
 *
 * @author Konrad Renner
 */
public class AdressFactory implements Serializable {
    
    
    public AdressFactory(){
        
    }

    
    public Adresse.Builder createAdressBuilder(String ort, String strasse, int postleitzahl, short hausnummer, Land land, String adresszeile1){
//        ExecutableValidator forExecutables = validator.forExecutables();
//        Set<ConstraintViolation<Adresse.Builder>> violations = forExecutables.validateConstructorParameters(adrBuilderConstructor, new Object[]{ort,strasse,postleitzahl,hausnummer,land,adresszeile1});
//        
//        if(violations.isEmpty()){
            return new Adresse.Builder(land, ort, strasse, postleitzahl, hausnummer, adresszeile1);
    }
    
    public EMail createEMail(String mail){
        return new EMail(mail);
    }
    
    public Telefon.Builder createTelefonBuilder(short vorwahlLand,short vorwahl, short nummer){
        return new Telefon.Builder(vorwahlLand, nummer, vorwahl);
    }
    
    public Fax.Builder createFaxBuilder(short vorwahlLand,short vorwahl, short nummer){
        return new Fax.Builder(vorwahlLand, nummer, vorwahl);
    }
}
