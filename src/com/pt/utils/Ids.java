package com.pt.utils;

import java.util.HashMap;
import java.util.Map;

public class Ids {
	private static Map<String,String> cidmap;

	public static Map<String, String> getCidmap() {
		if(cidmap==null)
			cidmap=new HashMap<String, String>();
		return cidmap;
	}

	public static void setCidmap(Map<String, String> cidmap) {
		Ids.cidmap = cidmap;
	}

	

}
