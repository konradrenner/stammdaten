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
import org.kore.stammdaten.lager.adapter.VersandkostenAdapter;
import org.kore.stammdaten.lager.application.versandkosten.VersandkostenBean;

/**
 *
 * @author Konrad Renner
 */
@Path("versandkostenService")
//@Consumes({"application/xml"})
//@Produces({"application/xml"})
@Consumes({"application/json"})
@Produces({"application/json"})
@RequestScoped
public class VersandkostenResource{

    @Inject
    VersandkostenBean bean;

    @Inject
    DefaultVersandkostenAdapterFactory adapterFactory;

    @GET
    public Collection<Versandkosten> getAll() {
        Collection<VersandkostenDTO> all = bean.getAll(adapterFactory);

        ArrayList<Versandkosten> ret = new ArrayList<>(all.size());

        for (VersandkostenAdapter dto : all) {
            Versandkosten.Builder builder = new Versandkosten.Builder(dto.getLand().getValue(), dto.getBetrag());
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
        VersandkostenDTO dto = bean.getDetail(adapterFactory, new Land(land));

        Versandkosten.Builder builder = new Versandkosten.Builder(dto.getLand().getValue(), dto.getBetrag());
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
    public void updateDetail(Versandkosten vkosten) {
        VersandkostenDTO dto = new VersandkostenDTO();
        dto.land(new Land(vkosten.getLand()));
        dto.betrag(vkosten.getBetrag());
        dto.freibetrag(vkosten.getFreibetrag());

        bean.update(dto);
    }
}
