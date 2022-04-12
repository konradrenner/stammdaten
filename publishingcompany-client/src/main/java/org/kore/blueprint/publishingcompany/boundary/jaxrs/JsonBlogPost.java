/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import java.net.URL;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Konrad Renner
 */
public class JsonBlogPost {

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
    public LocalDateTime publishingDate;
    @NotNull
    public URL location;

    public JsonBlogPost() {
        //tool
    }

    @Override
    public String toString() {
        return "BlogPostModel{" + "blogId=" + blogId + ", title=" + title + ", description=" + description + ", publishingDate=" + publishingDate + ", location=" + location + '}';
    }
    
    
}
