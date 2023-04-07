/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import org.kore.blueprint.publishingcompany.entity.author.BlogPost;
import java.net.URL;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

/**
 *
 * @author Konrad Renner
 */
public class BlogPostModel {

    @NotNull
    @NotBlank
    @Size(min = 13, max = 13)
    public String blogId;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    public String title;
    @NotNull
    @NotBlank
    @Size(min = 1, max = 300)
    public String description;
    @NotNull
    @PastOrPresent
    public LocalDateTime publishingDate;
    @NotNull
    public URL location;

    public BlogPostModel() {
        //tool
    }

    public BlogPostModel(BlogPost origin) {
        //tool
        blogId = origin.getId().getValue();
        title = origin.getTitle();
        description = origin.getDescription();
        publishingDate = origin.getPublishingDate();
        location = origin.getLocation();
    }
}
