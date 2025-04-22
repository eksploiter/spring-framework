package com.ssafy.shopApp.listener;

import com.ssafy.shopApp.util.DBUtil;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class AppInitListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  {
    	DBUtil.getUtil();
    	System.out.println("App init listener...");
    }

    public void contextDestroyed(ServletContextEvent sce)  {
    	
    }
	
}
