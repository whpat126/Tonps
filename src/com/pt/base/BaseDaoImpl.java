package com.pt.base;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.pt.utils.OurDaoUtils;

/**
 * BaseDaoImpl <br/>
 * 说明:<br/>
 * BaseDao的实现类,可由其他实体DaoImpl类继承
 * 
 * @param <Entity>
 *            实体类
 * @author whp
 * @date 2015年7月16日
 */
public class BaseDaoImpl<Entity> implements BaseDao<Entity> {

	/**
	 * 根据泛型需要,设置的成员变量clazz,由子类的构造函数进行实例化
	 */
	protected Class<Entity> clazz;

	/**
	 * 构造函数 初始化 clazz 将成员变量 clazz 初始化为子类泛型中的实体class
	 */
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {

		// System.out.println(this.getClass());
		// System.out.println(this.getClass().getGenericSuperclass());
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 带有真实类型参数的对象
		clazz = (Class<Entity>) pt.getActualTypeArguments()[0];
	}

	/**
	 * save 方法 <br/>
	 * 方法说明:<br/>
	 * 保存对象到对象对应的物理表
	 * 
	 * @param obj
	 *            对象
	 * @return
	 * @return boolean 是否保存成功
	 * @author whp
	 * @date 2015年7月16日
	 */
	@Override
	public boolean save(Entity obj) {
		System.out.println("111111111111111111111111111111111111");
		// obj.getSimpleName();
		Connection conn = OurDaoUtils.getConnection();
		String sql = "insert into " + clazz.getSimpleName() + " values(";
		// 可以获取本类所声明的变量
		Field[] fs = clazz.getDeclaredFields();
		// System.out.println(fs.length);

		for (int i = 0; i < fs.length; i++) {
			if (i == 0) {
				sql += "?";
			} else {
				sql += ",? ";
			}
		}
		sql = sql + ")";
		// System.out.println(sql);

		// 进行预编译
		PreparedStatement ps = OurDaoUtils.getPs(conn, sql);

		// ps.setString(1, user.getName());

		try {
			for (int i = 0; i < fs.length; i++) {
				// 拼接方法的名称
				String MethodName = "get" + Character.toUpperCase(fs[i].getName().charAt(0)) + fs[i].getName().substring(1);
				// System.out.println("MethodName:"+MethodName);
				Method m = clazz.getMethod(MethodName);
				// System.out.println(">>>>>>>>>>>"+m.invoke(obj));
				ps.setObject(i, m.invoke(obj));
			}
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OurDaoUtils.close(ps);
			OurDaoUtils.close(conn);
		}
		return false;
	}

	/**
	 * save 方法
	 * <p>
	 * 方法说明:保存对象到指定表
	 * </p>
	 * @param obj对象
	 * @param tablename
	 *            物理表名
	 * @return
	 * @return boolean 是否保存成功
	 * @author whp
	 * @date 2015年7月16日
	 */
	@Override
	public boolean save(Entity obj, String tablename) {
		// obj.getSimpleName();
		Connection conn = OurDaoUtils.getConnection();
		String sql = "insert into " + tablename + " values( ";
		// 可以获取本类所声明的变量
		Field[] fs = clazz.getDeclaredFields();
		// System.out.println(fs.length);

		for (int i = 0; i < fs.length; i++) {
			if (i == 0) {
				sql += "?";
			} else {
				sql += ",? ";
			}
		}
		sql = sql + ")";
		// System.out.println(sql);

		// 进行预编译
		PreparedStatement ps = OurDaoUtils.getPs(conn, sql);

		// ps.setString(1, user.getName());

		try {
			for (int i = 1; i < fs.length; i++) {// 从1开始,避开主键
				// 拼接方法的名称
				String MethodName = "get" + Character.toUpperCase(fs[i].getName().charAt(0)) + fs[i].getName().substring(1);
				// System.out.println("MethodName:"+MethodName);
				Method m = clazz.getMethod(MethodName);
				// System.out.println(">>>>>>>>>>>"+m.invoke(obj));
				ps.setObject(i, m.invoke(obj));
			}
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			OurDaoUtils.close(ps);
			OurDaoUtils.close(conn);
		}
	}

