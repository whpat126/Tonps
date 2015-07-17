package com.pt.service;

import com.pt.base.BaseService;
import com.pt.domain.Users;

public interface RoleService extends BaseService<Users> {

	boolean adminValidate(Users user);

	String userValidate(Users user);
	/**
	 * 添加一个用户
	 * author：songqi
	 * @param user
	 * @return 该用户名已经存在，添加成功，添加失败
	 */
	
}
