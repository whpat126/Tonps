package com.pt.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.pt.dao.impl.MyCompanyDaoImplPage;
import com.pt.domain.CorpGroup;
import com.pt.domain.MyCompany;
import com.pt.domain.PageBean;
import com.pt.domain.Users;
import com.pt.domain.Users_MyCompany;
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
	 * 根据用户的session找到id进而 检查用户状态：未加入企业、企业用户、企业管理员，将结果返回到jsp页面。
	 * @author：songqi
	 * @return 0表示非企业用户 1 表示为企业用户 2表示程序运行出错
	 */
	@RequestMapping("/checkUser")
	private void checkUser(HttpSession session, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		// 需要得到主键的方法
		String userId = userService.findByProp("userName", username).getPk_users();
		// 根据主键去数据库中查询用户是：未加入企业用户、企业用户、企业管理员、
		
		// 0901 14:15  直接判断该用户是否企业用户，不是企业用户，显示加入企业功能，企业用户，显示他的身份，普通用户和企业管理员
		int type = mcs.findByUserId(userId);
		out.print(type);
	}
	
	/**
	 * 用户加入企业页面：搜索企业，不使用模糊查找
	 * @author：songqi
	 */
	@RequestMapping("/checkCompany")
	private void checkCompany(HttpServletResponse response,String companyName)
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
	 * @param  
	 */
	@RequestMapping(value = "/joinCompany")
	private void joinCompany(HttpSession session, HttpServletResponse response,
			String pk_company) throws IOException {
		String username = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", username).getPk_users();
		int num = mcs.joinCompany(userId, pk_company);
		response.getWriter().print(num);
	}
	
	/**
	 * 创建企业的方法， 先判断企业是否存在，然后在执行创建
	 * @author：songqi
	 * @param company 
	 */
	@RequestMapping(value = "/company/createCompany")
	private String createCompany(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, MyCompany company) throws IOException {
		String username = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", username).getPk_users();
		MyCompany old_company = mcs.findByName(company.getName());
		if(null == old_company){
			company.setCompanyAdmin(userId);
			boolean flag = mcs.save(company);
			if(flag){
//				System.out.println("success");
				return "redirect:/company/myCompany.jsp";
			}else{
//				System.out.println("error");
				String msgError = "创建失败，请检查网络连接后刷新本页面重试!";
				request.setAttribute("msgError", msgError);
				return "/company/createCompany";
			}
		}else{
//			System.out.println("have ");
			String msgError = "创建失败，该企业已经存在您可以选择<a href='company/appealCompany.jsp'>申诉</a>!";
			request.setAttribute("msgError", msgError);
			return "/company/createCompany";
		}
	}
	
	
	/**
	 * 普通用户:我的企业功能
	 * @author：songqi
	 */
	@RequestMapping(value = "/userCompany")
	private void userCompany(HttpSession session, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", username).getPk_users();
		List<MyCompany> list = mcs.findUserCompanyByUserId(userId);
		Gson gson = new Gson();
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
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", username).getPk_users();
		boolean flag = mcs.outCompany(userId,pk_myCompany);
		if(flag){
			out.print("<script>window.location.href='index.jsp';</script>");
		}
	}
	
	/**
	 * 管理员： 我的企业功能，默认显示用户申请审核
	 * @author：songqi
	 */
	@RequestMapping(value = "/admin_userApply")
	private void adminCompany(HttpSession session, HttpServletResponse response,String action, String pageNum,String rowsNum)
			throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		// 查询该管理员名下的单位中正在申请加入企业或者成为企业管理员的用户
