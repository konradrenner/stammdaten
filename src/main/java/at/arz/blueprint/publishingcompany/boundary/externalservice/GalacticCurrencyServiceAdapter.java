/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.arz.blueprint.publishingcompany.boundary.externalservice;

import at.arz.blueprint.publishingcompany.control.CurrencyService;
import at.arz.blueprint.publishingcompany.entity.author.Currency;
import java.math.BigDecimal;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author rpri182
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
