package com.pt.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qq.connect.utils.json.JSONException;
import com.qq.connect.utils.json.JSONObject;


/**   
 * 版权所有：2015-whp
 * <br/>项目名称：pt2all 
 *
 * <br/>类描述：图灵API调用处理类
 * <br/>类名称：com.pt.controller.TulingController 
 * <br/>创建人：whp
 * <br/>创建时间：2015年7月31日 下午1:52:31
 * <br/>修改人：
 * <br/>修改时间：2015年7月31日 下午1:52:31
 * <br/>修改备注：
 * <br/>@version   V1.0 
 */
  
@Controller
public class TulingController {

	@RequestMapping("/kefu")
	public void kefu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		try {
			String APIKEY = "aa2082f755b94358513da99e47f00e7e";
//			System.out.println("request.getParameter获取的info:" + request.getParameter("info"));
			String INFO = URLEncoder.encode(request.getParameter("info"), "UTF-8");
//			System.out.println("URLEncoder.encode之后:" + INFO);
			String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;
//			System.out.println(getURL);
			URL getUrl = new URL(getURL);
			HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();
			connection.connect();

			// 取得输入流，并使用Reader读取
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			// 断开连接
			connection.disconnect();
//			System.out.println(sb.toString());
			JSONObject json = new JSONObject(sb.toString());
			String tlResult = json.getString("text");
			response.getWriter().write(tlResult);
//			System.out.println(tlResult);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
