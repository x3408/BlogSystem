package Action;

import Bean.Blog;
import Bean.BlogPage;
import Bean.User;
import Service.BlogService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BlogAction extends ActionSupport implements ModelDriven<Blog> {
    private Blog blog = new Blog();
    private BlogService blogService;

    private Integer totalPage;

    public String addEssay() {
        /*User user = (User) request.getSession(false).getAttribute("existUser");
        if (user == null) {
            request.setAttribute("msg","您的身份已经过期，请重新登陆");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return ;
        }

        String title = request.getParameter("title");
        String content = request.getParameter("content");
//        String id = UUID.randomUUID().toString();

        Blog blog = new Blog();
//        blog.setId(id);
        blog.setContent(content);
        blog.setTitle(title);

        BlogService bs = new BlogService();
        //这里的flag强制为true
        boolean flag = bs.addEssay(blog,user);
        BlogPage blogPage = (BlogPage) request.getSession(false).getAttribute("blogPage");
        request.getRequestDispatcher("/showBlogServlet?page="+(blogPage.getTotalPage()+1)).forward(request, response);*/

        User user = (User) ActionContext.getContext().getSession().get("existUser");
        if (user == null) {
            ActionContext.getContext().put("msg", "您的身份已经过期，请重新登陆");
            return "Login";
        }
        boolean flag = blogService.addEssay(blog, user);
        BlogPage blogPage = (BlogPage) ActionContext.getContext().getSession().get("blogPage");
        this.totalPage = blogPage.getTotalPage()+1;
        return "toHomeLast";
    }





    @Override
    public Blog getModel() {
        return blog;
    }

    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
