package com.pt.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

/**
 * 这是一个测试
 * @author Administrator
 *
 * @param <Entity>
 */

public class BaseServiceImpl<Entity> implements BaseService<Entity> {

	//此类别可能需要调整
	protected BaseDao<Entity> baseDaoImpl;

	
	
	private Class<?> clazz;//存储了具体操作的类,
	@SuppressWarnings("unchecked")
	public BaseServiceImpl(){
		// System.out.println(this.getClass());
		// System.out.println(this.getClass().getGenericSuperclass());
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 带有真实类型参数的对象
		clazz = (Class<Entity>) pt.getActualTypeArguments()[0];
	
	}
	@PostConstruct
	public void init(){
		String clazzName=clazz.getSimpleName();
		String clazzDao=Character.toLowerCase(clazzName.charAt(0))+clazzName.substring(1)+"DaoImpl";
		try {
			Field clazzField =this.getClass().getSuperclass().getDeclaredField(clazzDao);
			Field baseField =this.getClass().getSuperclass().getDeclaredField("baseDaoImpl");
			baseField.set(this, clazzField.get(this));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public BaseDao<Entity> getBaseDaoImpl() {
		return baseDaoImpl;
	}

	public void setBaseDaoImpl(BaseDao<Entity> baseDaoImpl) {
		this.baseDaoImpl = baseDaoImpl;
	}

	@Override
	public boolean save(Entity obj) {
		return baseDaoImpl.save(obj);
	}

	@Override
	public List<Entity> findAll() throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.findAll();
	}

	@Override
	public List<Entity> findAll(String username, int currentPage, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.findAll();
	}

	@Override
	public List<Entity> findAll(String username, int currentPage, int pageSize, Map<String, Object> m) throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.findAll();
	}

	@Override
	public void update(Entity obj, String pk) throws Exception {
		 baseDaoImpl.update(obj, pk);;
	}

	@Override
	public boolean delete(Long id, String pk) throws Exception {
		return baseDaoImpl.delete(id, pk);
	}

	@Override
	public boolean delete(Long id) throws Exception {
		return baseDaoImpl.delete(id);
	}

	

	@Override
	public Entity findById(Long id, String pk) throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.findByProp(id.toString(), pk).get(0);
	}

	@Override
	public Entity findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return baseDaoImpl.findById(id);
	}

}
