package com.pt.service;

import com.pt.domain.Users;

public interface UserService {

	boolean adminValidate(Users user);

	String userValidate(Users user);

	
}
