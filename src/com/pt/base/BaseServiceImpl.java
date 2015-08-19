package com.pt.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.pt.dao.IconDao;
import com.pt.dao.PwdqDao;
import com.pt.dao.PwdqaDao;
import com.pt.dao.UsersDao;

/** 
  * BaseServiceImpl  
  * <br/>说明:<br/> 基础service实现类，实现了BaseService接口,可由其他ServiceImpl实现类继承
  * @param <Entity> 
  * @author whp 
  * @date 2015年7月16日 
*/ 
@Lazy(true)
@Service("baseService")
public class BaseServiceImpl<Entity> implements BaseService<Entity> {
	@Autowired
	@Qualifier("userDao")
	public UsersDao usersDao;
	@Autowired
	@Qualifier("iconDao")
	private IconDao iconDao;
	@Autowired
	@Qualifier("pwdqDao")
	private PwdqDao pwdqDao;
	@Autowired
	@Qualifier("pwdqaDao")
	private PwdqaDao pwdqaDao;
	
	
	/**
	 * BaseDao<Entity>baseDao接口
	 */
	protected BaseDao<Entity> baseDao;
	/**
	 * Class<?>clazz
	 * 存储了具体操作的类
	 */
	private Class<?> clazz;
	
	@PostConstruct
	public void init(){
		String clazzName=clazz.getSimpleName();
		String clazzDao=Character.toLowerCase(clazzName.charAt(0))+clazzName.substring(1)+"Dao";
		try {
			Field clazzField =this.getClass().getSuperclass().getDeclaredField(clazzDao);
			Field baseField =this.getClass().getSuperclass().getDeclaredField("baseDao");
			baseField.set(this, clazzField.get(this));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public BaseServiceImpl(){
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 带有真实类型参数的对象
		clazz = (Class<Entity>) pt.getActualTypeArguments()[0];
	
	}
	
	
	public BaseDao<Entity> getBaseDaoImpl() {
		return baseDao;
	}

	public void setBaseDaoImpl(BaseDao<Entity> baseDao) {
		this.baseDao = baseDao;
	}

	/** 
	  * save 方法 
	  * <br/>方法说明:<br/> 保存对象到对象对应的物理表
	  * @param obj 对象
	  * @return boolean 是否保存成功
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	@Override
	public boolean save(Entity obj) {
		return baseDao.save(obj);
	}

	/** 
	  * findAll 方法 
	  * <p>方法说明:</p> 查询所有对象(无约束条件)
	  * @throws Exception 
	  * @return List<Entity> 对象列表
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	@Override
	public List<Entity> findAll() {
//		System.out.println("base findAll");
		// TODO Auto-generated method stub
		return baseDao.findAll();
	}
	/** 
	  * findAll 方法 
	  * 方法说明:<br/> 分页显示对象list
	  * @param username
	  * @param currentPage 页面传递过来的当前页
	  * @param pageSize 页面传递过来的每页显示条数
	  * @return List<Entity> 对象list
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	@Override
	public List<Entity> findAll(String username, int currentPage, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findAll();
	}
	/** 
	  * findAll 方法 
	  * <p>方法说明:</p> 分页显示对象list
	  * @param username
	  * @param currentPage 页面传递过来的当前页
	  * @param pageSize 页面传递过来的每页显示条数
	  * @param m 页面传递过来的的查询条件 map 其中的kv值的含义可以自定义
	  * @return List<Entity> 对象list
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	@Override
	public List<Entity> findAll(String username, int currentPage, int pageSize, Map<String, Object> m) throws Exception {
		// TODO Auto-generated method stub
		return baseDao.findAll();
	}
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
	@Override
	public boolean update(Entity obj, String pk) {
		 return baseDao.update(obj, pk);
	}
	/** 
	  * delete 方法 
	  * <p>方法说明:</p> 根据pk名称和对应的值id 删除对象
	  * @param pkValue 值
	  * @param pkName 名称
	  * @throws Exception 
	  * @return boolean 
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	@Override
	public boolean delete(String pkValue, String pkName) {
		return baseDao.delete(pkValue, pkName);
	}
	/** 
	  * delete 方法 
	  * <p>方法说明:</p> 根据主键id删除对象
	  * @param pkValue
	  * @throws Exception 
	  * @return boolean 
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	@Override
	public boolean delete(String pkValue) {
		return baseDao.delete(pkValue);
	}

	
	/** 
	  * findById 方法 
	  * <p>方法说明:</p> 根据属性名称 查找对象
	  * @param id 值
	  * @param pk 名称
	  * @return
	  * @throws Exception 
	  * @return Entity 
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	@Override
	public Entity findByProp(String prop, String value){
		List<Entity> list = baseDao.findByProp(prop, value);
		if(null == list || list.size() <1){
			return null;
		}else{
			return list.get(0);
		}
	}
	/** 
	  * findById 方法 
	  * <p>方法说明:</p> 根据主键查找实体
	  * @param id 实体类的主键
	  * @return 
	  * @return Entity 实体类
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	@Override
	public Entity findById(String id) {
		// TODO Auto-generated method stub
		return baseDao.findById(id);
	}
	/** 
	  * delete 方法 
	  * <p>方法说明:</p> 批量删除对象
	  * @param ids 主键数组
	  * @return 
	  * @return boolean 
	  * @author whp 
	  * @date 2015年7月16日 
	*/ 
	@Override
	public boolean delete(String[] ids) {
		// TODO Auto-generated method stub
		return baseDao.delete(ids);
	}
	
	@Override
	public int getTotal(String sqlwhere) {
		return baseDao.getTotal(sqlwhere);
	}

}
