package com.pt.test;

import java.util.HashMap;
import java.util.Map;

import com.pt.domain.Users;

public class TestDemo {

	public static void main(String[] args) {
//		String str1 = "userLogin.do";
//		String str2 = "adminLogin.do";
//		
//		if(str1.indexOf("Login.do")>=0)
//			System.out.println(true);
		Map<String, String> map=new HashMap<String, String>();
		Long long1=System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			String string = (new Users()).getPk_users();
			System.out.println(i+":"+string);
			map.put(string, string);
			
		}
		Long long2=System.currentTimeMillis();
		System.out.println(long1);
		System.out.println(long2);
		System.out.println(long2-long1);//3473 4359
		System.out.println(map.size());
		
	}
}
