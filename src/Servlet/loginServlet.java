package Servlet;

import Bean.User;
import Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("inputPassword");

        UserService us = new UserService();
        User user = us.findUserbyName(username, password);

        if(user == null) {
            request.setAttribute("msg","用户名或密码不正确");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            String login = request.getParameter("login");
            if("true".equals(login)) {
                Cookie cookie = new Cookie("autoLogin",
                        URLEncoder.encode(username,"utf-8")+"#login#"+
                URLEncoder.encode( password,"utf-8"));
                cookie.setMaxAge(60);
                cookie.setPath("/");
                response.addCookie(cookie);
            }

            request.getSession().invalidate();

            Map<User, HttpSession> map = (Map<User, HttpSession>) request.getServletContext().getAttribute("userMap");
            if (map.containsKey(user)) {
                HttpSession session = map.get(user);
                session.invalidate();
            }

            map.put(user, request.getSession());


            request.getSession(false).setAttribute("existUser", user);
            request.getRequestDispatcher("/showBlogServlet?page=1").forward(request, response);
        }
    }
}
