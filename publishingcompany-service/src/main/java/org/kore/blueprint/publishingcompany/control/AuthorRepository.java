/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.control;

import org.kore.blueprint.publishingcompany.entity.author.Author;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Konrad Renner
 */
public interface AuthorRepository {

    @NotNull
    Set<@Valid Author> findAll();

    Optional<Author> find(@NotNull UUID id);

    @NotNull
    @Valid
    Author insert(@NotNull @Valid Author author);

    Optional<Author> update(@NotNull @Valid Author author);
}
