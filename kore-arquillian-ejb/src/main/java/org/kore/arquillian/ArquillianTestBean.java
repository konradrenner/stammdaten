/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.arquillian;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author martin
 */
@Stateless
public class ArquillianTestBean implements ArquillianTestService{
    
    @PersistenceContext(unitName = "kore-arquillian-ejb")
    private EntityManager em;

    @Override
    public List<Testdata> getTestdata() {
        return em.createNamedQuery("TestdataEntity.findAll", Testdata.class).getResultList();
    }


}
