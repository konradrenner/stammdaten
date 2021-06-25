/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.arz.blueprint.publishingcompany.entity.author;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 *
 * @author rpri182
 */
public class BlogPost extends Publication {

    @NotNull
    private URL url;

    public BlogPost(PublicationID id, String title, String description, LocalDateTime publishingDate, URL url) {
        super(id, title, description, publishingDate);
        this.url = url;
    }

    public URL getLocation() {
        return url;
    }

    public void changeLocation(URL url) {
        Objects.requireNonNull(url);
        this.url = url;
    }

    @Override
    public String toString() {
        return "BlogPost{" + "url=" + url + super.toString() + '}';
    }

}
