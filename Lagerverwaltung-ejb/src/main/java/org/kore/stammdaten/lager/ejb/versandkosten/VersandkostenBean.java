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
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
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
@Stateful
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@ConversationScoped
public class VersandkostenBean {

    @PersistenceContext(name = "jdbc/stammdatenDB", type = PersistenceContextType.EXTENDED)
    EntityManager em;

    @Inject
    VersandkostenService service;
    @Inject
    VersandkostenFactory factory;
    @Inject
    VersandkostenRepository repository;
    @Inject
    LagerverwaltungUmrechner translator;
    private Land actualAuswahl;
    private VersandkostenDTO dto;

    public VersandkostenDTO getVersandkosten() {
        VersandkostenDTO response = new VersandkostenDTO();

        Versandkosten kosten = repository.find(actualAuswahl);

        mapping(kosten, response);

        return response;
    }

    public Collection<VersandkostenDTO> getAllVersandkosten() {
        List<Versandkosten> list = repository.find();
        
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

        Versandkosten entity = repository.find(land);
        service.changeBetrag(entity, betrag, translator);
        service.changeFreibetrag(entity, freibetrag, translator);

        em.merge(entity);

    }

    public void removeVersandkosten() {
        //TODO aus request auslesen
        Land land = new Land("AT");
        
        Versandkosten entity = repository.find(land);
        em.remove(entity);
    }

    public Land getActualAuswahl() {
        return actualAuswahl;
    }

    public void setActualAuswahl(Land actualAuswahl) {
        this.actualAuswahl = actualAuswahl;
    }

    public VersandkostenDTO getDto() {
        this.dto = getVersandkosten();
        return dto;
    }

    public void setDto(VersandkostenDTO dto) {
        this.dto = dto;
    }

    private void mapping(Versandkosten quelle, VersandkostenDTO ziel) {
        ziel.setBetrag(quelle.getBetrag());
        ziel.setFreibetrag(quelle.getFreibetrag());
        ziel.setLand(quelle.getLand());
    }

    @Produces
    @org.kore.stammdaten.domain.Versandkosten
    public EntityManager getVersandkostenEntityManager() {
        return this.em;
    }
}
