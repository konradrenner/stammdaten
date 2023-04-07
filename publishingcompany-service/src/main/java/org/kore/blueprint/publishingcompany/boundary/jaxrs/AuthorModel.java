/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import org.kore.blueprint.publishingcompany.entity.author.Author;
import java.util.ArrayList;
import java.util.Collection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * Auf Kapselung kann verzichtet werden, da diese Klassen ausschliesslich fuer
 * JSON-B Mapping benutzt werden! => Siehe auch Kommentar in Klasse
 * PersistentPublication ueber die Vorteile dieser Architektur
 *
 * @author Konrad Renner
 */
public class AuthorModel {

    public static final String UUID_REGEX = "([a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}){1}";

    //not null pruefung an der Stelle nicht aktiviert, da bei Anlage keine ID mitgesendet werden muss. An dieser Stelle laesst sich sehr schoen ein moeglicher Unterschied zwischen Entitaet und REST Modell erkennen
    @Pattern(regexp = UUID_REGEX)
    public String authorId;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 30)
    public String firstname;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 30)
    public String lastname;

    public Collection<@Valid BookModel> books;
    public Collection<@Valid BlogPostModel> blogPosts;

    public AuthorModel() {
        books = new ArrayList<>();
        blogPosts = new ArrayList<>();
    }

    public AuthorModel(Author origin) {
        books = new ArrayList<>();
        blogPosts = new ArrayList<>();

        authorId = origin.getId().toString();
        firstname = origin.getName().getFirstName();
        lastname = origin.getName().getLastName();

        origin.getAllBlogPost().stream().map(BlogPostModel::new).forEach(blogPosts::add);
        origin.getAllBooks().stream().map(BookModel::new).forEach(books::add);
    }
}
