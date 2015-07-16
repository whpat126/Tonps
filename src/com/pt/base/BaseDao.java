package com.pt.base;


import java.util.List;
import java.util.Map;

public interface BaseDao<Entity> {

	
	boolean save(Entity obj)  ; 
	
	List<Entity> findAll() throws Exception;
	
	List<Entity> findAll(String username, int currentPage, int pageSize);
	List<Entity> findAll(String username, int currentPage, int pageSize, Map<String, Object> m);
	void update(Entity obj, String pk) throws Exception;
	boolean delete(Long id, String pk) ;
	boolean delete(Long id) ;

	Entity findById(Long id );

	int getTotal(String sql);

	boolean save(Entity obj, String tablename);

	/**
	 * 根据属性值 查找实体
	 * @param prop 实体类的属性名
	 * @param value 实体类的属性值
	 * @return 实体类list
	 * @author lenovo
	 */
	List<Entity> findByProp(String prop, String value);
	
}