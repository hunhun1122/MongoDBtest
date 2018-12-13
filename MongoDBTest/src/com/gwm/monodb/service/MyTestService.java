package com.gwm.monodb.service;

import java.util.List;

import com.gwm.monodb.dao.entity.Person;

public interface MyTestService extends BaseService<Person> {
	
	public List<Person> findByName(String name);

}
