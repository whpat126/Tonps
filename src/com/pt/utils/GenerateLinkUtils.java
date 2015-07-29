/**  
* @Title: GenerateLinkUtils.java
* @Package com.pt.utils
* @Description: TODO(用一句话描述该文件做什么)
* @author whp   
* @date 2015年7月28日 下午6:30:30
* @version V1.0  
*/ 
package com.pt.utils;

import com.pt.domain.User;


/**   
 * 版权所有：2015-whp
 * <br/>项目名称：pt2all 
 *
 * <br/>类描述：生成链接地址工具类，用户激活、密码重置等邮件中用到的超链接
 * <br/>类名称：com.pt.utils.GenerateLinkUtils 
 * <br/>创建人：whp
 * <br/>创建时间：2015年7月28日 下午6:30:30
 * <br/>修改人：
 * <br/>修改时间：2015年7月28日 下午6:30:30
 * <br/>修改备注：
 * <br/>@version   V1.0 
 */

public class GenerateLinkUtils {

	   /**
	 * @Fields CHECK_CODE :验证码的变量名，用在url中进行传递数据
	 */ 
	private static final String CHECK_CODE = "checkCode";  
	   /**
	 * @Fields WEB_URL : 网站访问地址
	 */ 
	private static final String WEB_URL = "http://localhost:8080";  
	      
	   
	    /** 
	     * 生成帐户激活链接 
	     */  
	    public static String generateActivateLink(User user) {  
	        return WEB_URL+"/AccountActivate/activateAccount?id="   
	                + user.getPk_users() + "&" + CHECK_CODE + "=" + generateCheckcode(user);  
	    }  
	      
	    /** 
	     * 生成重设密码的链接 
	     */  
	    public static String generateResetPwdLink(User user) {  
	        return WEB_URL+"/AccountActivate/resetPasswordUI?userName="   
	                + user.getUsername() + "&" + CHECK_CODE + "=" + generateCheckcode(user);  
	    }  
	      
	    /** 
	     * 生成验证帐户的MD5校验码 
	     * @param user  要激活的帐户 
	     * @return 将用户名和密码组合后，通过md5加密后的16进制格式的字符串 
	     */  
	    public static String generateCheckcode(User user) {  
	        String userName = user.getName();  
	        String userMail = user.getEmail();  
	        //过期时间戳，用于校验链接的合法性
	        String ts = String.valueOf(System.currentTimeMillis()+86400000);  
	        String md5sString= Md5Util.genmd5(userName + ":" + userMail);  
	        //采用对称加密，用于后续提取出来时间戳ts，校验链接是否过期
//	        return Encode.encode(md5sString+":"+ts);  
	        return CodeMethod.encrypt((md5sString+":"+ts),"2015");  
	    }  
	      
	    /** 
	     * 验证校验码是否和注册时发送的验证码一致 
	     * @param user 要激活的帐户 
	     * @param checkcode 注册时发送的校验码 
	     * @return 如果一致返回true，否则返回false 
	     */  
	    /**
	     * <p>说明:  </p>
	     * @Title: verifyCheckcode
	     * @return boolean 是否有效
	     * @param checkCode 激活或者重置密码时的验证码
	     * @return 
	     */ 
	    public static boolean verifyCheckcode(String checkCode) {  
//	        String []temps = Encode.decode(checkCode).split(":"); 
	        String []temps = CodeMethod.decrypt(checkCode,"2015").split(":"); 
	        Long ts=Long.parseLong(temps[1]);
	        return System.currentTimeMillis()<=ts;
	    }  
	  
	 

}
