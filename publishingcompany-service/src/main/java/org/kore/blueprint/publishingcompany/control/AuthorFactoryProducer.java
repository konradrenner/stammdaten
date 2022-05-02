/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.control;

import org.kore.blueprint.publishingcompany.entity.author.AuthorFactory;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.validation.Validator;

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
