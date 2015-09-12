package com.pt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pt.base.BaseServiceImpl;
import com.pt.dao.CompanyIconDao;
import com.pt.domain.CompanyIcon;
import com.pt.domain.UserIcon;
import com.pt.service.CompanyIconService;
import com.pt.service.UserIconService;

@Service("companyIconService")
public class CompanyIconServiceImpl extends BaseServiceImpl<CompanyIcon> implements CompanyIconService {
	@Autowired
	@Qualifier("companyIconDao")
	private CompanyIconDao companyIconDao;

	@Override
	public boolean update(String userId, String sortString) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CompanyIcon> findAll(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	


}
