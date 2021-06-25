/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.arz.blueprint.publishingcompany.control;

import at.arz.blueprint.publishingcompany.entity.author.ISBN;
import at.arz.blueprint.publishingcompany.entity.author.Price;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author rpri182
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
