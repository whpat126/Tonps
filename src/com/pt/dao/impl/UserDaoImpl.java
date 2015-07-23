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
import com.pt.dao.UserDao;
import com.pt.domain.User;
import com.pt.utils.OurDaoUtils;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

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
	@Override
	public boolean adminLogin(User user) {
		return false;
	}


	/**
	 * 验证用户的用户名和密码是否一致
	 */
	@Override
	public boolean userLogin(User user) {
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
		}
		
		return false;
	}

	/**
	 * 验证用户是否存在
	 * author:宋琪
	 * @param userName
	 * @return
	 */
	@Override
	public boolean userValidate(String userName) {
		System.out.println("验证用户已经存在：useName" + userName);
		return false;
	}

}
