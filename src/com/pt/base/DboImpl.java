/**  
* @Title: Dbo.java
* @Package com.pt.base
* @Description: TODO(用一句话描述该文件做什么)
* @author whp   
* @date 2015年7月27日 下午6:12:53
* @version V1.0  
*/ 
package com.pt.base;

import java.util.UUID;


/**   
 * 版权所有：2015-whp
 * <br/>项目名称：pt2all 
 *
 * <br/>类描述：基础数据库操作辅助接口的实现类，实现了包括主键生成等功能后续扩展主键规则可以调整此类即可
 * <br/>类名称：com.pt.base.Dbo 
 * <br/>创建人：whp
 * <br/>创建时间：2015年7月27日 下午6:12:53
 * <br/>修改人：
 * <br/>修改时间：2015年7月27日 下午6:12:53
 * <br/>修改备注：
 * <br/>@version   V1.0 
 */

public class DboImpl implements BaseDbo {

	/**
	 * <p>说明:  生成主键</p>
	 * @Title: getPk
	 * @return String
	 * @return 
	 */ 
	@Override
	public String genPk(){
		return UUID.randomUUID().toString().replace("-", "");
	}

}
