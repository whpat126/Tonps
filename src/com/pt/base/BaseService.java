package com.pt.base;

import java.util.List;
import java.util.Map;

public interface BaseService<Entity> {
	boolean save(Entity obj) ; 
	
	List<Entity> findAll() throws Exception;
	List<Entity> findAll(String username, int currentPage, int pageSize) throws Exception;
	List<Entity> findAll(String username, int currentPage, int pageSize, Map<String, Object> m) throws Exception;
	
	void update(Entity obj, String pk) throws Exception;

	boolean delete(Long id, String pk) throws Exception;
	boolean delete(Long id) throws Exception;

//	boolean delete(String[] ids);

	Entity findById(Long id, String pk) throws Exception;

	Entity findById(Long id ) throws Exception;


	

}
