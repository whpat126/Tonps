package com.pt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pt.dao.UserDao;
import com.pt.domain.Users;
import com.pt.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDao")
	private UserDao ud;

	public boolean adminValidate(Users user) {

		return ud.adminValidate(user);
	}

	public String userValidate(Users user) {

		return ud.userValidate(user);
	}

}
