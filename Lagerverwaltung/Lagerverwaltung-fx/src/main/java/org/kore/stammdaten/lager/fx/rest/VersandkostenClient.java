/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.kore.stammdaten.lager.fx.rest;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
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
    private static final String BASE_URI = "http://localhost:8080/Lagerverwaltung-rest/webresources";

    public VersandkostenClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("versandkostenService");
    }

    public <T> T getAll(Class<T> responseType) throws javax.ws.rs.ClientErrorException {
        Invocation.Builder request = webTarget.request(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML);
        return request.get(responseType);
    }

    public void updateDetail(String land, Versandkosten kto) throws javax.ws.rs.ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("land/{0}", new Object[]{land})).request().put(Entity.json(kto));
    }

    public <T> T getDetail(Class<T> responseType, String land) throws javax.ws.rs.ClientErrorException {
        return webTarget.path(java.text.MessageFormat.format("land/{0}", new Object[]{land})).request(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML).get(responseType);
    }
    public void deleteDetail(String land) throws javax.ws.rs.ClientErrorException {
        webTarget.path(java.text.MessageFormat.format("land/{0}", new Object[]{land})).request().delete();
    }

    public void close() {
        client.close();
    }

}
