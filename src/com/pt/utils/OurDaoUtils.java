package com.pt.utils;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.pt.domain.Database;

public class OurDaoUtils {

	private static String xmlPath = null;
	private static Database db;

	public OurDaoUtils() {

	}
	/** 加载驱动 */
	static {
		try {
			// 获取到classes路径下的文件user.xml
			URL url = ClassLoader.getSystemResource("admin.xml");
			if (url == null)
				url = DBTest.class.getClassLoader().getResource("admin.xml");
			xmlPath = url.getPath();
			if(null != dbforName())
				Class.forName(dbforName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 建立数据库连接 */
	public static Connection getConnection() {
		try {
			db = read();
			String pwd = db.getDbpassword();
//			System.out.println("pwd:" + pwd);
			db.setDbpassword(CodeMethod.decrypt(pwd, "pingtongxinxi"));
//			System.out.println("db后--->" + db.getDbpassword());
			return DriverManager.getConnection(db.getDbaddress(), db.getDbuser(), db.getDbpassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/** jdbc 驱动类 */
	static String dbforName() throws Exception {
		db = OurDaoUtils.read();
		if (db.getDbtype().equals("mysql")) {
			return "com.mysql.jdbc.Driver";
		}
		if (db.getDbtype().equals("sqlserver")) {
			return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		}
		if (db.getDbtype().equals("oracle")) {
			return "oracle.jdbc.driver.OracleDriver";
		}
		return null;
	}

	/** 读取数据库配置信息 */
	private static Database read() {
		SAXReader reader = new SAXReader();
		Document document;
		try {
			document = reader.read(new File(xmlPath));
			Element root = document.getRootElement();
			Element database = root.element("database");
			String dbtype = database.element("dbtype").getText();
			String dbaddress = database.element("dbaddress").getText();
			String dbuser = database.element("dbuser").getText();
			String dbpassword = database.element("dbpassword").getText();

			db = new Database();
			db.setDbtype(dbtype);
			db.setDbaddress(dbaddress);
			db.setDbuser(dbuser);
			db.setDbpassword(dbpassword);
			return db;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}
}
