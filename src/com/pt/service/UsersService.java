package com.pt.service;

import com.pt.base.BaseService;
import com.pt.domain.Users;

public interface UsersService extends BaseService<Users> {

	boolean adminLogin(Users user);

	boolean userLogin(Users user);
	/**
	 * 添加一个用户
	 * author：songqi
	 * @param user
	 * @return 该用户名已经存在，添加成功，添加失败
	 */

	boolean userValidate(String userName);
	
	
	boolean findByProp(Users user);
}
