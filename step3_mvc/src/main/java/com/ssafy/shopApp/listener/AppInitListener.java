package com.ssafy.shopApp.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class AppInitListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  {
		ServletContext application = sce.getServletContext();
		application.setAttribute("root", application.getContextPath());
    	System.out.println("App init listener...");
    }

    public void contextDestroyed(ServletContextEvent sce)  {
    	
    }
	
}
