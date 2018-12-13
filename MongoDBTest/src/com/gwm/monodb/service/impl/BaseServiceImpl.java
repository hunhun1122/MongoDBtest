package com.gwm.monodb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.mongodb.core.query.Query;

import com.gwm.monodb.dao.BaseMongoDAO;
import com.gwm.monodb.service.BaseService;


public class BaseServiceImpl<T> implements BaseService<T>{

	@Autowired
	protected BaseMongoDAO<T> baseMongoDAO;
	/**
	 * 查询所有用户实体
	 */
    public List<T> findAll()
    {
    	return baseMongoDAO.findAll();
    }
    
    public void save(T entity)
    {
    	baseMongoDAO.save(entity);
    }
    
    public void saveOrUpdate(T entity)
    {
    	baseMongoDAO.saveOrUpdate(entity);
    }
   
    public T findOne(Query query)
    {
    	return baseMongoDAO.findOne(query);
    }

    public void remove(Query query)
    {
    	baseMongoDAO.remove(query);
    }
}
