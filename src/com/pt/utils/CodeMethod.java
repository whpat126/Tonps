package com.pt.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密解密方法
 * @author sq
 *
 */
public class CodeMethod {	
	/** 
	 * 加密 
	 * @param content 需要加密的内容 
	 * @param password  加密密码 
	 * @return 
	 */  
	public static String encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			
			String str = parseByte2HexStr(result);
			return str; // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解密
	 * @param content  待解密内容
	 * @param password  解密密钥
	 * @return 解密后内容
	 */
	public static String decrypt(String str, String password) {
		try {
			byte[] content = parseHexStr2Byte(str); 
			
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return new String(result); 
		} catch (Exception e) {
			System.out.println("解密失败");
		}
		return null;
	} 
	
	/**将二进制转换成16进制 
	 * @param buf byte（字节）类型
	 * @return 
	 */  
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
	
	/**将16进制转换为二进制 
	 * @param hexStr 字符串类型
	 * @return 
	 */  
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
	
	public void test(){}
	
	
	
	public static void main(String[] args) {
/*//		String content = "As`123~!@#$%^&*()_+{}|:<>?,./';[]\'";
		String content = "pt2all";
		String password = "平通信息";
		System.out.println("加密前： " + content);
//		long long1 = System.currentTimeMillis();
		//加密   
		String str = encrypt(content, password);  
		System.out.println("加密后：" + str);  
		//解密   
		String decryptResult = decrypt(str,password);
//		long long2 = System.currentTimeMillis();
		System.out.println("解密后： " + decryptResult); 
//		System.out.println(long2-long1);
 * 
*/	
//		String content = "0571C7068B4C39E75BF7C0107BE81BBC";
//		String password = "平通信息";
//		String decryptResult = decrypt(content,password);
////		long long2 = System.currentTimeMillis();
//		System.out.println("解密后： " + decryptResult); 
//	
	
	
	
	}
}
