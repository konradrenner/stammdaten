/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.lager.domain.versandkosten;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.NotNull;
import org.kore.stammdaten.core.adresse.Land;

/**
 *
 * @author koni
 */
@RequestScoped
public class VersandkostenRepository implements Serializable {

//    private final EntityManager em;

    public VersandkostenRepository() {
        //CDI
    }

   

    public Versandkosten find(@NotNull Land land) {
//        TypedQuery<Versandkosten> query = em.createNamedQuery("Versandkosten.findByLand",Versandkosten.class);
//        query.setParameter("land", land.getIso3166Code());
//        
//        return query.getSingleResult();
        return null;
    }
    
    public List<Versandkosten> find() {
//        TypedQuery<Versandkosten> query = em.createNamedQuery("Versandkosten.findAll", Versandkosten.class);
//        return Collections.unmodifiableList(query.getResultList());
        return Collections.EMPTY_LIST;
    }
}
