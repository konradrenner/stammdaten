/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.kore.stammdaten.lager.rest;

import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.kore.stammdaten.core.adresse.Land;
import org.kore.stammdaten.lager.application.versandkosten.VersandkostenBean;
import org.kore.stammdaten.lager.dto.versandkosten.VersandkostenDTO;

/**
 *
 * @author Konrad Renner
 */
@Path("versandkostenService")
//@Consumes({"application/json"})
//@Produces({"application/json"})
@Consumes({"text/plain"})
@Produces({"text/plain"})
@RequestScoped
public class VersandkostenResource{

    @Inject
    VersandkostenBean bean;

    @GET
    public String getAll() {
        Collection<VersandkostenDTO> all = bean.getAll();

        return all.toString();
    }

    @GET
    @Path("/land/{land}")
    public String getDetail(@PathParam("land") String land) {

        return bean.getDetail(new Land(land)).toString();
    }
}
