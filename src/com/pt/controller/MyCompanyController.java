package com.pt.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.pt.domain.Group;
import com.pt.domain.MyCompany;
import com.pt.domain.Users;
import com.pt.service.MyCompanyService;
import com.pt.service.UsersService;

@Controller
public class MyCompanyController {

//	@Autowired
//	@Qualifier("messageService")
//	private MessageService ms;
	@Autowired
	@Qualifier("userService")
	private UsersService userService;
	@Autowired
	@Qualifier("myCompanyService")
	private MyCompanyService mcs;
	
	/**
	 * 检查用户状态：未加入企业、企业用户、企业管理员，将结果返回到jsp页面。
	 * @author：songqi
	 */
	@RequestMapping("/checkUser")
	private void checkUser(HttpSession session, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		// boolean flag = false; //
		// 需要得到主键的方法
		String userId = userService.findByProp("userName", username).getPk_users();
		// 根据主键去数据库中查询用户是：未加入企业用户、企业用户、企业管理员、
		
		//写死：未加入企业：两种方式，1 去数据库中查两次，即判断有没有加入企业和是否是管理员  //2 去库查一次，返回三种内容int, 0 没有企业 1 企业普通用户  2 企业管理员
		// 0901 14:15  直接判断该用户是否企业用户，不是企业用户，显示加入企业功能，企业用户，显示他的身份，普通用户和企业管理员
		boolean flag = mcs.findByUserId(userId);
		out.print(flag);
	}
	
	/**
	 * 检查用户状态：未加入企业、企业用户、企业管理员，将结果返回到jsp页面。
	 * @author：songqi
	 */
	@RequestMapping("/checkCompany")
	private void checkCompany(HttpSession session, HttpServletResponse response,String companyName)
			throws IOException {
		PrintWriter out = response.getWriter();
		
		MyCompany company = mcs.findByName(companyName);
		if( null == company){
			out.print("false");
		}else{
			Gson gson = new Gson();
			String str = gson.toJson(company);
			out.print(str);
		}
		

	}
	
	
	/**
	 * 未加入企业的用户:加入企业的方法，
	 * @author：songqi
	 * @param pk_company 
	 */
//	@RequestMapping(value = "/joinCompany", method = { RequestMethod.GET })
	@RequestMapping(value = "/joinCompany")
	private void joinCompany(HttpSession session, HttpServletResponse response,
			String pk_company) throws IOException {
		String username = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", username).getPk_users();
		boolean flag = mcs.joinCompany(userId, pk_company);
		PrintWriter out = response.getWriter();
		if (flag) {
			out.print(true);
		}

	}
	
	/**
	 * 普通用户:我的企业功能
	 * @author：songqi
	 */
	@RequestMapping(value = "/userCompany")
	private void userCompany(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		Gson gson = new Gson();
		List<MyCompany> list = new ArrayList<MyCompany>();
		MyCompany mc1 = new MyCompany();
		mc1.setPk_myCompany("1");
		mc1.setName("山东高速");
		mc1.setCompanyAdmin("高谦");
		list.add(mc1);
		MyCompany mc2 = new MyCompany();
		mc2.setPk_myCompany("0002");
		mc2.setName("山东平通信息");
		mc2.setCompanyAdmin("宋琪");
		list.add(mc2);
		String str = gson.toJson(list);
		
		out.print(str);

	}
	
