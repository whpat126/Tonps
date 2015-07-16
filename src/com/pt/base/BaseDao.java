package com.pt.base;


import java.util.List;
import java.util.Map;

/**
 * 
  * BaseDao  
  * <br/>说明:<br/> 接口,可由各个实体dao接口进行继承
  * @param <Entity> 
  * @return 
  * @author whp 
  * @date 2015年7月16日
 */
public interface BaseDao<Entity> {

	/** 
	  * save 方法 
	  * <br/>方法说明:<br/> 保存对象到对象对应的物理表
	  * @param obj 对象
	  * @return 
	  * @return boolean 是否保存成功
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	boolean save(Entity obj)  ; 
	
	/** 
	  * findAll 方法 
	  * <p>方法说明:</p> 查询所有对象(无约束条件)
	  * @return
	  * @throws Exception 
	  * @return List<Entity> 对象列表
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
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
	List<Entity> findAll(String username, int currentPage, int pageSize);
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
	List<Entity> findAll(String username, int currentPage, int pageSize, Map<String, Object> m);
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
	boolean update(Entity obj, String pk) throws Exception;
	/** 
	  * delete 方法 
	  * <p>方法说明:</p> 根据pk名称和对应的值id 删除对象
	  * @param id 值
	  * @param pk 名称
	  * @return
	  * @throws Exception 
	  * @return boolean 
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	boolean delete(Long id, String pk) ;
	/** 
	  * delete 方法 
	  * <p>方法说明:</p> 根据id值 删除实体
	  * @param id 实体类的主键
	  * @return 
	  * @return boolean 是否成功
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	boolean delete(Long id) ;
	
	/** 
	  * findById 方法 
	  * <p>方法说明:</p> 根据主键查找实体
	  * @param id 实体类的主键
	  * @return 
	  * @return Entity 实体类
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	Entity findById(Long id );

	/** 
	  * getTotal 方法 
	  * <br/>方法说明:<br/> 根据sqlwhere语句查询结果的个数
	  * @param sqlwhere
	  * @return 
	  * @return int 对象个数
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	int getTotal(String sqlWhere);
	/** 
	  * save 方法 
	  * <p>方法说明:</p> 保存对象到指定表
	  * @param obj对象
	  * @param tablename 物理表名
	  * @return 
	  * @return boolean 是否保存成功
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	boolean save(Entity obj, String tablename);
	/** 
	  * findByProp 方法 
	  * <p>方法说明:</p> 根据属性值 查找实体
	  * @param prop 实体类的属性名
	  * @param value 实体类的属性值
	  * @return 
	  * @return List<Entity> 实体类list
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	List<Entity> findByProp(String prop, String value);

	/** 
	  * delete 方法 
	  * <p>方法说明:</p> 批量删除
	  * @param ids 主键值数组
	  * @return 
	  * @return boolean 是否操作成功
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	boolean delete(String[] ids);
	
}