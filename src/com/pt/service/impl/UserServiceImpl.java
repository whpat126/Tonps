package com.pt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pt.base.BaseServiceImpl;
import com.pt.dao.UserDao;
import com.pt.dao.impl.UserDaoImpl;
import com.pt.domain.Users;
import com.pt.service.UserService;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<Users> implements UserService {

//	@Autowired
//	@Qualifier("userDao")
//	private UserDao ud;

	public boolean adminValidate(Users user) {
		return userDao.adminValidate(user);
	}

	public String userValidate(Users user) {

		System.out.println("userService----qudiaolezhujieming");
		return userDao.userValidate(user);
	}

}
