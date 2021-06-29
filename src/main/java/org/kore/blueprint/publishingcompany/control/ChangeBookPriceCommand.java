/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.control;

import org.kore.blueprint.publishingcompany.entity.author.ISBN;
import org.kore.blueprint.publishingcompany.entity.author.Price;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Konrad Renner
 */
public interface ChangeBookPriceCommand {

    @NotNull
    UUID getAuthorId();

    @NotNull
    @Valid
    ISBN getISBN();

    @NotNull
    @Valid
    Price getNewPrice();
}