	/**
	 * update 方法
	 * <p>
	 * 方法说明:
	 * </p>
	 * 更新对象
	 * 
	 * @param obj
	 *            对象
	 * @param pk
	 *            主键
	 * @throws Exception
	 * @return boolean 是否更新成功
	 * @author whp
	 * @date 2015年7月16日
	 */
	@Override
	public boolean update(Entity obj, String pk) {
		Connection conn = OurDaoUtils.getConnection();
		String sql = " update " + clazz.getSimpleName() + " set  ";
		Field[] fs = clazz.getDeclaredFields();
		for (int i = 1; i < fs.length; i++) {
			sql += fs[i].getName() + "=?,";
		}
		sql = sql.substring(0, sql.length() - 1) + " where " + pk + " = ? ";
		PreparedStatement ps = OurDaoUtils.getPs(conn, sql);
		try {
			for (int i = 1; i < fs.length; i++) {
				String methodName = "get" + Character.toUpperCase(fs[i].getName().charAt(0)) + fs[i].getName().substring(1);
				Method m = clazz.getMethod(methodName);
				ps.setObject(i, m.invoke(obj));// user.getName();
			}
			Method m2 = clazz.getMethod("get" + Character.toUpperCase(fs[0].getName().charAt(0)) + fs[0].getName().substring(1));
			ps.setLong(fs.length, (Long) m2.invoke(obj));

			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			OurDaoUtils.close(ps);
			OurDaoUtils.close(conn);
		}
	}

