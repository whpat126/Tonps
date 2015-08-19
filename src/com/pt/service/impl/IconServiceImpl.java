package com.pt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pt.base.BaseServiceImpl;
import com.pt.dao.IconDao;
import com.pt.domain.Icon;
import com.pt.service.IconService;

@Service("iconService")
public class IconServiceImpl extends BaseServiceImpl<Icon> implements IconService {
	@Autowired
	@Qualifier("iconDao")
	private IconDao iconDao;

}
