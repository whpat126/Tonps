package com.pt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.pt.base.BaseDaoImpl;
import com.pt.dao.PwdqDao;
import com.pt.domain.Pwdq;

@Repository("pwdqDao")
public class PwdqaDaoImpl extends BaseDaoImpl<Pwdq> implements PwdqDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static String path = null;

	

}