//		List<MyCompany> list = mcs.findUserApply(userId);
		MyCompanyDaoImplPage page = new MyCompanyDaoImplPage();
		if("".equals(action) || null == action){ //判断是否是页面加载
			pageNum = "1";
			rowsNum = "5";
		}
		if(Integer.parseInt(pageNum) < 1){ // 判断是否是第一页
			out.print("false");
		}else{
			PageBean list = page.findAll(username,Integer.parseInt(pageNum),Integer.parseInt(rowsNum));
			if(list.getDataList()==null||list.getDataList().size()<1){
				out.print("false");
			}else{
				Gson gson = new Gson();
				String str =  gson.toJson(list);
//				System.out.println(str);
				out.print(str);
			}
		}
	}
	/**
	 *  单位管理员批量同意用户加入企业或者成为企业管理员
	 * @author：songqi
	 * @param joinCompany
	 * @param applyAdmin
	 */
	@RequestMapping(value = "/Users_Apply")
	private void Users_Apply(HttpSession session, String joinCompany,
			String applyAdmin) throws IOException {
		mcs.usersApply(joinCompany, applyAdmin);
	}

	/**
	 * 管理员： 显示企业申请审核
	 * @author：songqi
	 */
	@RequestMapping(value = "/admin_companyApply2")
	private void admin_companyApply(HttpSession session,
			HttpServletResponse response, String action, String pageNum,
			String rowsNum) throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", username).getPk_users();
		// 查询该管理员名下的单位中正在申请加入企业或者成为企业管理员的用户
		MyCompanyDaoImplPage page = new MyCompanyDaoImplPage();
		if("".equals(action) || null == action){ //判断是否是页面加载
			pageNum = "1";
			rowsNum = "20";
		}
		if(Integer.parseInt(pageNum) < 1){ // 判断是否是第一页
			out.print("false");
		}else{
			PageBean list = page.findAll2(userId,Integer.parseInt(pageNum),Integer.parseInt(rowsNum));
			if(list.getDataList()==null||list.getDataList().size()<1){
				out.print("false");
			}else{
				Gson gson = new Gson();
				String str =  gson.toJson(list);
				out.print(str);
			}
		}
	}
	
	/**
	 *  单位管理员批量同意用户加入企业或者成为企业管理员
	 * @author：songqi
	 * @param joinCompany
	 * @param applyAdmin
	 */
	@RequestMapping(value = "/company/admin_companyApply")
	private void Users_Company(HttpSession session, String applyFatherCompany,
			String applyChildCompany) throws IOException {
		mcs.companyApply(applyFatherCompany, applyChildCompany);
		return ;
	}
	
	
	
	
	/**
	 * 管理员：企业业务申请，用户通过checkCompany请求找到该企业之后使用
	 * @author：songqi
	 */
	@RequestMapping(value = "/companyBusinessApply")
	private void admin_business(HttpSession session,
			HttpServletResponse response) throws IOException {
		String username = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", username).getPk_users();
		List<MyCompany> list = mcs.findAll(userId);
		String str = new Gson().toJson(list);
//		System.out.println(str);
		response.getWriter().print(str);
	}
	
	/**
	 * 管理员：提交该单位的申请，需注意申请类型和申请企业，以及不能重复申请
	 * @param applyCompany 提交申请  企业  
	 * @param joinCompany  被申请  加入的企业
	 * @param  business 申请类型 成为上级单位1或成为下级单位2
	 * @author：songqi
	 */
	@RequestMapping(value = "/submitCompanyApply")
	private void submitCompanyApply(HttpSession session, String applyCompany,
			HttpServletResponse response, String business, String joinCompany)
			throws IOException {
		String username = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", username).getPk_users();
		int num = mcs.submitCompanyApply(business,joinCompany,applyCompany,userId);
		response.getWriter().print(num);
	}
	
	
	/**
	 * 管理员 : 显示本管理员名下的所有管理单位信息
	 */
	@RequestMapping(value = "/company/queryCompany")
	private void queryCompany(HttpSession session, HttpServletResponse response)
			throws IOException {
		PrintWriter out = response.getWriter();
		String username = (String) session.getAttribute("userName");
		String userId = userService.findByProp("userName", username).getPk_users();
		List<MyCompany> list = mcs.findAll(userId);
		Gson gson = new Gson();
		String str = gson.toJson(list);
		out.print(str);
	}
	
	/**
	 * 管理员 : 点击某个单位后显示该单位的详细信息
	 * @param pk_myCompany
	 */
	@RequestMapping(value = "/company/queryCompanyById")
	private void queryCompanyById(HttpServletResponse response,
			String pk_myCompany) throws IOException {
		PrintWriter out = response.getWriter();
		MyCompany company = mcs.findById(pk_myCompany);
		Gson gson = new Gson();
		String str = gson.toJson(company);
		out.print(str);
	}
	
	/**
	 * 管理员维护企业信息
	 * author：songqi
	 */
	@RequestMapping(value = "/company/admin_companyInfo")
	private String admin_companyInfo(HttpSession session,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
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
		List<CorpGroup> list = new ArrayList<CorpGroup>();
		CorpGroup g1 = new CorpGroup();
		g1.setPk_corpgroup(1 + "");
		g1.setName("山东高速财务群");;
		list.add(g1);
		CorpGroup g2 = new CorpGroup();
		g2.setPk_corpgroup(2+"");
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
			CorpGroup g1 = new CorpGroup();
			g1.setPk_corpgroup(1+"");
			g1.setName("山东高速");;
			g1.setGroupUsers("A B C D E F G");
			String str = gson.toJson(g1);
			out.print(str);
			
		}else{
			CorpGroup g2 = new CorpGroup();
			g2.setPk_corpgroup(2+"");
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
			HttpServletResponse response, CorpGroup group) throws IOException {
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
		Gson gson = new Gson();
		List<Users> list = mcs.queryUserByCompanyId(pk_myCompany);
		String str = gson.toJson(list);
		out.print(str);
	}
	
	/**
	 * 管理员用户信息维护： 将用户升级为企业管理员
	 * author：songqi
	 */
	@RequestMapping(value = "/company/userToAdmin")
	private String userToAdmin(HttpSession session, HttpServletRequest req, String pk_company,
			String pk_users) throws IOException {
		boolean flag = mcs.update(pk_company, pk_users);
		if(flag){
			req.setAttribute("userInfomsg", "该用户成功升级为企业管理员!");
			return "company/admin_userInfo";
		}else{
			req.setAttribute("userInfomsg", "未升级成功!");
			return "company/admin_userInfo";
		}
	}
	
	/**
	 * 管理员用户信息维护： 将用户自本单位移除
	 * author：songqi
	 */
	@RequestMapping(value = "/company/removeUsers")
	private String removeUsers(HttpServletRequest req, String pk_company,
			String pk_users) throws IOException {
		boolean flag = mcs.remove(pk_company, pk_users);
		if(flag){
			req.setAttribute("userInfomsg", "用户删除成功!");
			return "company/admin_userInfo";
		}else{
			req.setAttribute("userInfomsg", "移出失败!");
			return "company/admin_userInfo";
		}
	}
	
	
	/**
	 * 管理员企业系统维护
	 * author：songqi
	 */
	@RequestMapping(value = "/admin_companySystem")
	private void admin_companySystem(HttpSession session, HttpServletResponse response)
			throws IOException {
//		PrintWriter out = response.getWriter();
//		String username = (String) session.getAttribute("userName");
		
		
		

	}

}
