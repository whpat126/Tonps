package com.pt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pt.base.BaseDaoImpl;
import com.pt.dao.CompanyIconDao;
import com.pt.dao.MyCompanyDao;
import com.pt.dao.UserIconDao;
import com.pt.domain.CompanyIcon;
import com.pt.domain.MyCompany;
import com.pt.domain.UserIcon;
import com.pt.utils.OurDaoUtils;

@Repository("myCompanyDao")
public class MyCompanyDaoImpl extends BaseDaoImpl<MyCompany> implements MyCompanyDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static String path = null;

	public int getMsgCount() {
		String sql = "select count(*) from users "; // 
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			int num =0;
			while(rs.next()){
				num = rs.getInt(1);
			}
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(rs);
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return 0;
	}

	@Override
	public boolean findbyUserId(String userId) {
		String sql = "";
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			int num =0;
			while(rs.next()){
//				num = 
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
	public MyCompany findeByName(String companyName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
