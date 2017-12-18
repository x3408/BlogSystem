package Servlet;

import Bean.Blog;
import Bean.BlogPage;
import Bean.User;
import Service.BlogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/addEssayServlet")
public class addEssayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession(false).getAttribute("existUser");
        if (user == null) {
            request.setAttribute("msg","您的身份已经过期，请重新登陆");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return ;
        }

        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String id = UUID.randomUUID().toString();

        Blog blog = new Blog();
        blog.setId(id);
        blog.setContent(content);
        blog.setTitle(title);

        BlogService bs = new BlogService();
        //这里的flag强制为true
        boolean flag = bs.addEssay(blog,user);
        BlogPage blogPage = (BlogPage) request.getSession(false).getAttribute("blogPage");
        request.getRequestDispatcher("/showBlogServlet?page="+(blogPage.getTotalPage()+1)).forward(request, response);
    }
}
