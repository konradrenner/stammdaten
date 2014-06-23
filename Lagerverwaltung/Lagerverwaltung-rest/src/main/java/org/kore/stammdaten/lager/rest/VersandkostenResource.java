/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.kore.stammdaten.lager.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.kore.stammdaten.lager.application.versandkosten.VersandkostenBean;

/**
 *
 * @author Konrad Renner
 */
@Path("versandkosten")
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
//        Collection<VersandkostenDTO> all = bean.getAll();
//
//        return all.toString();
        return "Hallo Welt!";
    }

}
