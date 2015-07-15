package com.pt.dao;

import com.pt.domain.Users;

public interface UserDao {

	boolean adminValidate(Users user);

	String userValidate(Users user);

	
	
	
}
