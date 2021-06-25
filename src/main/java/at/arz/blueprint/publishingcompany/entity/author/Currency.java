/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.arz.blueprint.publishingcompany.entity.author;

import at.arz.blueprint.publishingcompany.entity.constraints.ValueObject;

/**
 *
 * @author rpri182
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
