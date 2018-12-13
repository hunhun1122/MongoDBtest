package com.gwm.monodb.service.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.gwm.monodb.dao.entity.UserInfo;
import com.gwm.monodb.service.UserInfoService;

@Service("userInfoServiceImpl")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService{

	
	public boolean login(String loginID,String password)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("loginID").is(loginID));
		UserInfo user = baseMongoDAO.findOne(query);
		if(password.equals(user.getPassword()))
		{
			return true;
		}
		else
		{
			//return true;
			return false;
		}
	}
	public UserInfo findById(String id)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		UserInfo user = baseMongoDAO.findOne(query);
		return user;
	}
	
	public void removeById(String id)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		baseMongoDAO.remove(query);
	}
	
}
