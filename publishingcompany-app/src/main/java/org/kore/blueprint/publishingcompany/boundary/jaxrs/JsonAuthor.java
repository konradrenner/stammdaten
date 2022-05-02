/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import java.util.ArrayList;
import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.kore.blueprint.publishingcompany.entity.Author;
import org.kore.blueprint.publishingcompany.entity.Name;

/**
 * Auf Kapselung kann verzichtet werden, da diese Klassen ausschliesslich fuer
 * JSON-B Mapping benutzt werden! => Siehe auch Kommentar in Klasse
 * PersistentPublication ueber die Vorteile dieser Architektur
 *
 * @author Konrad Renner
 */
public class JsonAuthor {

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

    public Collection<@Valid JsonBook> books;
    public Collection<@Valid JsonBlogPost> blogPosts;

    public JsonAuthor() {
        books = new ArrayList<>();
        blogPosts = new ArrayList<>();
    }

    public Author toEntity(){
        return new Author(new Name(firstname, lastname));
    }

    @Override
    public String toString() {
        return "JsonAuthor{" + "authorId=" + authorId + ", firstname=" + firstname + ", lastname=" + lastname + ", books=" + books + ", blogPosts=" + blogPosts + '}';
    }
}
