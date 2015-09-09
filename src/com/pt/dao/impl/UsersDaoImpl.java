package com.pt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Repository;

import com.pt.base.BaseDaoImpl;
import com.pt.dao.UsersDao;
import com.pt.domain.Users;
import com.pt.utils.OurDaoUtils;

@Repository("userDao")
public class UsersDaoImpl extends BaseDaoImpl<Users> implements UsersDao {

//	private UserDaoImpl(){
//		System.out.println("zhujiezhujiezhujiezhujiezhujie");
//	}
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static String path = null;

	/**
	 * 验证管理员的用户名和密码是否一致
	 */
	public boolean adminLogin(Users user) {
		return false;
	}

	/**
	 * 验证用户的用户名和密码是否一致
	 */
	public boolean userLogin(Users user) {
		String sql = "select username from users where username=? and password=?";
		Connection conn = OurDaoUtils.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(rs);
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		
		return false;
	}

	/**
	 * 验证用户是否存在，默认返回的是true，这一点和其他类有所不同，需注意
	 * author:宋琪
	 * @param userName
	 * @return true表示已经存在，false表示不存在
	 */
	public boolean userValidate(String userName) {
//		System.out.println("dao中---》验证用户已经存在：" + userName);
		String sql = "select username from users where username=? or email=? or phone=?";
		Connection conn = OurDaoUtils.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, userName);
			pstmt.setString(3, userName);
			rs = pstmt.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(rs);
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return false;
	}

	/**
	 * 修改用户的密码前，先到数据库中查询该用户的原密码是否正确
	 * author:宋琪
	 * @param user
	 * @return true 表示用户名密码正确
	 */
	public boolean findByProp(Users user) {
		String sql = "select username from users where username=? and password=?";
		Connection conn = OurDaoUtils.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(rs);
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return false;
	}

}
