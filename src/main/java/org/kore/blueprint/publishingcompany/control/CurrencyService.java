/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.control;

import org.kore.blueprint.publishingcompany.entity.author.Currency;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Konrad Renner
 */
public interface CurrencyService {

    @NotNull
    @DecimalMin("0.00")
    BigDecimal evaluateExchangeRatio(@NotNull Currency from, @NotNull Currency to);
}
