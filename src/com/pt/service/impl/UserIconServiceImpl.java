package com.pt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pt.base.BaseServiceImpl;
import com.pt.dao.UserIconDao;
import com.pt.domain.PublicIcon;
import com.pt.domain.UserIcon;
import com.pt.service.UserIconService;

@Service("userIconService")
public class UserIconServiceImpl extends BaseServiceImpl<UserIcon> implements UserIconService {
	@Autowired
	@Qualifier("userIconDao")
	private UserIconDao userIconDao;

	@Override
	public boolean update(String userId, String sortString) {
		
		return userIconDao.update(userId, sortString);
	}

	@Override
	public List<UserIcon> findAll(String userId) {
		return userIconDao.findAll(userId);
	}

}
