/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.boundary.externalservice;

import org.kore.blueprint.publishingcompany.control.CurrencyService;
import org.kore.blueprint.publishingcompany.entity.author.Currency;
import java.math.BigDecimal;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Konrad Renner
 */
@ApplicationScoped
public class GalacticCurrencyServiceAdapter implements CurrencyService {

    @Override
    public BigDecimal evaluateExchangeRatio(Currency from, Currency to) {
        if (from.equals(to)) {
            return BigDecimal.ONE;
        }
        if (to.equals(Currency.STONE)) {
            return BigDecimal.TEN;
        }
        return new BigDecimal("0.1");
    }

}
