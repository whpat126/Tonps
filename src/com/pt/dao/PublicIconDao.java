package com.pt.dao;

import java.util.List;

import com.pt.base.BaseDao;
import com.pt.domain.PublicIcon;

public interface PublicIconDao extends BaseDao<PublicIcon> {

	boolean update(String userId, String sortString);

//	List<PublicIcon> findAll(String userId);

}
