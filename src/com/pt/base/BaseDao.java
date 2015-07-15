package com.pt.base;


import java.util.List;
import java.util.Map;

public interface BaseDao<Entity> {

	
	boolean save(Entity obj)  ; 
	
	List<Entity> findAll() throws Exception;
	
	List<Entity> findAll(String username, int currentPage, int pageSize);
	List<Entity> findAll(String username, int currentPage, int pageSize, Map<String, Object> m);
	void update(Entity obj, String pk) throws Exception;

	boolean delete(Long id, String pk) throws Exception;
	boolean delete(Long id) throws Exception;

	Entity findById(String id, String pk) throws Exception;

	Entity findById(Long id ) throws Exception;

	int getTotal(String sql);

	boolean save(Entity obj, String tablename);
	
}