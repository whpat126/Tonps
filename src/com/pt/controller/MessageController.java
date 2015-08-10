package com.pt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.pt.domain.Message;


/**
 *  消息controller。
 * @author sq
 *
 */
@Controller
public class MessageController {

//	@Autowired
//	@Qualifier("messageService")
//	private MessageService ms;
	
	/**
	 * 初始化显示消息的个数
	 * author：songqi
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/MessageInit")
	private void Init(HttpSession session, HttpServletResponse response) throws IOException{
		// 根据用户名得到用户的id
		// 根据用户的主键得到消息的个数
		PrintWriter out = response.getWriter();
		out.print(3);
	}
	
	@RequestMapping("/MessageTitle")
	private void getMessageTitle(HttpSession session, HttpServletResponse response) throws IOException{
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		List<Message> list = new ArrayList<Message>();
		Gson gson = new Gson();
		Message message1 = new Message();
		message1.setTitle("消息1");
		Message message2 = new Message();
		message2.setTitle("消息2");
		list.add(message1);
		list.add(message2);
		String str = gson.toJson(list);
		PrintWriter out = response.getWriter();
		out.print(str);
	}
	
	
	
	
}
