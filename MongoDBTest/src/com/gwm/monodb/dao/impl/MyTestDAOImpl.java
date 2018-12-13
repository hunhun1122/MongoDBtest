package com.gwm.monodb.dao.impl;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.gwm.monodb.dao.entity.Person;

@Repository("myTestDAOImpl")
public class MyTestDAOImpl extends BaseMongoDAOImpl<Person> {

	
}
