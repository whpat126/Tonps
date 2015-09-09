package com.pt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pt.base.BaseServiceImpl;
import com.pt.dao.UsersDao;
import com.pt.domain.Users;
import com.pt.service.UsersService;

@Service("userService")
public class UsersServiceImpl extends BaseServiceImpl<Users> implements UsersService {

	@Autowired
	@Qualifier("userDao")
	private UsersDao userDao;

//	public boolean adminValidate(Users user) {
//		return userDao.adminValidate(user);
//	}
//
//	public String userValidate(Users user) {
//
//		System.out.println("userService----qudiaolezhujieming");
//		return userDao.userValidate(user);
//	}

	@Override
	public boolean adminLogin(Users user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userLogin(Users user) {
		// TODO Auto-generated method stub
		return userDao.userLogin(user);
	}

	@Override
	public boolean userValidate(String userName) {
		
		return userDao.userValidate(userName);
	}

	@Override
	public boolean findByProp(Users user) {
		// TODO Auto-generated method stub
		return userDao.findByProp(user);
	}

}
