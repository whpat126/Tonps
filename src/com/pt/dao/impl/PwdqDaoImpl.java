package com.pt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.pt.base.BaseDaoImpl;
import com.pt.dao.PwdqaDao;
import com.pt.domain.Pwdqa;

@Repository("pwdqaDao")
public class PwdqDaoImpl extends BaseDaoImpl<Pwdqa> implements PwdqaDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static String path = null;

	

}
