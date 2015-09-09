package com.pt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pt.base.BaseDaoImpl;
import com.pt.dao.UserIconDao;
import com.pt.domain.PublicIcon;
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
		// 删除图标先查询出当前排序
		String sql4 = "select iconsort from usericon where pk_users=?";
		// 删除的sql
		String sql3 = "delete form usericon where pk_users=? and pk_usericon=?";
		String[] arrstr = sortString.split(","); // 调整后的顺序
		conn = OurDaoUtils.getConnection();
		try {
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			String str = "";
			while(rs.next()){
				str += rs.getInt(1) + ",";
			}
			rs.close();
			pstmt.close();
			String[] arr = str.split(","); // 保存的是调整前的顺序
			conn.setAutoCommit(false);
			if(arrstr.length == arr.length){ // 为调整顺序
				//执行修改,还需要确认修改的顺序问题
				for(int i=0; i<arrstr.length; i++){
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, arrstr[i]);
					pstmt.setString(2, arr[i]);
					int line = pstmt.executeUpdate(); /** 必须根据主键来取得  现在这个方式有问题 */
					pstmt.close();
					if(line == 1){
						continue;
					}
					return false;
				}
				conn.commit();
				return true;
			}else{ // 为删除操作
				// 删除前先得到原来的顺序
				pstmt = conn.prepareStatement(sql4);
				rs = pstmt.executeQuery();
				List<UserIcon> list = new ArrayList<UserIcon>();
				while(rs.next()){
					UserIcon ui = new UserIcon();
					ui.setIconsort(rs.getString(1));
					list.add(ui);
				}
				rs.close();
				pstmt.close();
				// 得到用户删除的那个数据
				String deleteIcon = "";
				for(UserIcon uicon : list){
					for(int i =0;i<arrstr.length;i++){
						if(!uicon.getIconsort().equals(arrstr[i])){
							deleteIcon = uicon.getIconsort();
							break;
						}
					}
				}
				
				//执行删除操作
				pstmt = conn.prepareStatement(sql3);
				pstmt.setString(1, userId);
				pstmt.setString(2, deleteIcon);
				int line = pstmt.executeUpdate();
				pstmt.close();
				if(line == 1){
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
