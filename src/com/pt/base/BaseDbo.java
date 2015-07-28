package com.pt.base;
/**   
 * 版权所有：2015-whp
 * <br/>项目名称：pt2all 
 *
 * <br/>类描述：基础数据库操作辅助类，可包括主键生成等
 * <br/>类名称：com.pt.base.BaseDbo 
 * <br/>创建人：whp
 * <br/>创建时间：2015年7月27日 下午6:12:53
 * <br/>修改人：
 * <br/>修改时间：2015年7月27日 下午6:12:53
 * <br/>修改备注：
 * <br/>@version   V1.0 
 */


  
public interface BaseDbo {


	/**
	 * <p>说明:  返回生成的主键</p>
	 * @Title: getPk
	 * @return String
	 * @return 
	 */ 
	String genPk();
}