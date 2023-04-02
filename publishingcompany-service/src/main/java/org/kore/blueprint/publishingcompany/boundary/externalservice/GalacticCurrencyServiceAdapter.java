/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.boundary.externalservice;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.kore.blueprint.publishingcompany.control.CurrencyService;
import org.kore.blueprint.publishingcompany.entity.author.Currency;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

/**
 *
 * @author Konrad Renner
 */
@Alternative
@ApplicationScoped
public class GalacticCurrencyServiceAdapter implements CurrencyService {

    private static final Logger LOG = Logger.getLogger(GalacticCurrencyServiceAdapter.class.getName());

    @WithSpan
    @Override
    public BigDecimal evaluateExchangeRatio(Currency from, Currency to) {

        LOG.log(Level.INFO, "converting from:{0} to {1}", new String[]{from.toString(), to.toString()});

        if (from.equals(to)) {
            return BigDecimal.ONE;
        }
        if (to.equals(Currency.STONE)) {
            return BigDecimal.TEN;
        }
        return new BigDecimal("0.1");
    }

}
