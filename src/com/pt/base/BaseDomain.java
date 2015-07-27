package com.pt.base;


/**   
 * 版权所有：2015-whp
 * <br/>项目名称：pt2all 
 *
 * <br/>类描述：所有实体类的基类，含有成员变量主键生成器接口
 * <br/>类名称：com.pt.base.BaseDomain 
 * <br/>创建人：whp
 * <br/>创建时间：2015年7月27日 下午6:45:02
 * <br/>修改人：
 * <br/>修改时间：2015年7月27日 下午6:45:02
 * <br/>修改备注：
 * <br/>@version   V1.0 
 */
  
public class BaseDomain {
	/**
	 * @Fields basedbo :主键生成器接口
	 */ 
	protected BaseDbo basedbo;

	public BaseDomain() {
		 basedbo=new DboImpl();
	}

}