	/**
	 * 普通用户：申请成为管理员
	 * @author：songqi
	 * @param pk_myCompany
	 */
	@RequestMapping(value = "/applyAdmin", method = { RequestMethod.GET })
	private void applyAdmin(HttpSession session, HttpServletResponse response,
			String pk_myCompany) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", username).getPk_users();
		boolean flag = mcs.applyAdmin(userId,pk_myCompany);
		if(flag){
			out.print("<script>alert('申请已经提交给管理员!')</script>");
			out.print("<script>history.go(-1);</script>");
		}
	}
	
	/**
	 * 普通用户：退出企业
	 * @author：songqi
	 * @param pk_myCompany
	 */
	@RequestMapping(value = "/outCompany", method = { RequestMethod.GET })
	private void outCompany(HttpSession session, HttpServletResponse response,
			String pk_myCompany) throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", username).getPk_users();
		boolean flag = mcs.outCompany(userId,pk_myCompany);
		if(flag){
			out.print("<script>window.location.href='index.jsp';</script>");
		}
	}
	
	
	/**
	 * 管理员： 用户申请审核
	 * @author：songqi
	 */
	@RequestMapping(value = "/admin_userApply")
	private void adminCompany(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		Gson gson = new Gson();
		List<MyCompany> list = new ArrayList<MyCompany>();
		MyCompany mc1 = new MyCompany();
		mc1.setPk_myCompany(1+"");
		mc1.setType("申请加入企业");
		mc1.setUserName("宋琪");
		mc1.setUser_phone("111222333");
		mc1.setUser_company("山东平通");
		list.add(mc1);
		MyCompany mc2 = new MyCompany();
		mc2.setPk_myCompany(2+"");
		mc2.setUserName("张飞");
		mc2.setUser_phone("222333444");
		mc2.setUser_company("山东平通");
		mc2.setType("申请成为管理员");
		list.add(mc2);
		String str = gson.toJson(list);
		
		out.print(str);

	}
	/**
	 * 管理员：企业申请审核
	 * @author：songqi
	 */
	@RequestMapping(value = "/admin_companyApply")
	private void admin_companyApply(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		Gson gson = new Gson();
		List<MyCompany> list = new ArrayList<MyCompany>();
		MyCompany mc1 = new MyCompany();
		mc1.setPk_myCompany(1+"");
		mc1.setType("申请成为上级");
		mc1.setUserName("宋琪");
		mc1.setUser_phone("111222333");
		mc1.setUser_company("山东平通");
		list.add(mc1);
		MyCompany mc2 = new MyCompany();
		mc2.setPk_myCompany(2+"");
		mc2.setUserName("王五");
		mc2.setUser_phone("222333444");
		mc2.setUser_company("山东平通");
		mc2.setType("申请成为下级");
		list.add(mc2);
		String str = gson.toJson(list);
		
		out.print(str);

	}
	
	/**
	 * 管理员：业务申请审核
	 * @author：songqi
	 */
	@RequestMapping(value = "/admin_business")
	private void admin_business(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		Gson gson = new Gson();
		List<MyCompany> list = new ArrayList<MyCompany>();
		MyCompany mc1 = new MyCompany();
		mc1.setPk_myCompany(1+"");
		mc1.setType("申请成为上级");
		mc1.setUserName("宋琪");
		mc1.setUser_phone("111222333");
		mc1.setUser_company("山东平通");
		list.add(mc1);
		MyCompany mc2 = new MyCompany();
		mc2.setPk_myCompany(2+"");
		mc2.setUserName("王五");
		mc2.setUser_phone("222333444");
		mc2.setUser_company("山东平通");
		mc2.setType("申请成为下级");
		list.add(mc2);
		String str = gson.toJson(list);
		
		out.print(str);

	}
	
	/**
	 * 管理员 : 显示本管理员名下的所有管理单位信息
	 */
	@RequestMapping(value = "/company/queryCompany")
	private void queryCompany(HttpSession session, HttpServletResponse response)throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", username).getPk_users();
		
