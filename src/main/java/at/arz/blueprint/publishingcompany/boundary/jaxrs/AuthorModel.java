/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.arz.blueprint.publishingcompany.boundary.jaxrs;

import at.arz.blueprint.publishingcompany.entity.author.Author;
import java.util.ArrayList;
import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Auf Kapselung kann verzichtet werden, da diese Klassen ausschliesslich fuer
 * JSON-B Mapping benutzt werden! => Siehe auch Kommentar in Klasse
 * PersistentPublication ueber die Vorteile dieser Architektur
 * <br>
 * mit Java 14 kann diese klasse ganz einfach ein record werden
 *
 * @author rpri182
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
