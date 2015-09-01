package com.pt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pt.base.BaseDaoImpl;
import com.pt.dao.UserIconDao;
import com.pt.domain.UserIcon;
import com.pt.utils.OurDaoUtils;

@Repository("userIconDao")
public class UserIconDaoImpl extends BaseDaoImpl<UserIcon> implements UserIconDao {

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
	public boolean update(String userId, String sortString) {
		String sql = "update "; // 
		// 查询现在数据库中的个数，用于判断是删除还是修改
		String sql1 = "select count(*) from icon a1 inner join users_icon a2 "
				+ " on a1.pk_icon= a2.pk_icon inner JOIN users a3 "
				+ " ON a2.pk_users= a3.pk_users where a3.pk_users=1 ";
		String[] arrstr = sortString.split(",");
		
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql1);
			rs = pstmt.executeQuery();
			int num =0;
			while(rs.next()){
				num = rs.getInt(1);
			}
			if(arrstr.length == num){ // 是修改
				//执行修改
				
			}else{
				// 执行删除
				
			}
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
