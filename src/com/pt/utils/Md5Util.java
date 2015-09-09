/**  
 * @Title: Md5Util.java
 * @Package com.pt.utils
 * @Description: TODO(用一句话描述该文件做什么)
 * @author whp   
 * @date 2015年7月28日 下午6:38:08
 * @version V1.0  
 */
package com.pt.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 版权所有：2015-whp <br/>
 * 项目名称：pt2all
 * 
 * <br/>
 * 类描述：Md5加密类 <br/>
 * 类名称：com.pt.utils.Md5Util <br/>
 * 创建人：whp <br/>
 * 创建时间：2015年7月28日 下午6:38:08 <br/>
 * 修改人： <br/>
 * 修改时间：2015年7月28日 下午6:38:08 <br/>
 * 修改备注： <br/>
 * @version V1.0
 */

public class Md5Util {
	/**
	 * <p>说明:  加密工具类的生成加密字符串方法</p>
	 * @Title: genmd5
	 * @return String
	 * @param string
	 * @return 
	 */ 
	public static String genmd5(String string) {
		return md5(string);
	}

	private static String md5(String string) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("md5");
			md.update(string.getBytes());
			byte[] md5Bytes = md.digest();
			return bytes2Hex(md5Bytes);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static String bytes2Hex(byte[] byteArray) {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (byteArray[i] >= 0 && byteArray[i] < 16) {
				strBuf.append("0");
			}
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}

}
