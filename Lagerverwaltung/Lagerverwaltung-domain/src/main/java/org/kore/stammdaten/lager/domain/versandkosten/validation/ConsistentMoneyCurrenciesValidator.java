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
package org.kore.stammdaten.lager.domain.versandkosten.validation;

import java.util.Currency;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import org.kore.runtime.currency.Money;

/**
 * Ueberprueft ob alle Money-Parameter einer Methode die gleiche Currency haben.
 * Parameter die nicht vom Typ Money sind, werden ignoriert
 *
 * @author Konrad Renner
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class ConsistentMoneyCurrenciesValidator implements
        ConstraintValidator<ConsistentMoneyCurrencies, Object[]> {

    @Override
    public boolean isValid(Object[] values, ConstraintValidatorContext context) {
        Currency toCheck = null;
        for (Object value : values) {
            if (value instanceof Money) {
                Money money = (Money) value;
                if (toCheck == null) {
                    toCheck = money.getCurrency();
                } else if (!toCheck.equals(money.getCurrency())) {
                    return false;
                }
            } 
        }

        return true;
    }

    @Override
    public void initialize(ConsistentMoneyCurrencies constraintAnnotation) {
        //nothing at the moment
    }

}
