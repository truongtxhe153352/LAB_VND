package edu.java.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SimpleServletListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        System.out.println("\n\n ServletContextListener destroyed\n\n");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionSQL.getSingeltonPatern();
        ServletContextListener.super.contextDestroyed(sce);
        System.out.println("\n\nServletContextListener started\n\n");
    }
}
