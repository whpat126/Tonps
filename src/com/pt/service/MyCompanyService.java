package com.pt.service;

import com.pt.base.BaseService;
import com.pt.domain.MyCompany;

public interface MyCompanyService extends BaseService<MyCompany>{

	/**
	 * 查询用户的身份，非企业用户和企业用户两种
	 * author：songqi
	 * @param userId
	 * @return false表示非企业用户 true 表示为企业用户
	 */
	boolean findByUserId(String userId);

	/**
	 * 查询单位信息，
	 * author：songqi
	 * @param companyName
	 * @return null 表示单位不存在，反之，则存在
	 */
	MyCompany findByName(String companyName);

	/**
	 * 普通用户： 申请加入企业
	 * @param userId 用户的主键
	 * @param pk_company 企业的主键
	 * @return false表示加入失败，true表示加入成功
	 */
	boolean joinCompany(String userId, String pk_company);

	/**
	 * 普通用户： 申请成为管理员
	 * @param userId
	 * @param pk_myCompany
	 * @return true 表示申请成功
	 */
	boolean applyAdmin(String userId, String pk_myCompany);

	/**
	 * 普通用户： 退出企业
	 * @param userId
	 * @param pk_myCompany
	 * @return true 表示退出企业成功
	 */
	boolean outCompany(String userId, String pk_myCompany);
}
