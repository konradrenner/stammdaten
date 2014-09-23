/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.kore.stammdaten.lager.fx.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.kore.stammdaten.lager.rest.Versandkosten;

/**
 * Jersey REST client generated for REST resource:VersandkostenResource
 * [versandkostenService]<br>
 * USAGE:
 * <pre>
 *        VersandkostenClient client = new VersandkostenClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Konrad Renner
 */
public class VersandkostenClient {
    private javax.ws.rs.client.WebTarget webTarget;
    private javax.ws.rs.client.Client client;
    private static final String LOCALHOST_URI = "http://localhost:8080/Lagerverwaltung-rest/webresources";
    private static final String OPENSHIFT_URI = "http://stammdt-kuk.rhcloud.com/Lagerverwaltung-rest/webresources";

    public VersandkostenClient() {
        ObjectMapper mapper = new ObjectMapper();
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        mapper.registerModule(module);

        JacksonJsonProvider provider = new JacksonJsonProvider(mapper);

        client = javax.ws.rs.client.ClientBuilder.newClient().register(provider);
        webTarget = client.target(OPENSHIFT_URI).path("versandkostenService");
    }

    public <T> T getAll(Class<T> responseType) throws javax.ws.rs.ClientErrorException {
        Invocation.Builder request = webTarget.request(MediaType.APPLICATION_JSON);
        return request.get(responseType);
    }

    public void updateDetail(String land, Versandkosten kto) throws javax.ws.rs.ClientErrorException {
        Entity<Versandkosten> json = Entity.json(kto);
        webTarget.request(MediaType.APPLICATION_JSON).put(json);
    }

    public <T> T getDetail(Class<T> responseType, String land) throws javax.ws.rs.ClientErrorException {
        WebTarget path = webTarget.path(java.text.MessageFormat.format("land/{0}", new Object[]{land}));
        Invocation.Builder request = path.request(MediaType.APPLICATION_JSON_TYPE);

        return request.get(responseType);
    }
    public void deleteDetail(String land) throws javax.ws.rs.ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("land/{0}", new Object[]{land})).request(MediaType.APPLICATION_JSON).delete();
    }

    public void close() {
        client.close();
    }

}
