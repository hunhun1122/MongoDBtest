package com.gwm.monodb.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gwm.monodb.dao.entity.Person;
import com.gwm.monodb.service.MyTestService;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;

@Service("myTestServiceImpl")
public class MyTestServiceImpl extends BaseServiceImpl<Person> implements MyTestService{

	public List<Person> findByName(String name)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		return baseMongoDAO.find(query);
	}
	
}
