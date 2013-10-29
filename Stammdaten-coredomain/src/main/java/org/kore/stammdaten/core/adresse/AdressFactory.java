/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.core.adresse;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.Set;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;

/**
 *
 * @author koni
 */
public class AdressFactory implements Serializable {
    
    
    public AdressFactory(){
        
    }

    
    public Adresse.Builder createAdressBuilder(String ort, String strasse, int postleitzahl, short hausnummer, Land land, String adresszeile1){
//        ExecutableValidator forExecutables = validator.forExecutables();
//        Set<ConstraintViolation<Adresse.Builder>> violations = forExecutables.validateConstructorParameters(adrBuilderConstructor, new Object[]{ort,strasse,postleitzahl,hausnummer,land,adresszeile1});
//        
//        if(violations.isEmpty()){
            return new Adresse.Builder(ort, strasse, postleitzahl, hausnummer, land, adresszeile1);
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
