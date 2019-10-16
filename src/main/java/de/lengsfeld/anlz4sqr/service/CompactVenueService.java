package de.lengsfeld.anlz4sqr.service;

import de.lengsfeld.anlz4sqr.entity.CompactVenue;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CompactVenueService extends Service<CompactVenue> {

    @PersistenceContext(unitName = "FS")
    private EntityManager entityManager;

    public CompactVenueService(){
        super(CompactVenue.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
