package org.kore.blueprint.publishingcompany.boundary.externalservice;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.kore.blueprint.publishingcompany.boundary.externalservice.jaxrs.CurrencyServiceClient;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private static final Logger LOG = Logger.getLogger(CurrencyServiceAdapter.class.getName());

    @Inject
    @RestClient
    CurrencyServiceClient client;

    @WithSpan
    @Override
    public BigDecimal evaluateExchangeRatio(Currency from, Currency to) {
        LOG.log(Level.INFO, "calling currency-service to get conversion rate from: {0}; to: {1}", new String[]{from.getValue(), to.getValue()});

        CurrencyServiceClient.ConversionRate conversionRate = client.getConversionRate(from.getValue(), to.getValue());

        LOG.log(Level.INFO, "got conversion rate from: {0};", conversionRate.toString());

        return BigDecimal.valueOf(conversionRate.conversionRate);
    }

}