//		System.out.println("queryCompany");
		
		Gson gson = new Gson();
		List<MyCompany> list = new ArrayList<MyCompany>();
		MyCompany mc1 = new MyCompany();
		mc1.setPk_myCompany(1+"");
		mc1.setName("山东高速");;
		list.add(mc1);
		MyCompany mc2 = new MyCompany();
		mc2.setPk_myCompany(2+"");
		mc2.setName("山东平通");
		list.add(mc2);
		String str = gson.toJson(list);
		out.print(str);
		
	}
	
	/**
	 * 管理员 : 点击某个单位后显示该单位的详细信息
	 * @param pk_myCompany
	 */
	@RequestMapping(value = "/company/queryCompanyById")
	private void queryCompanyById(HttpSession session,
			HttpServletResponse response, String pk_myCompany) throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", username).getPk_users();
		Gson gson = new Gson();
		if("1".equals(pk_myCompany)){
			MyCompany mc1 = new MyCompany();
			mc1.setPk_myCompany(1+"");
			mc1.setName("山东高速");;
			mc1.setAddress("中国山东济南");
			mc1.setPhone("150640203521");
			String str = gson.toJson(mc1);
			out.print(str);
			
		}else{
			MyCompany mc2 = new MyCompany();
			mc2.setPk_myCompany(2+"");
			mc2.setName("山东平通");
			mc2.setAddress("济南");
			mc2.setPhone("250640203522");
			String str = gson.toJson(mc2);
			out.print(str);
			
		}
		
	}
	
	/**
	 * 管理员维护企业信息
	 * author：songqi
	 */
	@RequestMapping(value = "/company/admin_companyInfo")
	private String admin_companyInfo(HttpSession session,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		PrintWriter out = response.getWriter();
		String fileDir = session.getServletContext().getRealPath("upload/");
		String localFileName = "";
		String serverFileName = "";
		String serverFilePath = "";
		String companyInfomsg = "";
		if (ServletFileUpload.isMultipartContent(request)) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(factory.getRepository());
			ServletFileUpload upload = new ServletFileUpload(factory);
			int size = 10 * 1024;
			List<FileItem> listForms = null;
			try {
				listForms = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			MyCompany mc = new MyCompany();
			for (FileItem listForm : listForms) {
				if (listForm.isFormField()) { // 普通表单
				// System.out.println("---"+ listForm.getFieldName());
					String name = listForm.getFieldName();
					if ("name".equals(name)) {
//						mc.setName(listForm.getString()); // 会出现中文乱码，解决方法是getString("utf-8"); 
						mc.setName(listForm.getString("utf-8")); 
					}
					if ("phone".equals(name))	mc.setPhone(listForm.getString("utf-8"));
					
				} else { // 上传文件
					// 在前台判断文件是否为空
//					if (null != listForm.getName()
//							&& !listForm.getName().equals("")) {

					long upFileSize = listForm.getSize();
					if (upFileSize > size) {
						companyInfomsg = "文件限制"+size+"M";
						request.setAttribute("companyInfomsg", companyInfomsg);
						return "company/admin_companyInfo";
					}
					localFileName = listForm.getName(); // 获取文件名
					int ii = localFileName.lastIndexOf(".");
					String sExt = localFileName.substring(ii,
							localFileName.length());// 取文件名的后缀
					String newFileName = UUID.randomUUID().toString();
					serverFileName = newFileName;
					serverFileName += sExt;
					File dir = new File(fileDir);
					if (!dir.exists()) {
						dir.mkdir();
					}
					serverFilePath = fileDir + "/" + serverFileName;
					File serverFile = new File(serverFilePath);
					try {
						listForm.write(serverFile);
					} catch (Exception e) {
						e.printStackTrace();
					}
//					}
					
//					else {
//						return "company/admin_companyInfo";
//					}
				}
			}

			System.out.println(mc.getName());
			System.out.println(mc.getPhone());
			companyInfomsg = "单位信息更新成功!";
			request.setAttribute("companyInfomsg", companyInfomsg);
			return "company/admin_companyInfo";
		}

		return "company/admin_companyInfo";
	}
	
	/**
	 * 管理员群组设置 ： 页面加载显示群组名称
	 * 要求管理员只能看到本人权限内的用户，群组跟企业没有关系
	 * author：songqi
	 */
	@RequestMapping(value = "company/queryGroup")
	private void queryGroup(HttpSession session, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		Gson gson = new Gson();
		List<Group> list = new ArrayList<Group>();
		Group g1 = new Group();
		g1.setPk_group(1 + "");
		g1.setName("山东高速财务群");;
		list.add(g1);
		Group g2 = new Group();
		g2.setPk_group(2+"");
		g2.setName("山东平通管理群");
		list.add(g2);
		String str = gson.toJson(list);
		out.print(str);
	}
	
	
	/**
	 * 管理员群组设置 ： 查询群组信息显示到页面中
	 * author：songqi
	 */
	@RequestMapping(value = "company/queryGroupById")
	private void queryGroupById(HttpSession session,
			HttpServletResponse response, String pk_group) throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		
		Gson gson = new Gson();
		if("1".equals(pk_group)){
			Group g1 = new Group();
			g1.setPk_group(1+"");
			g1.setName("山东高速");;
			g1.setGroupUsers("A B C D E F G");
			String str = gson.toJson(g1);
			out.print(str);
			
		}else{
			Group g2 = new Group();
			g2.setPk_group(2+"");
			g2.setName("山东平通");;
			g2.setGroupUsers("1 2 3 4 5 6 7");
			String str = gson.toJson(g2);
			out.print(str);
			
		}
	}
	
	/**
	 * 管理员群组设置 ： 查询群组信息显示到页面中
	 * author：songqi
	 */
	@RequestMapping(value = "company/admin_group")
	private String admin_group(HttpSession session, HttpServletRequest request, 
			HttpServletResponse response, Group group) throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		System.out.println(group.getName());
		System.out.println(group.getGroupUsers());
		
		request.setAttribute("groupInfomsg", "群组信息更新成功!");
		return "company/admin_group";
		
	}
	
	/**
	 * 管理员用户信息维护： 通过单位主键查询本单位的所有用户
	 * author：songqi
	 */
	@RequestMapping(value = "/company/queryUserByCompanyId")
	private void queryUserByCompanyId(HttpSession session, String pk_myCompany, 
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		Gson gson = new Gson();
		List<Users> list = new ArrayList<Users>();
		if("1".equals(pk_myCompany)){
			Users u1 = new Users();
			u1.setPk_users(1 + "");
			u1.setUsername("财费用");
			u1.setPhone("15322345321");
			list.add(u1);
			String str = gson.toJson(list);
			out.print(str);
		}else{
			Users g2 = new Users();
			g2.setPk_users(2+"");
			g2.setUsername("高三都");
			g2.setPhone("10086");
			Users g3 = new Users();
			g3.setPk_users(2+"");
			g3.setUsername("高四都");
			g3.setPhone("10010");
			list.add(g2);
			list.add(g3);
			String str = gson.toJson(list);
			out.print(str);
		}
	}
	
	/**
	 * 管理员用户信息维护： 将用户升级为企业管理员
	 * author：songqi
	 */
	@RequestMapping(value = "/company/userToAdmin")
	private String userToAdmin(HttpSession session, HttpServletRequest req, String pk_myCompany,
			String pk_users, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		String username = (String) session.getAttribute("userName");
		
		System.out.println("将 " + pk_myCompany + "  用户：" + pk_users +  "  升级管理员 ");
		req.setAttribute("userInfomsg", "该用户成功升级为企业管理员!");
		return "company/admin_userInfo";
	}
	
	/**
	 * 管理员用户信息维护： 将用户自本单位移除
	 * author：songqi
	 */
	@RequestMapping(value = "/company/removeUsers")
	private String removeUsers(HttpSession session, HttpServletRequest req,
			String pk_myCompany, String pk_users, HttpServletResponse resp)
			throws IOException {
		PrintWriter out = resp.getWriter();
		String username = (String) session.getAttribute("userName");
		
		System.out.println("将 " + pk_myCompany + "  用户：" + pk_users +  "  删除 ");
		req.setAttribute("userInfomsg", "用户删除成功!");
		return "company/admin_userInfo";
	}
	
	
	/**
	 * 管理员企业系统维护
	 * author：songqi
	 */
	@RequestMapping(value = "/admin_companySystem")
	private void admin_companySystem(HttpSession session, HttpServletResponse response)
			throws IOException {
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		
		
		

	}

}
