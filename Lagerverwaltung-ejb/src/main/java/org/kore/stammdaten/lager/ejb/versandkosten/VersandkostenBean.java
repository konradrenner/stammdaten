/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.lager.ejb.versandkosten;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.kore.runtime.currency.Money;
import org.kore.stammdaten.core.adresse.Land;
import org.kore.stammdaten.domain.versandkosten.Versandkosten;
import org.kore.stammdaten.domain.versandkosten.VersandkostenFactory;
import org.kore.stammdaten.domain.versandkosten.VersandkostenRepository;
import org.kore.stammdaten.domain.versandkosten.VersandkostenService;
import org.kore.stammdaten.lager.ejb.translator.LagerverwaltungUmrechner;

/**
 *
 * @author koni
 */
@Named("versandkosten")
@Stateless
public class VersandkostenBean {

    @PersistenceContext(name = "jdbc/stammdatenDB")
    EntityManager em;

    @Inject
    VersandkostenService service;
    @Inject
    VersandkostenFactory factory;
    @Inject
    VersandkostenRepository repository;
    @Inject
    LagerverwaltungUmrechner translator;

    public VersandkostenDTO getVersandkosten() {
        VersandkostenDTO response = new VersandkostenDTO();
        //TODO Aus Request die Daten laden
        Land land = new Land("AT");

        Versandkosten kosten = repository.find(em, land);

        mapping(kosten, response);

        return response;
    }

    public Collection<VersandkostenDTO> getAllVersandkosten() {
        List<Versandkosten> list = repository.find(em);
        
        ArrayList<VersandkostenDTO> response = new ArrayList<>(list.size());
        
        for (Versandkosten entity : list) {
            VersandkostenDTO dto = new VersandkostenDTO();
            
            mapping(entity, dto);
            
            response.add(dto);
        }
        
        return response;
    }

    public void createVersandkosten() {
        //TODO aus request auslesen
        Land land = new Land("AT");
        Money betrag = new Money(BigDecimal.ZERO, Currency.getInstance("EUR"));
        Money freibetrag = new Money(BigDecimal.ZERO, Currency.getInstance("EUR"));
        
        Versandkosten entity = factory.create(land, betrag, freibetrag);
        em.persist(entity);
    }

    public void changeVersandkosten() {
        //TODO aus request auslesen
        Land land = new Land("AT");
        Money betrag = new Money(BigDecimal.ZERO, Currency.getInstance("EUR"));
        Money freibetrag = new Money(BigDecimal.ZERO, Currency.getInstance("EUR"));

        Versandkosten entity = repository.find(em, land);
        service.changeBetrag(entity, betrag, translator);
        service.changeFreibetrag(entity, freibetrag, translator);

        em.merge(entity);

    }

    public void removeVersandkosten() {
        //TODO aus request auslesen
        Land land = new Land("AT");
        
        Versandkosten entity = repository.find(em, land);
        em.remove(entity);
    }
    
    private void mapping(Versandkosten quelle, VersandkostenDTO ziel) {
        ziel.setBetrag(quelle.getBetrag());
        ziel.setFreibetrag(quelle.getFreibetrag());
        ziel.setLand(quelle.getLand());
    }
}
