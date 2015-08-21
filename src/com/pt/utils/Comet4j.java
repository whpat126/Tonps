package com.pt.utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;

import com.pt.dao.impl.IconDaoImpl;

public class Comet4j implements ServletContextListener{

	private static final String CHANNEL = "songqiupdate";
	
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
			while(true){
				msg = getMsgNum();
				try {
					Thread.sleep(10000); // 设置为参数，可由系统管理员给予修改
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//				System.out.println("com4j:" + msg);
				engine.sendToAll(CHANNEL, msg);
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
		IconDaoImpl idi = new IconDaoImpl();
		int num = idi.getMsgCount();
		return num;
	}
}





