/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.arz.blueprint.publishingcompany.entity.author;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 *
 * @author rpri182
 */
public class Publication {

    @NotNull
    @Valid
    private final PublicationID id;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    private final String title;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 300)
    private final String description;
    @NotNull
    @Past
    private final LocalDateTime publishingDate;

    public Publication(PublicationID id, String title, String description, LocalDateTime publishingDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publishingDate = publishingDate;
    }

    public PublicationID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getPublishingDate() {
        return publishingDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Publication other = (Publication) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", title=" + title + ", description=" + description + ", publishingDate=" + publishingDate + '}';
    }

}
