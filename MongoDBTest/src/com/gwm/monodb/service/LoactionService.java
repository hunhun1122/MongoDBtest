package com.gwm.monodb.service;

import java.util.List;

import com.gwm.monodb.dao.entity.Locationinfo;
import com.gwm.monodb.dao.entity.Person;

public interface LoactionService extends BaseService<Locationinfo> {
	
	public List<Locationinfo> findByName(String name);

	public void datata();

	public void jisuan();

	public void xianlu();

}
