package com.pt.dao;

import java.util.List;

import com.pt.base.BaseDao;
import com.pt.domain.UserIcon;

public interface UserIconDao extends BaseDao<UserIcon> {

	/**
	 *	根据用户在页面中拖动的顺序重新排列图标顺序
	 * @author：songqi
	 * @param userId
	 * @param sortString
	 */
	boolean update(String userId, String sortString);

	/**
	 * 用户个人图标初始化就执行，该方法将数据库中存放的图标返回到页面中
	 * @author：songqi
	 * @param userId 通过用户id来获取该用户的爱常用中的图标
	 */
	List<UserIcon> findAll(String userId);


}
