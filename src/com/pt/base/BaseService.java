package com.pt.base;

import java.util.List;
import java.util.Map;

/** 
  * BaseService  
  * <p>说明:</p> Service接口,可由其他实体Service接口进行继承
  * @param <Entity> 
  * @author whp 
  * @date 2015年7月16日 
*/ 
public interface BaseService<Entity> {
	/** 
	  * save 方法 
	  * <br/>方法说明:<br/> 保存对象到对象对应的物理表
	  * @param obj 对象
	  * @return 
	  * @return boolean 是否保存成功
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	boolean save(Entity obj) ; 

	
	List<Entity> findAll();
	
	/** 
	  * findAll 方法 
	  * 方法说明:<br/> 分页显示对象list
	  * @param username
	  * @param currentPage 页面传递过来的当前页
	  * @param pageSize 页面传递过来的每页显示条数
	  * @return 
	  * @return List<Entity> 对象list
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	List<Entity> findAll(String username, int currentPage, int pageSize) throws Exception;
	/** 
	  * findAll 方法 
	  * <p>方法说明:</p> 分页显示对象list
	  * @param username
	  * @param currentPage 页面传递过来的当前页
	  * @param pageSize 页面传递过来的每页显示条数
	  * @param m 页面传递过来的的查询条件 map 其中的kv值的含义可以自定义
	  * @return 
	  * @return List<Entity> 对象list
	  * @author whp 
	  * @date 2015年7月16日 
	*/  
	List<Entity> findAll(String username, int currentPage, int pageSize, Map<String, Object> m) throws Exception;
	
	/** 
	  * update 方法 
	  * <p>方法说明:</p> 更新对象
	  * @param obj 对象
	  * @param pk 主键
	  * @throws Exception 
	  * @return boolean 是否更新成功
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	boolean update(Entity obj, String pk) ;

	/** 
	  * delete 方法 
	  * <p>方法说明:</p> 根据pk名称和对应的值id 删除对象
	  * @param pkValue 值
	  * @param pkName 名称
	  * @return
	  * @throws Exception 
	  * @return boolean 
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	boolean delete(String pkValue, String pkName);
	/** 
	  * delete 方法 
	  * <p>方法说明:</p> 根据主键id删除对象
	  * @param pkValue
	  * @return
	  * @throws Exception 
	  * @return boolean 
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	boolean delete(String pkValue) ;

	/** 
	  * delete 方法 
	  * <p>方法说明:</p> 批量删除对象
	  * @param ids 主键数组
	  * @return 
	  * @return boolean 
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	boolean delete(String[] ids);

	/** 
	  * findByProp 方法 
	  * <p>方法说明:</p> 根据属性名称 查找对象
	  * @param prop 属性名
	  * @param value 属性值
	  * @return
	  * @throws Exception 
	  * @return Entity 
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	Entity findByProp(String prop, String value) ;

	/** 
	  * findById 方法 
	  * <p>方法说明:</p> 根据主键的值 查找对象
	  * @param id
	  * @return
	  * @throws Exception 
	  * @return Entity 
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	Entity findById(String id );

	/** 
	  * getTotal 方法 
	  * <br/>方法说明:<br/> 根据sqlwhere语句查询结果的个数
	  * @param sqlwhere
	  * @return 
	  * @return int 对象个数
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	public int getTotal(String sqlwhere);



	

}
