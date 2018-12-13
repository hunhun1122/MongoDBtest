package com.gwm.monodb.service;

import com.gwm.monodb.dao.entity.UserInfo;
import com.gwm.monodb.service.BaseService;

public interface UserInfoService extends BaseService<UserInfo> {

	public boolean login(String loginId,String password);
	public UserInfo findById(String id);
	public void removeById(String id);
}
