package com.pt.dao;

import com.pt.base.BaseDao;
import com.pt.domain.CompanyIcon;

public interface CompanyIconDao extends BaseDao<CompanyIcon> {

	boolean update(String userId, String sortString);

}
