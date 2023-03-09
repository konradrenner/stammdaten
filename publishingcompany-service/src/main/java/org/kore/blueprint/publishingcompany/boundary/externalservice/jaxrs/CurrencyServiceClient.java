/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.kore.blueprint.publishingcompany.boundary.externalservice.jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author koni
 */
@Path("/conversion-rates")
@RegisterRestClient(configKey = "currency-service-api")
public interface CurrencyServiceClient {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    ConversionRate getConversionRate(@QueryParam("fromCurrency") String fromCurrency, @QueryParam("toCurrency") String toCurrency);

    class ConversionRate {

        public String fromCurrency;
        public String toCurrency;
        public double conversionRate;

        @Override
        public String toString() {
            return "ConversionRate{" + "fromCurrency=" + fromCurrency + ", toCurrency=" + toCurrency + ", conversionRate=" + conversionRate + '}';
        }

    }
}
