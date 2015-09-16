package com.pt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pt.domain.PageBean;
import com.pt.domain.Users_MyCompany;
import com.pt.utils.OurDaoUtils;

public class MyCompanyDaoImplPage {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	
	public PageBean findAll(String username,int currentPage, int pageSize){
		String sql2 ="select distinct a1.username,a2.apply,a3.name,a1.pk_users,a3.pk_mycompany,a2.pk_users_mycompany from users a1"
				+ " inner join users_mycompany a2 on a1.pk_users=a2.pk_users"
				+ " inner join mycompany a3 on a2.pk_mycompany=a3.pk_mycompany"
				+ " where a1.username=? and a2.apply is not null";
		conn = OurDaoUtils.getConnection();
		try {
			List<Object> listObj = new ArrayList<Object>();
			pstmt = conn.prepareStatement(sql2,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			PageBean pageBean = new PageBean(currentPage, pageSize, rs);
			int count = 0;
			while (rs.next()) {
				count++;
				if (count > pageSize)
					break;
				Users_MyCompany company = new Users_MyCompany();
				company.setUsername(rs.getString("username"));
				company.setApply(rs.getString("apply"));
				company.setName(rs.getString("name"));
				company.setPk_users(rs.getString("pk_users"));
				company.setPk_mycompany(rs.getString("pk_mycompany"));
				company.setPk_users_mycompany(rs.getString("pk_users_mycompany"));
				listObj.add(company);
			}
			rs.close();
			pstmt.close();
			conn.close();
			pageBean.setDataList(listObj);
			return pageBean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
