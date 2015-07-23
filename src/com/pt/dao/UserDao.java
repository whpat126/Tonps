package com.pt.dao;

import com.pt.base.BaseDao;
import com.pt.domain.User;

public interface UserDao extends BaseDao<User> {

	boolean adminLogin(User user);
	
	boolean userLogin(User user);

	boolean userValidate(String userName);

	
	
	
}
