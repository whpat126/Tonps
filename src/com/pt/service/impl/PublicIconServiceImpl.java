package com.pt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pt.base.BaseServiceImpl;
import com.pt.dao.PublicIconDao;
import com.pt.domain.PublicIcon;
import com.pt.service.PublicIconService;

@Service("publicIconService")
public class PublicIconServiceImpl extends BaseServiceImpl<PublicIcon> implements PublicIconService {
	@Autowired
	@Qualifier("publicIconDao")
	private PublicIconDao publicIconDao;




}
