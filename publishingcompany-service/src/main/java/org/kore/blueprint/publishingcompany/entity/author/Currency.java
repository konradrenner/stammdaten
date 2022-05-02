/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.entity.author;

import org.kore.blueprint.publishingcompany.entity.constraints.ValueObject;

/**
 *
 * @author Konrad Renner
 */
@ValueObject
public enum Currency {
    GCRDT("Galactic Credits"), STONE("Stones");

    private final String value;

    private Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
