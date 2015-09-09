package com.pt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.comet4j.core.CometContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.pt.domain.Message;
import com.pt.utils.Ids;

/**
 * 消息controller。
 * 
 * @author sq
 * 
 */
@Controller
public class MessageController {

	// @Autowired
	// @Qualifier("messageService")
	// private MessageService ms;

	/**
	 * 初始化显示消息的个数 author：songqi
	 * 
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/AddIds")
	private void Init(HttpSession session, HttpServletResponse response,
			String uname, String cId) throws IOException {
		// 根据用户名得到用户的id
		// 根据用户的主键得到消息的个数
		// PrintWriter out = response.getWriter();
		// out.print(10);
		if (Ids.getCidmap() == null)
			Ids.setCidmap(new HashMap<String, String>());
		Ids.getCidmap().put(uname, cId);
//		CometContext cc = CometContext.getInstance();
//		cc.getEngine();
	}

	/**
	 * 消息头的列表 author：songqi
	 * 
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/MessageTitle")
	private void getMessageTitle(HttpSession session,
			HttpServletResponse response) throws IOException {
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

	/**
	 * 消息详情页，全部消息（不包括删除消息） author：songqi
	 * 
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/unreadMsg")
	private void getUnreadMsg(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		List<Message> list = new ArrayList<Message>();
		Gson gson = new Gson();
		Message message1 = new Message();
		message1.setTitle("消息1");
		message1.setContents("消息1内容： ——————————————————————————————————————这是消息内容");
		Message message2 = new Message();
		message2.setTitle("消息2");
		message2.setContents("消息2内容： ——————————————————————————————————————这是消息内容");
		list.add(message1);
		list.add(message2);
		String str = gson.toJson(list);
		PrintWriter out = response.getWriter();
		out.print(str);
	}

	/**
	 * 消息详情页，用户未读消息 author：songqi
	 * 
	 * @param session
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/newMsg")
	private void getNewMsg(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		List<Message> list = new ArrayList<Message>();
		Gson gson = new Gson();
		Message message1 = new Message();
		message1.setTitle("新消息1");
		message1.setContents("新消息1内容： ——————————————————————————————————————这是消息内容");
		message1.setMsg_href("http://www.baidu.com/");
		Message message2 = new Message();
		message2.setTitle("新消息2");
		message2.setContents("新消息2内容： ——————————————————————————————————————这是消息内容");
		message2.setMsg_href("http://www.sina.com.cn/");
		list.add(message1);
		list.add(message2);
		String str = gson.toJson(list);
		PrintWriter out = response.getWriter();
		out.print(str);
	}

}
