package com.pt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;

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
			while(true){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				engine.sendToAll(CHANNEL, new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss").format(new Date()));
			}
		}
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

}





