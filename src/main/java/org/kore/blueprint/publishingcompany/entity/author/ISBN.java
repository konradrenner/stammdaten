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
public final class ISBN extends PublicationID {

    public ISBN(String isbn) {
        super(isbn);
    }

    @Override
    public String toString() {
        return "ISBN{" + getValue() + '}';
    }

}
