/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.kore.blueprint.publishingcompany.boundary.externalservice;

import org.kore.blueprint.publishingcompany.boundary.externalservice.jaxrs.CurrencyServiceClient;
import java.math.BigDecimal;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.kore.blueprint.publishingcompany.control.CurrencyService;
import org.kore.blueprint.publishingcompany.entity.author.Currency;

/**
 *
 * @author koni
 */
@ApplicationScoped
public class CurrencyServiceAdapter implements CurrencyService {

    @Inject
    @RestClient
    CurrencyServiceClient client;

    @Override
    public BigDecimal evaluateExchangeRatio(Currency from, Currency to) {
        return BigDecimal.valueOf(client.getConversionRate(from.getValue(), to.getValue()).conversionRate);
    }

}
