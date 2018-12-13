package com.gwm.monodb.service;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public interface BaseService<T> {

    public List<T> findAll() ;  
    public void save(T entity);
    public void saveOrUpdate(T entity);
    public T findOne(Query query);
    public void remove(Query query);
}
