package com.pt.dao;

import com.pt.base.BaseDao;
import com.pt.domain.Users;

public interface UsersDao extends BaseDao<Users> {

	boolean adminLogin(Users user);
	
	boolean userLogin(Users user);

	boolean userValidate(String userName);

	boolean findByProp(Users user);

	
	
	
}
