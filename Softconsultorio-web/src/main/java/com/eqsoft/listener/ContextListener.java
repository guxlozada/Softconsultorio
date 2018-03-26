package com.eqsoft.listener;

import javax.servlet.ServletContextEvent;

public class ContextListener {// extends ServletContextListener {
    public void contextDestroyed(ServletContextEvent event) {
        // NOOP
    }

    public void contextInitialized(ServletContextEvent event) {
        System.setProperty("org.apache.el.parser.COERCE_TO_ZERO", "false");
    }
}
