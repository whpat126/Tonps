package com.pt.service;

import com.pt.base.BaseService;
import com.pt.domain.User;

public interface UserService extends BaseService<User> {

	boolean adminLogin(User user);

	boolean userLogin(User user);
	/**
	 * 添加一个用户
	 * author：songqi
	 * @param user
	 * @return 该用户名已经存在，添加成功，添加失败
	 */

	boolean userValidate(String userName);
	
}
