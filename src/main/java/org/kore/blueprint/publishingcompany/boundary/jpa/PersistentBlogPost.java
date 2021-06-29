/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.boundary.jpa;

import org.kore.blueprint.publishingcompany.entity.author.BlogPost;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Konrad Renner
 */
@Entity
@DiscriminatorValue("2")
public class PersistentBlogPost extends PersistentPublication {

    @Column
    @NotNull
    @Pattern(regexp = "(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public PersistentBlogPost() {
    }

    public PersistentBlogPost(BlogPost origin) {
        super(origin);
        url = origin.getLocation().toExternalForm();
    }

    @Override
    public String toString() {
        return "PersistentBook{" + "url=" + url + ", " + super.toString() + '}';
    }
}
