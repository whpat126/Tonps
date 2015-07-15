package com.pt.dao;

import com.pt.base.BaseDao;
import com.pt.domain.Users;

public interface UserDao extends BaseDao<Users> {

	boolean adminValidate(Users user);

	String userValidate(Users user);

	
	
	
}
