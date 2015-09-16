package com.pt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pt.base.BaseServiceImpl;
import com.pt.dao.MyCompanyDao;
import com.pt.dao.impl.MyCompanyDaoImplPage;
import com.pt.domain.MyCompany;
import com.pt.domain.Users;
import com.pt.service.MyCompanyService;

@Service("myCompanyService")
public class MyCompanyServiceImpl extends BaseServiceImpl<MyCompany> implements MyCompanyService{

	@Autowired
	@Qualifier("myCompanyDao")
	private MyCompanyDao mcd;
	
	@Override
	public int findByUserId(String userId) {
		
		return mcd.findbyUserId(userId);
	}

	@Override
	public MyCompany findByName(String companyName) {
		return mcd.findeByName(companyName);
	}

	@Override
	public int joinCompany(String userId, String pk_company) {
		boolean flag = mcd.checkJoinCompany(userId,pk_company);
		if(flag){
			return 1;
		}else{
			boolean flag2 = mcd.joinCompany(userId,pk_company);
			if(flag2){
				return 2;
			}else{
				return 3;
			}
		}
	}
	@Override
	public boolean save(MyCompany company) {
		return mcd.saveCompany(company);
	}

	@Override
	public boolean applyAdmin(String userId, String pk_myCompany) {
		// TODO Auto-generated method stub
		return mcd.applyAdmin(userId,pk_myCompany);
	}

	@Override
	public boolean outCompany(String userId, String pk_myCompany) {
		// TODO Auto-generated method stub
		return mcd.outCompany(userId,pk_myCompany);
	}

	@Override
	public List<Users> queryUserByCompanyId(String pk_myCompany) {
		return mcd.queryUserByCompanyId(pk_myCompany);
	}

	@Override
	public boolean update(String pk_company, String pk_users) {
		return mcd.update(pk_company,pk_users);
	}

	@Override
	public boolean remove(String pk_company, String pk_users) {
		return mcd.remove(pk_company, pk_users);
	}

	@Override
	public List<MyCompany> findAll(String userId) {
		// TODO Auto-generated method stub
		return mcd.findAll(userId);
	}

	@Override
	public List<MyCompany> findUserCompanyByUserId(String userId) {
		// TODO Auto-generated method stub
		return mcd.findUserCompanyByUserId(userId);
	}

	@Override
	public void usersApply(String joinCompany, String applyAdmin) {
		mcd.usersApply(joinCompany, applyAdmin);
	}

//	@Override
//	public List<MyCompany> findAll(String username, int currentPage, int pageSize){
//		return mcd.findAll(username, currentPage, pageSize);
//	}
	
}
