package com.pt.base;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

public  class BaseDaoImpl<Entity> implements BaseDao<Entity> {

	// public
	protected Class<Entity> clazz;

	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {

		// System.out.println(this.getClass());
		// System.out.println(this.getClass().getGenericSuperclass());
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 带有真实类型参数的对象
		clazz = (Class<Entity>) pt.getActualTypeArguments()[0];
	}

	/**
	 * 保存方法
	 */
	@Override
	public boolean save(Entity obj) {
		// obj.getSimpleName();
		Connection conn = OurDaoUtils.getConnection();
		String sql = "insert into " + clazz.getSimpleName() + " values(null ";
		// 可以获取本类所声明的变量
		Field[] fs = clazz.getDeclaredFields();
		System.out.println(fs.length);

		for (int i = 1; i < fs.length; i++) {
			sql += ",? ";
		}
		sql = sql + ")";
		System.out.println(sql);

		// 进行预编译
		PreparedStatement ps = OurDaoUtils.getPs(conn, sql);

		// ps.setString(1, user.getName());

		try {
			for (int i = 1; i < fs.length; i++) {
				// 拼接方法的名称
				String MethodName = "get" + Character.toUpperCase(fs[i].getName().charAt(0)) + fs[i].getName().substring(1);
				System.out.println("MethodName:"+MethodName);
				Method m = clazz.getMethod(MethodName);
				System.out.println(">>>>>>>>>>>"+m.invoke(obj));
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
	@Override
	public boolean save(Entity obj,String tablename) {
		// obj.getSimpleName();
		Connection conn = OurDaoUtils.getConnection();
		String sql = "insert into " + tablename + " values(null ";
		// 可以获取本类所声明的变量
		Field[] fs = clazz.getDeclaredFields();
		System.out.println(fs.length);
		
		for (int i = 1; i < fs.length; i++) {
			sql += ",? ";
		}
		sql = sql + ")";
		System.out.println(sql);
		
		// 进行预编译
		PreparedStatement ps = OurDaoUtils.getPs(conn, sql);
		
		// ps.setString(1, user.getName());
		
		try {
			for (int i = 1; i < fs.length; i++) {
				// 拼接方法的名称
				String MethodName = "get" + Character.toUpperCase(fs[i].getName().charAt(0)) + fs[i].getName().substring(1);
				System.out.println("MethodName:"+MethodName);
				Method m = clazz.getMethod(MethodName);
				System.out.println(">>>>>>>>>>>"+m.invoke(obj));
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
	 * 更新方法 无返回值
	 */
	@Override
	public void update(Entity obj, String pk) {
		Connection conn = OurDaoUtils.getConnection();
		// update user set name = ? , age = ? where id = ?
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OurDaoUtils.close(ps);
		OurDaoUtils.close(conn);
	}

	/**
	 * 删除方法
	 */
	@Override
	public boolean delete(Long id, String pk) {
		Connection conn = OurDaoUtils.getConnection();
		String sql = " delete from " + clazz.getSimpleName() + " where " + pk + " =" + id;
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
	 * 查询所有
	 */
	@Override
	public List<Entity> findAll() throws Exception {
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

	@Override
	public Entity findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return findById( "id",id.toString());
	}

	@Override
	public Entity findById(String prop, String value) throws Exception {

		Connection conn = OurDaoUtils.getConnection();
		String sql = " select * from  " + clazz.getSimpleName() + " where " + prop + " = '" + value+"'";
		try {
			QueryRunner qRunner = new QueryRunner();
			List<Entity> nList = new ArrayList<Entity>();
			// 使用 BeanListHandler 实现将所有的结果集转换为 实体bean
			ResultSetHandler<List<Entity>> h = new BeanListHandler<Entity>(clazz);
			// 执行 SQL 语句,使用 BeanListHandler,返回对象的list集合
			nList = (List<Entity>) qRunner.query(conn, sql, h);
			return nList.get(0);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	@Override
	public boolean delete(Long id) {
		Connection conn = OurDaoUtils.getConnection();
		String sql = " delete from  " + clazz.getSimpleName() + " where id = " + id;
		try {
			QueryRunner qRunner = new QueryRunner();
			qRunner.update(conn, sql);
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DbUtils.closeQuietly(conn);
		}
	}

	public int getTotal(String sql) {
		Connection conn = OurDaoUtils.getConnection();
		QueryRunner qRunner = new QueryRunner();
		try {
			int count = (Integer) qRunner.query(conn, sql, new ScalarHandler<Integer>(1));
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

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

	@Override
	public List<Entity> findAll(String username, int currentPage, int pageSize, Map<String, Object> m) {
		Connection conn = OurDaoUtils.getConnection();
		QueryRunner qRunner = new QueryRunner();
		String sql = " select * from  " + clazz.getSimpleName() + " where 1=1";

		Set<Entry<String, Object>> set = m.entrySet();
		Iterator<Entry<String, Object>> io = set.iterator();
		while (io.hasNext()) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
			if ("htcode".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " like '%" + me.getValue() + "%'";
			}
			if ("dwname".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " like '%" + me.getValue() + "%'";
			}
			if ("q".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and htcode  like '%" + me.getValue() + "%'";
			}
			if ("sort".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " order by " + me.getValue();
			}
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

}