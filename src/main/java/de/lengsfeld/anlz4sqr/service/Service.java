package de.lengsfeld.anlz4sqr.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

public abstract class Service<T> {

    private final Class<T> entityClass;


    public Service(Class<T> entityClass){
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    @Transactional
    public void create(T entity){
        getEntityManager().persist(entity);
    }

    public T find(long id){
        T persistentEntity = (T) getEntityManager().find(entityClass, id);
        return persistentEntity;
    }

    public List<T> createNamedQuery(String queryString){
        Query query = getEntityManager().createNamedQuery(queryString, entityClass);
        return query.getResultList();
    }

}
