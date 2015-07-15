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

import com.pt.dao.UserDao;
import com.pt.domain.Users;
import com.pt.utils.OurDaoUtils;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static String path = null;

	/**
	 * 验证管理员的用户名和密码是否一致
	 */
	@Override
	public boolean adminValidate(Users user) {
		path = this.getClass().getClassLoader().getResource("admin.xml")
				.getPath();
		// 调用验证管理员的方法
		boolean flag = this.validateAdmin(user);
		return flag;
	}

	/**
	 * 验证user对象中的内容与保存的内容是否一致
	 * 
	 * @param user
	 * @return
	 */
	private boolean validateAdmin(Users user) {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(path);
			Element root = document.getRootElement();
			List<Element> element = root.elements("user");
			for (Element e : element) {
				String username = e.element("username").getText();
				String password = e.element("password").getText();
				if (user.getUsername().equals(username)
						&& user.getPassword().equals(password))
					return true;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 验证用户的用户名和密码是否一致
	 */
	@Override
	public String userValidate(Users user) {
		String sql = "select username from users where username=? and password=?";
		Connection conn = OurDaoUtils.getConnection();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			if(rs.next())
				return user.getUsername();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "false";
	}

}