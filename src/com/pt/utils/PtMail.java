package com.pt.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.junit.Test;

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
	public static final String serviceMailPwd = Encode.decode(props.getProperty("mail.serviceMailPwd"));
	/**
	 * @Fields defaUserMail : 用户邮箱为空时。发送到此邮箱
	 */
	public static String defaUserMail = props.getProperty("mail.defaUserMail");

	/**
	 * <p>
	 * 说明: 发送邮件给客户(简单邮件模式) 现阶段使用此方法
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

	/**
	 * <p>
	 * 说明: 发送邮件给客户(附件邮件模式)
	 * </p>
	 * 
	 * @Title: sendAttachmentMail
	 * @return void
	 * @param userMail
	 */
	public static void sendAttachmentMail(String userMail) {
		if (userMail == null)
			userMail = PtMail.defaUserMail;
		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("config/mailConfig.properties");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Picture of John");
		attachment.setName("cash.png");

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName(serviceMailHostName);
		email.setSmtpPort(serviceMailPortNumber);
		email.setAuthenticator(new DefaultAuthenticator(serviceMailMaster, serviceMailPwd));
		email.setSSLOnConnect(ssl);
		try {
			email.addTo(userMail);
			email.setFrom(serviceMailMaster);
			email.setSubject("The picture");
			email.setMsg("Here is the picture you wanted");

			// add the attachment
			email.attach(attachment).attach(attachment);
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send the email
	}

	/**
	 * <p>
	 * 说明: 发送邮件给客户(url邮件模式)
	 * </p>
	 * 
	 * @Title: sendAttachmentUrlMail
	 * @return void
	 * @param userMail
	 */
	public static void sendAttachmentUrlMail(String userMail) {
		if (userMail == null)
			userMail = PtMail.defaUserMail;
		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();
		try {
			attachment.setURL(new URL("http://www.apache.org/images/asf_logo_wide.gif"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("Apache logo");
		attachment.setName("asf_logo_wide.gif");

		// Create the email message
		MultiPartEmail email = new MultiPartEmail();
		email.setHostName(serviceMailHostName);
		email.setSmtpPort(serviceMailPortNumber);
		email.setAuthenticator(new DefaultAuthenticator(serviceMailMaster, serviceMailPwd));
		email.setSSLOnConnect(ssl);
		try {
			email.addTo(userMail);
			email.setFrom(serviceMailMaster);
			email.setSubject("The logo");
			email.setMsg("Here is Apache's logo");

			// add the attachment
			email.attach(attachment);

			// send the email
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 说明: 发送邮件给客户(html邮件模式)
	 * </p>
	 * 
	 * @Title: sendAttachmentHtmlMail
	 * @return void
	 * @param userMail
	 */
	public static void sendAttachmentHtmlMail(String userMail) {
		if (userMail == null)
			userMail = PtMail.defaUserMail;
		// Create the email message
		HtmlEmail email = new HtmlEmail();
		email.setHostName(serviceMailHostName);
		email.setSmtpPort(serviceMailPortNumber);
		email.setAuthenticator(new DefaultAuthenticator(serviceMailMaster, serviceMailPwd));
		email.setSSLOnConnect(ssl);
		try {
			email.addTo(userMail);
			email.setFrom(serviceMailMaster);
			email.setSubject("Test email with inline image");

			// embed the image and get the content id
			URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
			String cid = email.embed(url, "Apache logo");

			// set the html message
			email.setHtmlMsg("<html>The apache logo - <img src=\"cid:" + cid + "\"></html>");

			// set the alternative message
			email.setTextMsg("Your email client does not support HTML messages");

			// send the email
			email.send();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 说明: 发送邮件给客户(img邮件模式)
	 * </p>
	 * 
	 * @Title: sendAttachmentHtmlMailWithImg
	 * @return void
	 * @param userMail
	 */
	public static void sendAttachmentHtmlMailWithImg(String userMail) {
		if (userMail == null)
			userMail = PtMail.defaUserMail;
		// load your HTML email template
		String htmlEmailTemplate = ".... <img src=\"http://www.apache.org/images/feather.gif\"> ....";

		try {
			// define you base URL to resolve relative resource locations
			URL url = new URL("http://www.apache.org");

			// create the email message
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName(serviceMailHostName);
			email.setSmtpPort(serviceMailPortNumber);
			email.setAuthenticator(new DefaultAuthenticator(serviceMailMaster, serviceMailPwd));
			email.setSSLOnConnect(ssl);
			email.addTo(userMail);
			email.setFrom(serviceMailMaster);
			email.setSubject("Test email with inline image");

			// set the html message
			email.setHtmlMsg(htmlEmailTemplate);

			// set the alternative message
			email.setTextMsg("Your email client does not support HTML messages");

			// send the email
			email.send();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public  void Test() {
		PtMail.sendSimpMail(null, "test msg");
//		PtMail.sendAttachmentMail(null);
	}

	/**
	 * 调用图灵机器人平台接口
	 */
	public static void main(String[] args) throws IOException {

//		tuling();
		
		PtMail.sendSimpMail(null, "test msg");

	}

	/**
	 * <p>说明:  图灵接口测试</p>
	 * @Title: tuling
	 * @return void
	 * @throws UnsupportedEncodingException
	 * @throws MalformedURLException
	 * @throws IOException 
	 */ 
	public static void tuling() throws UnsupportedEncodingException, MalformedURLException, IOException {
		String APIKEY = "aa2082f755b94358513da99e47f00e7e";
		String INFO = URLEncoder.encode("北京今日天气", "utf-8");
		String getURL = "http://www.tuling123.com/openapi/api?key=" + APIKEY + "&info=" + INFO;
		System.out.println(getURL);
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
		System.out.println(sb);
	}

}
