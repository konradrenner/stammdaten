/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.kore.blueprint.publishingcompany.boundary.jaxrs;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

/**
 *
 * @author koni
 */
public class AuthorsClient {
    
    public List<JsonAuthor> getAll(){
        Client client = null;
        try{
            client = ResteasyClientBuilder.newBuilder().build();
            WebTarget target = client.target("http://localhost:8080/authors"); // read from property or some kind of application config
            try(Response response = target.request().get()){

                if(response.getStatus() < 200 || response.getStatus() > 399){
                    throw new IllegalStateException("Got an error from server:"+response.getStatus());
                }

                return response.readEntity(new GenericType<List<JsonAuthor>>() {});
            }
        }finally{
            Optional.ofNullable(client).ifPresent(cl -> cl.close());
        }
    }
}
