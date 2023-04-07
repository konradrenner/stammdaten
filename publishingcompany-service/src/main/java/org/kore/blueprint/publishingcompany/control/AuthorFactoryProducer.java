/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.control;

import org.kore.blueprint.publishingcompany.entity.author.AuthorFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.validation.Validator;

/**
 *
 * @author Konrad Renner
 */
@ApplicationScoped
public class AuthorFactoryProducer {

    @Inject
    Validator validator;

    @Produces
    public AuthorFactory produce() {
        return new AuthorFactory(validator);
    }
}
