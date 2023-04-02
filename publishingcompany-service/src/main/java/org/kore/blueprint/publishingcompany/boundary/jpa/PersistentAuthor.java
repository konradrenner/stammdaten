/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.boundary.jpa;

import org.kore.blueprint.publishingcompany.entity.author.Author;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.MapKey;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Konrad Renner
 */
@Entity
@Table(name = "author")
@NamedQuery(name = PersistentAuthor.FIND_ALL, query = "SELECT author from PersistentAuthor author")
public class PersistentAuthor implements Serializable {

    static final String FIND_ALL = "PersistentAuthor.findAll";

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @NotNull
    private UUID id;

    @Column
    @NotNull
    @NotBlank
    @Size(min = 1, max = 30)
    private String firstname;

    @Column
    @NotNull
    @NotBlank
    @Size(min = 1, max = 30)
    private String lastname;

    // Da immer ueber Aggregate Root Entitaet (Author) persistiert wird, koennen die Cascades unprobelmatisch durchgefuehrt werden (delete nicht, da ein Publikation von mehreren Autoren sein kann)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "publicationauthor",
            joinColumns = @JoinColumn(name = "authorID"),
            inverseJoinColumns = @JoinColumn(name = "publicationID"))
    @MapKey(name = "id")
    private Map<@NotNull String, @NotNull @Valid PersistentPublication> publications;

    Collection<PersistentPublication> getPublications() {
        //im moment sind nur Aenderungen an Buechern moeglich. Das Beispiel verdeutlicht das der Aggergate Root fuer eine optimale Kapselung und Konsistenz sorgt
        return publications.values();
    }

    void updateBook(@NotNull @Valid PersistentBook book) {
        PersistentBook oldbook = (PersistentBook) publications.get(book.getId());
        if (oldbook == null) {
            publications.put(book.getId(), book);
            return;
        }
        oldbook.setPrice(book.getPrice());
    }

    public PersistentAuthor() {
        publications = new LinkedHashMap<>();
    }

    PersistentAuthor(Map<String, PersistentPublication> publications) {
        this.publications = publications;
    }

    public PersistentAuthor mapFrom(@NotNull @Valid Author origin) {
        id = origin.getId();
        firstname = origin.getName().getFirstName();
        lastname = origin.getName().getLastName();

        origin.getAllBlogPost().stream().map(PersistentBlogPost::new).forEach(post -> publications.put(post.getId(), post));
        origin.getAllBooks().stream().map(PersistentBook::new).forEach(this::updateBook);

        return this;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<PersistentBook> getBooks() {
        LinkedHashSet<PersistentBook> ret = new LinkedHashSet<>();
        publications.values()
                .stream()
                .filter(publication -> publication.getPublicationtype() == '1')
                .map(publication -> (PersistentBook) publication)
                .forEach(ret::add);
        return ret;
    }

    public Set<PersistentBlogPost> getBlogPosts() {
        LinkedHashSet<PersistentBlogPost> ret = new LinkedHashSet<>();
        publications.values()
                .stream()
                .filter(publication -> publication.getPublicationtype() == '2')
                .map(publication -> (PersistentBlogPost) publication)
                .forEach(ret::add);
        return ret;
    }

    @Override
    public String toString() {
        return "PersistentAuthor{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", publications=" + publications + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersistentAuthor other = (PersistentAuthor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
