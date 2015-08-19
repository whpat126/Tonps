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
import com.pt.dao.IconDao;
import com.pt.dao.UsersDao;
import com.pt.domain.Icon;
import com.pt.domain.Users;
import com.pt.utils.OurDaoUtils;

@Repository("iconDao")
public class IconDaoImpl extends BaseDaoImpl<Icon> implements IconDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static String path = null;

	

}
