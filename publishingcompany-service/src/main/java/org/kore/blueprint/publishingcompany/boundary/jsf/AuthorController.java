/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.boundary.jsf;

import org.kore.blueprint.publishingcompany.control.AuthorRepository;
import org.kore.blueprint.publishingcompany.entity.author.Author;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Konrad Renner
 */
@Named
@RequestScoped
public class AuthorController {

    @Inject
    AuthorRepository authorRepository;

    private Collection<AuthorViewModel> authors;

    @PostConstruct
    void init() {
        authors = new ArrayList<>();
        authorRepository.findAll()
                .stream()
                .map(this::createViewModel)
                .forEach(authors::add);
    }

    AuthorViewModel createViewModel(final Author author) {
        AuthorViewModel model = new AuthorViewModel();
        model.setFirstname(author.getName().getFirstName());
        model.setLastname(author.getName().getLastName());
        return model;
    }

    public Collection<AuthorViewModel> getAuthors() {
        return authors;
    }

}
