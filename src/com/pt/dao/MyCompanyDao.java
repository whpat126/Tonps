package com.pt.dao;

import java.util.List;

import com.pt.base.BaseDao;
import com.pt.domain.MyCompany;
import com.pt.domain.Users;

public interface MyCompanyDao extends BaseDao<MyCompany> {

	/**
	 *  查询用户是否是企业用户
	 * @author：songqi
	 * @param userId
	 * @return 0表示非企业用户 1 表示为企业用户 2表示程序运行出错
	 */
	int findbyUserId(String userId);
	
	/**
	 * 根据用户的ID查询其身份为管理员的单位信息
	 * @author：songqi
	 * @param userId
	 * @return 符合身份的单位信息
	 */
	List<MyCompany> findAll(String userId);
	
	/**
	 * 查询单位：不使用模糊查询
	 * @author:宋琪
	 * @param companyName
	 * @return
	 */
	MyCompany findeByName(String companyName);

	/**
	 * 用户申请加入企业功能，insert的state和apply设置为0
	 * @author：songqi
	 * @param userId 申请用户的主键
	 * @param pk_company 企业的主键
	 * @return true 表示通知已经提交给企业管理员，false表示有错误 
	 */
	boolean joinCompany(String userId, String pk_company);

	/**
	 * 检查用户是否已经做过同类申请,检查apply的值是否为0
	 * @author：songqi
	 * @param userId
	 * @return true 表示做过该申请，false表示未做过同类申请
	 */
	boolean checkJoinCompany(String userId,String pk_company);
	/**
	 * 保存企业信息，同时将企业管理员设置为该用户，需要保存多个表，mycompany表，users_mycompany表
	 * @author：songqi
	 * @param company
	 * @return
	 */
	boolean saveCompany(MyCompany company);

	/**
	 * 企业管理员应用：通过企业的主键查询本单位的所有用户信息
	 * @author：songqi
	 * @param pk_myCompany
	 * @return
	 */
	List<Users> queryUserByCompanyId(String pk_myCompany);

	/**
	 * 企业管理员根据企业和用户的id，将用户升级为企业管理员，state改为2，apply为"".
	 * @author：songqi
	 * @param pk_company
	 * @param pk_users
	 * @return
	 */
	boolean update(String pk_company, String pk_users);

	/**
	 * 企业管理员根据企业和用户的id，将用户从本单位移出
	 * @author：songqi
	 * @param pk_company
	 * @param pk_users
	 * @return
	 */
	boolean remove(String pk_company, String pk_users);

	/**
	 * 用户的我的企业功能，从数据库中查询该用户的所有加入的企业
	 * @author：songqi
	 * @param userId
	 * @return
	 */
	List<MyCompany> findUserCompanyByUserId(String userId);

	/**
	 * 用户功能： 申请成为企业管理员，将用户的apply设置为1,state不变
	 * @author：songqi
	 * @param userId
	 * @param pk_myCompany
	 * @return
	 */
	boolean applyAdmin(String userId, String pk_myCompany);

	/**
	 * 用户功能：退出该企业，将用户的state设置为0即可,无需同意
	 * @author：songqi
	 * @param userId
	 * @param pk_myCompany
	 * @return
	 */
	boolean outCompany(String userId, String pk_myCompany);

	/**
	 * 批量同意用户申请加入企业，修改的时候需要update其state和apply两列。
	 * @author：songqi
	 * @param joinCompany 加入企业
	 * @param applyAdmin 申请成为管理员
	 * @return
	 */
	void usersApply(String joinCompany, String applyAdmin);

	/**
	 * 
	 * @author：songqi
	 * @param userId
	 * @return
	 */
//	List<MyCompany> findUserApply(String userId);

//	boolean update(String userId, String sortString);

//	List<MyCompany> findAll(String username, int currentPage, int pageSize);

}
