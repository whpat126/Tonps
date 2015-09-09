package com.pt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pt.base.BaseDaoImpl;
import com.pt.dao.PublicIconDao;
import com.pt.domain.PublicIcon;
import com.pt.utils.OurDaoUtils;

@Repository("publicIconDao")
public class PublicIconDaoImpl extends BaseDaoImpl<PublicIcon> implements PublicIconDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static String path = null;

	@Override
	public boolean update(String userId, String sortString) {
		
		return false;
	}

	

	

}
