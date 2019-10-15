package de.lengsfeld.anlz4sqr.service;

import de.lengsfeld.anlz4sqr.entity.VenueHistory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VenueHistoryService extends Service<VenueHistory> {

    @PersistenceContext
    private EntityManager entityManager;

    public VenueHistoryService(){
        super(VenueHistory.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
