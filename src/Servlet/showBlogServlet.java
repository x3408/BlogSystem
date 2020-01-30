package Servlet;

import Bean.BlogPage;
import Bean.User;
import Service.BlogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/showBlogServlet")
public class showBlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        User user = (User) request.getSession().getAttribute("existUser");
        if (user == null) {
            request.setAttribute("msg","身份信息已过期，请重新登陆！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }


        BlogService bs = new BlogService();
//        BlogPage blogPage = bs.showPage(page);
        BlogPage blogPage = bs.showPage(page, user);

        System.out.println(blogPage.getList().get(0).getTitle());

        request.getSession().setAttribute("blogPage", blogPage);
        request.getSession().setAttribute("blog", blogPage.getList());
        request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
