/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.lager.domain.versandkosten;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import org.kore.stammdaten.core.adresse.Land;

/**
 *
 * @author koni
 */
public class VersandkostenRepository implements Serializable {

    private EntityManager em;

    VersandkostenRepository() {
        //CDI
    }

    public VersandkostenRepository(final EntityManager manager) {
        em = manager;
    }


    public Versandkosten find(@NotNull Land land) {
        return em.find(Versandkosten.class, land.getIso3166Code());
    }
    
    public List<Versandkosten> find() {
        TypedQuery<Versandkosten> query = em.createNamedQuery("Versandkosten.findAll", Versandkosten.class);
        return Collections.unmodifiableList(query.getResultList());
    }
}
