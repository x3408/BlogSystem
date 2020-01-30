package Filter;

import Bean.User;
import Service.UserService;
import Utils.CookieUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;

public class AutoLoginFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        User user = (User) request.getSession().getAttribute("existUser");
        if(user != null) {
            filterChain.doFilter(request, servletResponse);
        }else {
            Cookie [] cookies = request.getCookies();
            Cookie cookie = CookieUtils.findCookie(cookies, "autoLogin");

            if (cookie == null)
                filterChain.doFilter(request, servletResponse);
            else {
                String username = (cookie.getValue().split("#login#"))[0];
                username = URLDecoder.decode(username, "utf-8");
                String password = (cookie.getValue().split("#login#"))[1];

                UserService us = new UserService();
                user = us.findUserbyName(username, password);
                if (user != null) {
                    request.getSession(false).setAttribute("existUser", user);
                    request.getRequestDispatcher("/showBlogServlet?page=1").forward(request, servletResponse);
                } else {
                    request.setAttribute("msg","身份信息已过期,请重新输入!");
                    request.getRequestDispatcher("/login.jsp").forward(request, servletResponse);
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
