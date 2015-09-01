package com.pt.dao;

import com.pt.base.BaseDao;
import com.pt.domain.UserIcon;

public interface UserIconDao extends BaseDao<UserIcon> {

	boolean update(String userId, String sortString);

}
