package com.pt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
	
	@Override
	public boolean update(String userId, String sortString) {
		// 查询现在数据库中的个数，用于判断是删除还是修改
		String sql1 = "select pk_usericon from usericon where pk_users=?";
		// 修改的sql语句
		String sql2 = "update usericon set iconsort=? where pk_usericon=?";
		// 根据用户的Id和被删除的图标的序号删除该图标
		String sql3 = "delete from usericon where pk_usericon=?";
		String[] arrstr = sortString.split(","); // 前台传过来pk顺序
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			String str = "";
			while(rs.next()){
				str += rs.getString(1)+",";
			}
			rs.close();
			pstmt.close();
			String[] arrDB = str.split(","); // 数据库中主键个数，是无序的
			conn.setAutoCommit(false);
			if(arrstr.length == arrDB.length){ // 判断是调整顺序的情况
				for(int i=0; i<arrstr.length; i++){
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, i+1 +"");
					pstmt.setString(2, arrstr[i]);
					int line = pstmt.executeUpdate(); // 必须根据主键来取得  
					pstmt.close();
					if(line == 1){
						continue;
					}
					conn.rollback();
					return false;
				}
				conn.commit();
				return true;
			}else{ // 为删除操作
				String deleteIcon = ""; // 定义用户删除的那个数据
				List<String> arrList = Arrays.asList(arrstr); // 前台传过来的主键
				List<String> arrDBList = Arrays.asList(arrDB); // 数据库中主键
				// 比较前台和后台不一致的数据
				for(String uicon : arrDBList){
					if(!arrList.contains(uicon)){
						deleteIcon = uicon;
					}
				}
				System.out.println("delteIcon:"+deleteIcon);
				//执行删除操作
				pstmt = conn.prepareStatement(sql3);
				pstmt.setString(1, deleteIcon);
				int line = pstmt.executeUpdate();
				pstmt.close();
				if(line == 1){
					for(int i=0;i<arrDBList.size()-1;i++){
						pstmt = conn.prepareStatement(sql2);
						pstmt.setString(1, i+1 +"");
						pstmt.setString(2, arrstr[i]);
						int num = pstmt.executeUpdate(); // 必须根据主键来取得  
						pstmt.close();
						if(num == 1){
							continue;
						}
						conn.rollback();
						return false;
					}
					conn.commit();
					return true;
				}				
			}
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			OurDaoUtils.close(conn);
		}
		return false;
	}

	@Override
	public List<UserIcon> findAll(String userId) {
		String sql = "select a1.pk_usericon,a1.iconsort, a1.type, a1.param_title, a1.param_href, a2.name, a2.url from usericon a1 INNER JOIN iconlib a2 on a1.pk_iconlib= a2.pk_iconlib WHERE a1.pk_users=? order by a1.iconsort";
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			List<UserIcon> list = new ArrayList<UserIcon>();
			while(rs.next()){
				UserIcon ui = new UserIcon();
				ui.setPk_userIcon(rs.getString("pk_usericon"));
				ui.setIconsort(rs.getString("iconsort"));
				ui.setParam_href(rs.getString("param_href"));
				ui.setType(rs.getString("type"));
				list.add(ui);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			OurDaoUtils.close(rs);
			OurDaoUtils.close(pstmt);
			OurDaoUtils.close(conn);
		}
		return null;
	}
	
	
	
	public static void main(String[] args) {
		String sub = "";
		String[] dateSub = sub.split("-");
		if(dateSub[1].length()==1){
			dateSub[1]= "0"+dateSub[1];
		}
		
	}
}
