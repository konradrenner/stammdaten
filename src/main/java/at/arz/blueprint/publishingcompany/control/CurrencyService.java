/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.arz.blueprint.publishingcompany.control;

import at.arz.blueprint.publishingcompany.entity.author.Currency;
import java.math.BigDecimal;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 *
 * @author rpri182
 */
public interface CurrencyService {

    @NotNull
    @DecimalMin("0.00")
    BigDecimal evaluateExchangeRatio(@NotNull Currency from, @NotNull Currency to);
}
