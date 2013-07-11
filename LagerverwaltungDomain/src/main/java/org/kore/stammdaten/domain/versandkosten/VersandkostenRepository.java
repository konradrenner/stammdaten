/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.domain.versandkosten;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.kore.stammdaten.core.adresse.Land;

/**
 *
 * @author koni
 */
public class VersandkostenRepository {
    
    public Versandkosten find(EntityManager em, Land land){
        TypedQuery<Versandkosten> query = em.createNamedQuery("Versandkosten.findByLand",Versandkosten.class);
        query.setParameter("land", land.getIso3166Code());
        
        return query.getSingleResult();
    }
    
    public List<Versandkosten> find(EntityManager em) {
        TypedQuery<Versandkosten> query = em.createNamedQuery("Versandkosten.findAll", Versandkosten.class);
        return Collections.unmodifiableList(query.getResultList());
    }
}
