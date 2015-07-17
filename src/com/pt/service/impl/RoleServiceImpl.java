package com.pt.service.impl;

import org.springframework.stereotype.Service;

import com.pt.base.BaseServiceImpl;
import com.pt.domain.Users;
import com.pt.service.RoleService;

@Service("abcdService")
public class RoleServiceImpl extends BaseServiceImpl<Users> implements RoleService {

//	@Autowired
//	@Qualifier("userDao")
//	private UserDao ud;

	public boolean adminValidate(Users user) {
		return userDao.adminValidate(user);
	}

	public String userValidate(Users user) {

		System.out.println("roleservice--------------------------11111111111111--------------");
		return userDao.userValidate(user);
	}

}
