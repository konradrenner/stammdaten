package org.kore.blueprint.publishingcompany.boundary.externalservice.jaxrs;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
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
