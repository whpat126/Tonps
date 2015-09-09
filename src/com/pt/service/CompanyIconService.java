package com.pt.service;

import com.pt.base.BaseService;
import com.pt.domain.CompanyIcon;

public interface CompanyIconService  extends BaseService<CompanyIcon>{

	boolean update(String userId, String sortString);

}
