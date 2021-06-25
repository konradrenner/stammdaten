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
public final class ISBN extends PublicationID {

    public ISBN(String isbn) {
        super(isbn);
    }

    @Override
    public String toString() {
        return "ISBN{" + getValue() + '}';
    }

}
