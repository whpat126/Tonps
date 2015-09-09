package com.pt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pt.base.BaseServiceImpl;
import com.pt.dao.MyCompanyDao;
import com.pt.domain.MyCompany;
import com.pt.service.MyCompanyService;

@Service("myCompanyService")
public class MyCompanyServiceImpl extends BaseServiceImpl<MyCompany> implements MyCompanyService{

	@Autowired
	@Qualifier("myCompanyDao")
	private MyCompanyDao mcd;
	
	@Override
	public boolean findByUserId(String userId) {
		
		return mcd.findbyUserId(userId);
	}

	@Override
	public MyCompany findByName(String companyName) {
		return mcd.findeByName(companyName);
	}

	@Override
	public boolean joinCompany(String userId, String pk_company) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean applyAdmin(String userId, String pk_myCompany) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean outCompany(String userId, String pk_myCompany) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
