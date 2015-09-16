package com.pt.service;

import java.util.List;

import com.pt.base.BaseService;
import com.pt.domain.MyCompany;
import com.pt.domain.Users;
import com.pt.domain.Users_MyCompany;

public interface MyCompanyService extends BaseService<MyCompany>{

	/**
	 * 查询用户的身份，非企业用户和企业用户两种
	 * @author：songqi
	 * @param userId
	 * @return 0表示非企业用户 1 表示为企业用户 2表示程序运行出错
	 */
	int findByUserId(String userId);

	/**
	 * 
	 * @author：songqi
	 * @param useId
	 * @return
	 */
	List<MyCompany> findAll(String useId);
	/**
	 * 查询单位信息，
	 * @author：songqi
	 * @param companyName
	 * @return null 表示单位不存在，反之，则存在
	 */
	MyCompany findByName(String companyName);

	/**
	 * 普通用户： 申请加入企业，先判断该用户是否做过同类申请，然后在确定是否将申请写入数据库
	 * @param userId 用户的主键
	 * @param pk_company 企业的主键
	 * @return 0未申请，1申请中不可重复申请 2申请成功，3申请失败
	 */
	int joinCompany(String userId, String pk_company);

	/**
	 * 用户创建企业，重写了父类方法，原因是需要先判断该企业是否已经存在
	 * @author:宋琪
	 * @param company
	 * @return true 表示企业创建成功，false 表示创建失败
	 */
	boolean save(MyCompany company);
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

	/**
	 *  企业管理员查看本单位的所有用户
	 * @author：songqi
	 * @param pk_myCompany 根据单位的主键查询
	 * @return 本单位的所有用户信息
	 */

	List<Users> queryUserByCompanyId(String pk_myCompany);

	/**
	 *  企业管理员根据企业和用户的id，将用户升级为企业管理员
	 * @author：songqi 
	 * @param pk_company 企业的id
	 * @param pk_users 用户的id
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
	 * 用户的我的企业功能，查询当前用户加入的所有企业
	 * @author：songqi
	 * @param userId
	 * @return
	 */
	List<MyCompany> findUserCompanyByUserId(String userId);

	/**
	 * 批量同意用户加入企业
	 * @author：songqi
	 * @param joinCompany
	 * @param applyAdmin
	 * @return
	 */
	void usersApply(String joinCompany, String applyAdmin);

//	List<MyCompany> findAll(String username, int currentPage, int pageSize);
}
