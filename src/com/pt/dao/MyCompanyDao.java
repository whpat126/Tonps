package com.pt.dao;

import java.util.List;

import com.pt.base.BaseDao;
import com.pt.domain.MyCompany;
import com.pt.domain.PublicIcon;

public interface MyCompanyDao extends BaseDao<MyCompany> {

	/**
	 *  查询用户是否是企业用户
	 * author：songqi
	 * @param userId
	 * @return false表示非企业用户
	 */
	boolean findbyUserId(String userId);

	/**
	 * 查询单位：不使用模糊查询
	 * author:宋琪
	 * @param companyName
	 * @return
	 */
	MyCompany findeByName(String companyName);


//	boolean update(String userId, String sortString);

//	List<PublicIcon> findAll(String userId);

}
