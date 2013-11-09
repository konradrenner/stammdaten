/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kore.stammdaten.lager.ejb.versandkosten;

import java.util.ArrayList;
import java.util.Collection;
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
import org.kore.menu.api.EntryUIDFactory;
import org.kore.menu.api.Menu;
import org.kore.stammdaten.core.adresse.Land;
import org.kore.stammdaten.domain.versandkosten.Versandkosten;
import org.kore.stammdaten.domain.versandkosten.VersandkostenFactory;
import org.kore.stammdaten.domain.versandkosten.VersandkostenRepository;
import org.kore.stammdaten.domain.versandkosten.VersandkostenService;
import org.kore.stammdaten.lager.ejb.menu.VersandkostenItems;
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

    @PersistenceContext(name = "stammdatenDB", type = PersistenceContextType.EXTENDED)
    EntityManager em;

    @Inject
    VersandkostenService service;
    @Inject
    VersandkostenFactory factory;
    @Inject
    VersandkostenRepository repository;
    @Inject
    LagerverwaltungUmrechner translator;
    @Inject
    Menu versandkostenMenu;
    @Inject
    EntryUIDFactory uidFactory;
    private Land actualAuswahl;
    private VersandkostenDTO dto;

    public VersandkostenDTO getVersandkosten() {
        if (dto == null) {
            dto = new VersandkostenDTO();
        }

        Versandkosten kosten = repository.find(actualAuswahl);

        mapping(kosten, dto);

        return dto;
    }

    public Collection<VersandkostenDTO> getAllVersandkosten() {
        List<Versandkosten> list = repository.find();
        
        ArrayList<VersandkostenDTO> response = new ArrayList<>(list.size());
        
        for (Versandkosten entity : list) {
            VersandkostenDTO newdto = new VersandkostenDTO();
            
            mapping(entity, newdto);
            
            response.add(newdto);
        }
        
        return response;
    }

    public String createVersandkosten() {
        Versandkosten kosten = repository.find(dto.getLand());
        
        if (kosten == null) {
            Versandkosten entity = factory.create(dto.getLand(), dto.getBetrag(), dto.getFreibetrag());
            
            dto = VersandkostenDTO.createForJPAPersist();
            dto.setBetrag(entity.getBetrag());
            dto.setFreibetrag(entity.getFreibetrag());
            dto.setLand(entity.getLand());
        } else {
            changeVersandkosten();
        }

        return VersandkostenItems.getEntry(VersandkostenItems.DETAIL_VERSANDKOSTEN, uidFactory, versandkostenMenu).getNavigationPath().asString();
    }

    public void changeVersandkosten() {
        Versandkosten entity = repository.find(dto.getLand());
        service.changeBetrag(entity, dto.getBetrag(), translator);
        service.changeFreibetrag(entity, dto.getFreibetrag(), translator);

        em.merge(entity);

    }

    public void removeVersandkosten() {
        Versandkosten entity = repository.find(dto.getLand());
        em.remove(entity);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String save() {
        if (dto.isNewVersandkosten()) {
            em.persist(dto);
        } else {
            em.merge(dto);
        }

        return VersandkostenItems.getEntry(VersandkostenItems.SAVE_VERSANDKOSTEN, uidFactory, versandkostenMenu).getNavigationPath().asString();
    }
    
    public String cancel() {
        Versandkosten entity = repository.find(dto.getLand());
        
        if (entity != null) {
            em.refresh(entity);
        }

        return VersandkostenItems.getEntry(VersandkostenItems.CANCEL_VERSANDKOSTEN, uidFactory, versandkostenMenu).getNavigationPath().asString();
    }

    public Land getActualAuswahl() {
        return actualAuswahl;
    }

    public void setActualAuswahl(Land actualAuswahl) {
        this.actualAuswahl = actualAuswahl;
    }

    private void mapping(Versandkosten quelle, VersandkostenDTO ziel) {
        if (quelle == null) {
            return;
        }

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