	/**
	 * delete 方法
	 * <p>
	 * 方法说明:
	 * </p>
	 * 
	 * @param pkValue
	 * @param pkName
	 * @return
	 * @return boolean
	 * @author whp
	 * @date 2015年7月16日
	 */
	@Override
	public boolean delete(String pkValue, String pkName) {
		Connection conn = OurDaoUtils.getConnection();
		String sql = " delete from " + clazz.getSimpleName() + " where " + pkName + " =" + pkValue;
		QueryRunner qRunner = new QueryRunner();
		try {
			qRunner.update(conn, sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * findAll 方法
	 * <p>
	 * 方法说明:
	 * </p>
	 * 查询所有对象(无约束条件)
	 * 
	 * @return
	 * @throws Exception
	 * @return List<Entity> 对象列表
	 * @author whp
	 * @date 2015年7月16日
	 */
	@Override
	public List<Entity> findAll() {
		Connection conn = OurDaoUtils.getConnection();
		String sql = " select * from " + clazz.getSimpleName();
		try {
			QueryRunner qRunner = new QueryRunner();
			List<Entity> nList = new ArrayList<Entity>();
			// 使用 BeanListHandler 实现将所有的结果集转换为 实体bean
			ResultSetHandler<List<Entity>> h = new BeanListHandler<Entity>(clazz);
			// 执行 SQL 语句,使用 BeanListHandler,返回对象的list集合
			nList = (List<Entity>) qRunner.query(conn, sql, h);

			return nList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * findById 方法
	 * <p>
	 * 方法说明:
	 * </p>
	 * 根据主键查找实体
	 * 
	 * @param id
	 *            实体类的主键
	 * @return
	 * @return Entity 实体类
	 * @author whp
	 * @date 2015年7月16日
	 */
	@Override
	public Entity findById(String id) {
		Field[] fs = clazz.getDeclaredFields();
		String pkName = fs[0].getName();// 反射得出主键名称
		return findByProp(pkName, id.toString()).get(0);
	}

	/**
	 * findByProp 方法
	 * <p>
	 * 方法说明:
	 * </p>
	 * 根据属性值 查找实体
	 * 
	 * @param prop
	 *            实体类的属性名
	 * @param value
	 *            实体类的属性值
	 * @return
	 * @return List<Entity> 实体类list
	 * @author whp
	 * @date 2015年7月16日
	 */
	@Override
	public List<Entity> findByProp(String prop, String value) {
		Connection conn = OurDaoUtils.getConnection();
		String sql = " select * from  " + clazz.getSimpleName() + " where " + prop + " = ?";
		try {
			QueryRunner qRunner = new QueryRunner();
			List<Entity> nList = new ArrayList<Entity>();
			// 使用 BeanListHandler 实现将所有的结果集转换为 实体bean
			ResultSetHandler<List<Entity>> h = new BeanListHandler<Entity>(clazz);
			// 执行 SQL 语句,使用 BeanListHandler,返回对象的list集合
			nList = (List<Entity>) qRunner.query(conn, sql, h, value);
			return nList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * delete 方法
	 * <p>
	 * 方法说明:
	 * </p>
	 * 根据id值 删除实体
	 * 
	 * @param id
	 *            实体类的主键
	 * @return
	 * @return boolean 是否成功
	 * @author whp
	 * @date 2015年7月16日
	 */
	@Override
	public boolean delete(String id) {
		Field[] fs = clazz.getDeclaredFields();
		String pkName = fs[0].getName();// 反射得出主键名称
		Connection conn = OurDaoUtils.getConnection();
		String sql = " delete from  " + clazz.getSimpleName() + " where " + pkName + " = ?";
		try {
			QueryRunner qRunner = new QueryRunner();
			qRunner.update(conn, sql, id);
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * getTotal 方法 <br/>
	 * 方法说明:<br/>
	 * 根据sqlwhere语句查询结果的个数
	 * 
	 * @param sqlwhere
	 * @return
	 * @return int 对象个数
	 * @author whp
	 * @date 2015年7月16日
	 */
	@Override
	public int getTotal(String sqlwhere) {
		Connection conn = OurDaoUtils.getConnection();
		QueryRunner qRunner = new QueryRunner();
		String sql = "select count(*) as total from" + clazz.getSimpleName() + " where ";
		sql += sqlwhere;

		try {
			// ScalarHandler：将结果集中某一条记录的其中某一列的数据存成 Object,此处为 Integer
			/*
			 * ScalarHandler: 单值查询 如： select count(*) from account; Long count =
			 * (Long)runner.query("select count(*) from account",new
			 * ScalarHandler(1) );
			 */
			int count = qRunner.query(conn, sql, new ScalarHandler<Long>(1)).intValue();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * findAll 方法 方法说明:<br/>
	 * 分页显示对象list
	 * 
	 * @param username
	 * @param currentPage
	 *            页面传递过来的当前页
	 * @param pageSize
	 *            页面传递过来的每页显示条数
	 * @return
	 * @return List<Entity> 对象list
	 * @author whp
	 * @date 2015年7月16日
	 */
	@Override
	public List<Entity> findAll(String username, int currentPage, int pageSize) {
		Connection conn = OurDaoUtils.getConnection();
		QueryRunner qRunner = new QueryRunner();
		String sql = " select * from  " + clazz.getSimpleName() + " where 1=1";

		sql = sql + " limit " + (currentPage - 1) * pageSize + " , " + pageSize;

		List<Entity> nList = new ArrayList<Entity>();
		// 使用 BeanListHandler 实现将所有的结果集转换为 实体bean
		ResultSetHandler<List<Entity>> h = new BeanListHandler<Entity>(clazz);
		// 执行 SQL 语句,使用 BeanListHandler,返回对象的list集合
		try {
			nList = (List<Entity>) qRunner.query(conn, sql, h);
			return nList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * findAll 方法
	 * <p>
	 * 方法说明:
	 * </p>
	 * 分页显示对象list
	 * 
	 * @param username
	 * @param currentPage
	 *            页面传递过来的当前页
	 * @param pageSize
	 *            页面传递过来的每页显示条数
	 * @param m
	 *            页面传递过来的的查询条件 map 其中的kv值的含义可以自定义
	 * @return
	 * @return List<Entity> 对象list
	 * @author whp
	 * @date 2015年7月16日
	 */
	@Override
	public List<Entity> findAll(String username, int currentPage, int pageSize, Map<String, Object> m) {
		Connection conn = OurDaoUtils.getConnection();
		QueryRunner qRunner = new QueryRunner();
		String sql = " select * from  " + clazz.getSimpleName() + " where 1=1";

		Set<Entry<String, Object>> set = m.entrySet();
		Iterator<Entry<String, Object>> io = set.iterator();
		while (io.hasNext()) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
			// easyui自动搜索控件 传递的参数值，一般作为sql语句的like条件使用。
			if ("q".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and htcode  like '%" + me.getValue() + "%'";
			}
			// easyui datagrid控件传递的排序字段名
			if ("sort".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " order by " + me.getValue();
			}
			// easyui datagrid控件传递的升序或者降序字段名
			if ("order".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " " + me.getValue();
			}
		}
		sql = sql + " limit " + (currentPage - 1) * pageSize + " , " + pageSize;

		List<Entity> nList = new ArrayList<Entity>();
		// 使用 BeanListHandler 实现将所有的结果集转换为 实体bean
		ResultSetHandler<List<Entity>> h = new BeanListHandler<Entity>(clazz);
		// 执行 SQL 语句,使用 BeanListHandler,返回对象的list集合
		try {
			nList = (List<Entity>) qRunner.query(conn, sql, h);
			return nList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	/**
	 * delete 方法
	 * <p>
	 * 方法说明:
	 * </p>
	 * 批量删除
	 * 
	 * @param ids
	 *            主键值数组
	 * @return
	 * @return boolean 是否操作成功
	 * @author whp
	 * @date 2015年7月16日
	 */
	@Override
	public boolean delete(String[] ids) {
		Field[] fs = clazz.getDeclaredFields();
		String pkName = fs[0].getName();// 反射得出主键名称
		Connection conn = OurDaoUtils.getConnection();
		String sqlin = "";
		for (String id : ids) {
			sqlin += "," + id;
		}
		sqlin = sqlin.substring(1);
		String sql = " delete from " + clazz.getSimpleName() + " where " + pkName + " in(" + sqlin + ")";
		QueryRunner qRunner = new QueryRunner();
		try {
			qRunner.update(conn, sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

}