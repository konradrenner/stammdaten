/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.kore.stammdaten.lager.rest;

import java.util.ArrayList;
import java.util.Collection;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
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
@Consumes({"application/json", "application/xml"})
@Produces({"application/json", "application/xml"})
@RequestScoped
public class VersandkostenResource{

    @Inject
    VersandkostenBean bean;

    @GET
    public Collection<Versandkosten> getAll() {
        Collection<VersandkostenDTO> all = bean.getAll();

        ArrayList<Versandkosten> ret = new ArrayList<>(all.size());

        for (VersandkostenDTO dto : all) {
            Versandkosten.Builder builder = new Versandkosten.Builder(dto.getLand(), dto.getBetrag());
            if (dto.getFreibetrag() != null) {
                builder.withFreibetrag(dto.getFreibetrag().getAmount());
            }

            ret.add(builder.build());
        }

        return ret;
    }

    @GET
    @Path("/land/{land}")
    public Versandkosten getDetail(@PathParam("land") String land) {
        VersandkostenDTO dto = bean.getDetail(new Land(land));

        Versandkosten.Builder builder = new Versandkosten.Builder(dto.getLand(), dto.getBetrag());
        if (dto.getFreibetrag() != null) {
            builder.withFreibetrag(dto.getFreibetrag().getAmount());
        }
        return builder.build();
    }

    @DELETE
    @Path("/land/{land}")
    public void deleteDetail(@PathParam("land") String land) {
        bean.delete(new Land(land));
    }

    @PUT
    @Path("/land/{land}")
    public void updateDetail(@PathParam("land") String land, Versandkosten vkosten) {
        VersandkostenDTO dto = new VersandkostenDTO();
        dto.setLand(land);
        dto.setBetrag(vkosten.getBetrag());
        dto.setFreibetrag(vkosten.getFreibetrag());

        bean.update(dto);
    }
}
