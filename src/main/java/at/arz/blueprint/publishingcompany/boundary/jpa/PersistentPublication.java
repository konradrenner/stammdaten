/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.arz.blueprint.publishingcompany.boundary.jpa;

import at.arz.blueprint.publishingcompany.entity.author.Publication;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 * Vorteil wenn JPA und Domain Entitaeten entkoppelt sind:
 *
 * * Datenmodell und Domainmodel sind nur lose gekoppelt <br>
 * * Es kann ruecksichtig genommen werden auf alle Eigenheiten des Tools <br>
 * ** Serializeable implementiereen <br>
 * ** public default Konstruktor fuer manche JPA Implementierungen erforderlich
 * <br>
 * ** Spezifische equals Implementierungen je nach Proxying oder Byte code
 * enhancement <br>
 * ** Koennen echte POJOs sein, Ruecksicht auf Datenkapselung muss nicht ganz
 * streng sein (in der Logik darf diese Klasse nicht verwendet werden) <br>
 * ** 1:1 Generierung aus Datenbank moeglich <br>
 * ** Eventuelle spezifische Persistenzlogiken abbildbar ohne das sie fachliche
 * Aussagekraft stoeren (beforePersist, transient, usw <br>
 *
 * @author rpri182
 */
@Entity
@Table(name = "publication")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "publicationtype")
public abstract class PersistentPublication implements Serializable {

    @Id
    @Column(updatable = false, nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 13, max = 13)
    private String id;

    @Column
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private String title;

    @Column
    @NotNull
    @NotBlank
    @Size(min = 1, max = 300)
    private String description;

    @Column(columnDefinition = "TIMESTAMP")
    @NotNull
    @Past
    private LocalDateTime publishingDate;

    @Column(updatable = false, insertable = false, nullable = false)
    private char publicationtype;

    public PersistentPublication() {
    }

    public PersistentPublication(Publication origin) {
        id = origin.getId().getValue();
        title = origin.getTitle();
        description = origin.getDescription();
        publishingDate = origin.getPublishingDate();
    }

    void setPublicationType(char value) {
        this.publicationtype = value;
    }

    @Override
    public String toString() {
        return "PersistentPublication{" + "id=" + id + ", title=" + title + ", description=" + description + ", publishingDate=" + publishingDate + '}';
    }

    public String getId() {
        return id;
    }

    char getPublicationtype() {
        return publicationtype;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(LocalDateTime publishingDate) {
        this.publishingDate = publishingDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final PersistentPublication other = (PersistentPublication) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
