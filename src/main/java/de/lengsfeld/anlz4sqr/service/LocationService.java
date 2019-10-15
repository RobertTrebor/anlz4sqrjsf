package de.lengsfeld.anlz4sqr.service;

import de.lengsfeld.anlz4sqr.entity.Location;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class LocationService extends Service<Location> {

    @PersistenceContext
    private EntityManager entityManager;

    public LocationService(){
        super(Location.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public Location find(long id){
        Location persistentEntity = getEntityManager().find(Location.class, id);
        return persistentEntity;
    }
}
