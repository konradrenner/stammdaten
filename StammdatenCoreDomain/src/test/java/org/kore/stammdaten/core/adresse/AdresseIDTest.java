/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.core.adresse;

import javax.validation.Validation;
import javax.validation.executable.ExecutableValidator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author koni
 */
public class AdresseIDTest {

    public AdresseIDTest() {
    }

    @Test
    public void testConstructorValidation() throws Exception {

        ExecutableValidator executableValidator = Validation
                .buildDefaultValidatorFactory().getValidator().forExecutables();

        executableValidator.validateConstructorParameters(AdresseID.class.getConstructor(String.class), new Object[]{null});
    }
}