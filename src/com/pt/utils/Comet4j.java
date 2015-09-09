package com.pt.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;

import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;

import com.pt.dao.impl.UserIconDaoImpl;

public  class Comet4j implements ServletContextListener{

	private static final String CHANNEL = "songqiupdate";
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		CometContext cc = CometContext.getInstance();
		
		cc.registChannel(CHANNEL);
		
		Thread helloAppModule = new Thread(new HelloAppModule(), "Sender App Module");
		
		helloAppModule.setDaemon(true);
		
		helloAppModule.start();
	}
	
	class HelloAppModule implements Runnable{
		
		public void run() {
			
			CometEngine engine = CometContext.getInstance().getEngine();
			int msg =0;
			int i=0;
			while(true){
				String id =Ids.getCidmap().get("aa");
//				System.out.println("id:"+id);
//				System.out.println("map:" + Ids.getCidmap().size());
				msg = getMsgNum();
				try {
					Thread.sleep(1000); // 设置为参数，可由系统管理员给予修改
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//				System.out.println("com4j:" + msg);
//				engine.sendToAll(CHANNEL, msg);
				i++;
//				engine.sendTo(CHANNEL, engine.getConnection(id),i);
				engine.sendTo(CHANNEL, engine.getConnection(id), msg);
			}
		}
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}
	/**
	 * 
	 * author：songqi
	 * @return
	 */
	private int getMsgNum() {
		/*UserIconDaoImpl idi = new UserIconDaoImpl();
		int num = idi.getMsgCount();*/
		return 0;
//		UserIconDaoImpl idi = new UserIconDaoImpl();
//		int num = idi.getMsgCount();
//		return num;
	}
	
}





