package com.pt.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Test;

import com.pt.domain.User;

public class PtMail {

	/**
	 * @Fields props : 邮件配置文件,配置邮件服务器的地址发件箱密码等信息
	 */
	static Properties props;

	static {
		props = new Properties();
		try {
			props.load(PtMail.class.getClassLoader().getResourceAsStream("mailConfig.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final String serviceMailHostName = props.getProperty("mail.serviceMailHostName");
	public static final int serviceMailPortNumber = Integer.parseInt(props.getProperty("mail.serviceMailPortNumber"));
	public static final boolean ssl = Boolean.parseBoolean(props.getProperty("mail.ssl"));
	public static final String serviceMailMaster = props.getProperty("mail.serviceMailMaster");
	public static final String serviceName = props.getProperty("mail.serviceName");
	public static final String serviceMailPwd = Encode.decode(props.getProperty("mail.serviceMailPwd"));
	/**
	 * @Fields defaUserMail : 用户邮箱为空时。发送到此邮箱
	 */
	public static String defaUserMail = props.getProperty("mail.defaUserMail");

	
	/**
	 * <p>说明:  注册成功后,向用户发送帐户激活链接的邮件</p>
	 * @Title: sendAccountActivateEmail
	 * @return void
	 * @param user 
	 */ 
	public static void sendAccountActivateEmail(User user) {
		String userMail=user.getEmail();
		if (userMail == null)
			userMail = PtMail.defaUserMail;
		// Create the email message
		HtmlEmail email = new HtmlEmail();
		email.setHostName(serviceMailHostName);
		email.setCharset("UTF-8");
		email.setSmtpPort(serviceMailPortNumber);
		email.setAuthenticator(new DefaultAuthenticator(serviceMailMaster, serviceMailPwd));
		email.setSSLOnConnect(ssl);
		try {
			email.addTo(userMail);
			email.setFrom(serviceMailMaster,serviceName);
			email.setSubject("账号激活邮件");
//			String msg = "http://www.baidu.com";
			String msg=GenerateLinkUtils.generateActivateLink(user);

			// set the html message
			email.setHtmlMsg("<html><head><meta charset='UTF-8'></head><body>"
					+ "HI!"+user.getUsername()+":<br/>"
					+ "感谢您注册本系统！此邮件是系统发给您的账户激活邮件。您只需要点击以下链接即可激活账户："
					+ "<p><a href='"+msg+"'><font color='#0000ff'>"
					+msg 
					+"</font></a><br><font color='#ff0000'>请于24小时内点击</font></p><p>如果上述链接无法点击，可以将上述链接复制到浏览器地址栏中进行访问。</p></body></html>");

			// set the alternative message
			email.setTextMsg("Your email client does not support HTML messages");

			// send the email
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * <p>说明:  发送重设密码链接的邮件</p>
	 * @Title: sendResetPasswordEmail
	 * @return void
	 * @param user 
	 */ 
	public static void sendResetPasswordEmail(User user) {
		String userMail=user.getEmail();
		if (userMail == null)
			userMail = PtMail.defaUserMail;
		// Create the email message
		HtmlEmail email = new HtmlEmail();
		email.setHostName(serviceMailHostName);
		email.setCharset("UTF-8");
		email.setSmtpPort(serviceMailPortNumber);
		email.setAuthenticator(new DefaultAuthenticator(serviceMailMaster, serviceMailPwd));
		email.setSSLOnConnect(ssl);
		try {
			email.addTo(userMail);
			email.setFrom(serviceMailMaster,serviceName);
			email.setSubject("重置账户密码");
//			String msg = "http://www.baidu.com";
			String msg=GenerateLinkUtils.generateResetPwdLink(user);
			
			// set the html message
			email.setHtmlMsg("<html><head><meta charset='UTF-8'></head><body>"
					+ "HI!"+user.getUsername()+":<br/>"
							+ "感谢您注册本系统！此邮件是系统发给您的密码重置邮件。内容保密请您务必保密此邮件和其中的链接.<br/>"
							+ "要使用新的密码, 请使用以下链接进行密码重置操作:"
					+ "<p><a href='"
					+msg
					+"'><font color='#0000ff'>"
					+ msg
					+ "</font></a><br><font color='#ff0000'>请于24小时内点击</font></p><p>如果上述链接无法点击，可以将上述链接复制到浏览器地址栏中进行访问。</p>"
					+ "</body></html>");
			
			// set the alternative message
			email.setTextMsg("Your email client does not support HTML messages");
			
			// send the email
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * <p>
	 * 说明: 发送邮件给客户(简单邮件模式)
	 * </p>
	 * 
	 * @Title: sendSimpMail
	 * @return void
	 * @param userMail
	 *            用户邮箱地址
	 * @param msg
	 *            邮件内容
	 */
	public static void sendSimpMail(String userMail, String msg) {
		if (userMail == null)
			userMail = PtMail.defaUserMail;
		Email email = new SimpleEmail();
		email.setHostName(serviceMailHostName);
		email.setCharset("utf-8");
		email.setSmtpPort(serviceMailPortNumber);
		email.setAuthenticator(new DefaultAuthenticator(serviceMailMaster, serviceMailPwd));
		email.setSSLOnConnect(ssl);
		try {
			email.setFrom(serviceMailMaster);
			email.setSubject("ServiceMail");
			email.addTo(userMail);
			email.setMsg(msg);
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	@Test
	public  void Test() {
		User user=new User();
		user.setEmail(null);
		PtMail.sendAccountActivateEmail(user);
		PtMail.sendResetPasswordEmail(user);
	}


	public static void main(String[] args) throws IOException {

		User user=new User();
		user.setUsername("张三");
		user.setEmail(null);
		PtMail.sendAccountActivateEmail(user);
		PtMail.sendResetPasswordEmail(user);

	}

	

}
