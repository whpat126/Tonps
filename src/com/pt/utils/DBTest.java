package com.pt.utils;

import java.sql.Connection;
import java.sql.DriverManager;

import com.pt.domain.Database;

public class DBTest {

	private DBTest(){
	}
	
	/** 当用户点击测试数据库连接信息时
	 * @param db 该对象封装了数据库的连接信息
	 * @return 连接成功返回true,连接失败返回false */
	public static boolean test(Database db) {
		Connection conn = null;
		try {
			String password = db.getDbpassword();
			db.setDbpassword(CodeMethod.decrypt(password, "平通"));
			conn = DriverManager.getConnection(db.getDbaddress(),db.getDbuser(),db.getDbpassword());
			if(conn != null)
				return true;
		} catch (Exception e) {
			System.err.println("数据库连接测试不成功！");
		}
		return false;
	}
	public static void main(String[] args) {
		
	}
}
