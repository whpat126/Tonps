package com.pt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pt.base.BaseDaoImpl;
import com.pt.base.DboImpl;
import com.pt.dao.MyCompanyDao;
import com.pt.domain.MyCompany;
import com.pt.domain.Users;
import com.pt.utils.OurDaoUtils;

@Repository("myCompanyDao")
public class MyCompanyDaoImpl extends BaseDaoImpl<MyCompany> implements MyCompanyDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public int findbyUserId(String userId) {
		String sql = "select state from users_mycompany where pk_users=?"; // 此处state 判断是否是企业用户 
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			List<Integer> list = new ArrayList<Integer>();
			// 判断程序是否检索到数据
			boolean check = false;
			while(rs.next()){
				check = true;
				int i = 0;
				i = Integer.parseInt(rs.getString(1));
				list.add(i);
			}
			if(!check){ // 没有找到说明肯定不是企业用户
				return 0;
			}
			if(list.contains(1) || list.contains(2)){ // 1说明是企业用户  2说明是管理员
				return 1;
			}else if(list.contains(0)){ // 0是默认，申请加入企业的用户
				return 0;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(rs);
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return 2;
	}

	@Override
	public MyCompany findeByName(String companyName) {
		String sql = "select pk_mycompany,name,pk_father,address,phone from mycompany where name=?";
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, companyName);
			rs = pstmt.executeQuery();
			MyCompany company =null ;
			while(rs.next()){
				company = new MyCompany();
				company.setPk_myCompany(rs.getString("pk_mycompany"));
				company.setName(rs.getString(2));
				company.setAddress(rs.getString("address"));
				company.setPhone(rs.getString("phone"));
			}
			return company;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(rs);
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return null;
	}

	@Override
	public boolean joinCompany(String userId, String pk_company) {
		/* 不需要指定state 状态，如果是0 表示还没有正式是本企业员工，还需要企业管理员确认。  数据库默认值为0 */
		String sql = "insert into users_mycompany(pk_users_mycompany,pk_users,pk_mycompany,state,apply) values(?,?,?,?,?)";
		conn = OurDaoUtils.getConnection();
		String pk = new DboImpl().genPk();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pk);
			pstmt.setString(2, userId);
			pstmt.setString(3, pk_company);
			pstmt.setInt(4, 0); // 还不算是企业用户
			pstmt.setInt(5, 0); // 申请加入企业
			int num = pstmt.executeUpdate();
			if(num == 1){
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return false;
	}

	@Override
	public boolean checkJoinCompany(String userId, String pk_company) {
		String sql = "select pk_users_mycompany from users_mycompany where pk_users=? and pk_mycompany=? ";
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, pk_company);
			int num = pstmt.executeUpdate();
			if(num >= 1){ // 已经做过申请
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return false;
	}

	@Override
	public boolean saveCompany(MyCompany company) {
		// 保存到mycompany表
		String sql1 = "insert into mycompany(pk_mycompany,name,address,phone) values(?,?,?,?)";
		// 保存到users_mycompany表
		String sql2 = "insert into users_mycompany(pk_users_mycompany,pk_users,pk_mycompany,state) values(?,?,?,?)";
		conn = OurDaoUtils.getConnection();
		String pk_mycompany = new DboImpl().genPk();
		String pk_users_mycompany = new DboImpl().genPk();
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, pk_mycompany);
			pstmt.setString(2, company.getName());
			pstmt.setString(3, company.getAddress());
			pstmt.setString(4, company.getPhone());
			int num = pstmt.executeUpdate();
			pstmt.close();
			if(num == 1){ 
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, pk_users_mycompany);
				pstmt.setString(2, company.getCompanyAdmin());
				pstmt.setString(3, pk_mycompany);
				pstmt.setString(4, "2"); // 设置为企业管理员
				int num2 = pstmt.executeUpdate();
				if(num2 == 1){
					return true;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return false;
	}

	@Override
	public List<Users> queryUserByCompanyId(String pk_myCompany) {
		String sql = "select a1.pk_users,username,phone,a2.pk_mycompany,a2.state from users a1 "
				+ " inner join users_mycompany a2 on a1.pk_users=a2.pk_users "
				+ " where a2.pk_mycompany=? and (a2.state=2 or a2.state=1)"; // 
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pk_myCompany);
			rs = pstmt.executeQuery();
			List<Users> list = new ArrayList<Users>();
			while(rs.next()){
				Users user = new Users();
				user.setPk_users(rs.getString(1));
				user.setUsername(rs.getString("username"));
				user.setPhone(rs.getString("phone"));
				user.setPk_mycompany(rs.getString("pk_mycompany"));
				user.setState(rs.getString("state"));
				list.add(user);
			}
			return list;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(rs);
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return null;
	}

	@Override
	public boolean update(String pk_company, String pk_users) {
		String sql = "update users_mycompany set state=?,apply=? where pk_mycompany=? and pk_users=?"; // 
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "2");
			pstmt.setString(2, "");
			pstmt.setString(3, pk_company);
			pstmt.setString(4, pk_users);
			int num = pstmt.executeUpdate();
			if(num == 1){
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(rs);
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return false;
	}

	@Override
	public boolean remove(String pk_company, String pk_users) {
		/*String sql = "delete from users_mycompany where pk_mycompany=? and pk_users=?"; // 
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pk_company);
			pstmt.setString(2, pk_users);
			int num = pstmt.executeUpdate();
			if(num == 1){
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(rs);
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return false;*/
		return true;
	}

	@Override
	public List<MyCompany> findAll(String userId) {
		String sql = "select a1.pk_mycompany, name, pk_father from mycompany a1 "
				+ " inner join users_mycompany a2 on a1.pk_mycompany=a2.pk_mycompany "
				+ " where a2.pk_users=? and state=2"; // 
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			List<MyCompany> list = new ArrayList<MyCompany>();
			while(rs.next()){
				MyCompany company = new MyCompany();
				company.setPk_myCompany(rs.getString(1));
				company.setName(rs.getString(2));
				company.setPk_father(rs.getString(3));
				list.add(company);
			}
			return list;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(rs);
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return null;
	}

	@Override
	public List<MyCompany> findUserCompanyByUserId(String userId) {
		String sql = "select a1.pk_mycompany, name, pk_father from mycompany a1 "
				+ " inner join users_mycompany a2 on a1.pk_mycompany=a2.pk_mycompany "
				+ " where a2.pk_users=? and state=1"; // 
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			List<MyCompany> list = new ArrayList<MyCompany>();
			while(rs.next()){
				MyCompany company = new MyCompany();
				company.setPk_myCompany(rs.getString(1));
				company.setName(rs.getString(2));
				company.setPk_father(rs.getString(3));
				list.add(company);
			}
			return list;
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(rs);
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return null;
	}

	@Override
	public boolean applyAdmin(String userId, String pk_myCompany) {
		String sql = "update users_mycompany set apply=? where pk_mycompany=? and pk_users=?"; // 
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "1"); // 表示用户申请成为企业管理员
			pstmt.setString(2, pk_myCompany);
			pstmt.setString(3, userId);
			int num = pstmt.executeUpdate();
			if(num == 1){
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(rs);
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return false;
	}

	@Override
	public boolean outCompany(String userId, String pk_myCompany) {
		String sql = "update users_mycompany set state=? where pk_mycompany=? and pk_users=?"; // 
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "0"); // 表示用户申请退出企业
			pstmt.setString(2, pk_myCompany);
			pstmt.setString(3, userId);
			int num = pstmt.executeUpdate();
			if(num == 1){
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(rs);
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return false;
	}

	@Override
	public void usersApply(String joinCompany, String applyAdmin) {
		String sql = "update users_mycompany set state=?, apply=? where pk_users_mycompany=?"; // 
		String[] joinCompanys = joinCompany.split(",");
		String[] applyAdmins = applyAdmin.split(",");
		conn = OurDaoUtils.getConnection();
		try {
			// 用户申请加入企业
			for(int i=0;i<joinCompanys.length;i++){
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "1");
				pstmt.setString(2, "");
				pstmt.setString(3, joinCompanys[i]);
				pstmt.executeUpdate();
				pstmt.close();
			}
			// 申请成为管理员
			for(int i=0;i<applyAdmins.length;i++){
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "2");
				pstmt.setString(2, "");
				pstmt.setString(3, applyAdmins[i]);
				pstmt.executeUpdate();
				pstmt.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(conn);
		}
	}

	

	
	
	

}

