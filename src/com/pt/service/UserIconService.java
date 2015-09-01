package com.pt.service;

import java.util.List;

import com.pt.base.BaseService;
import com.pt.domain.PublicIcon;
import com.pt.domain.UserIcon;

public interface UserIconService  extends BaseService<UserIcon>{

	boolean update(String userId, String sortString);

	List<PublicIcon> findAll(String userId);

}
