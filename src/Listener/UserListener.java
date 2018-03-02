package Listener;

import Bean.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class UserListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        Map<User, HttpSession> map = new HashMap<>();
        //服务action
        Map<User, Map<String, Object>> map = new HashMap<>();
        servletContextEvent.getServletContext().setAttribute("userMap", map);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
