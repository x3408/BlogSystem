package Action;

import Bean.Blog;
import Bean.BlogPage;
import Bean.User;
import Service.BlogService;
import Service.UserService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import java.net.URLEncoder;
import java.util.Map;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    private UserService userService;
    private User user = new User();
    private String login;
    private String page;

    public String login() throws Exception {
        /*
            request.getSession().invalidate();

            Map<User, HttpSession> map = (Map<User, HttpSession>) request.getServletContext().getAttribute("userMap");
            if (map.containsKey(user)) {
                HttpSession session = map.get(user);
                session.invalidate();
            }

            map.put(user, request.getSession());


            request.getSession(false).setAttribute("existUser", user);
            request.getRequestDispatcher("/showBlogServlet?page=1").forward(request, response);*/

        User user = userService.findUserbyName(this.user.getUsername(), this.user.getPassword());

        if(user == null) {
            ActionContext.getContext().put("msg", "用户名或密码不正确");
            return "Login";
        } else {
            //自动登陆
            if("true".equals(login)) {
                Cookie cookie = new Cookie("autoLogin",
                        URLEncoder.encode(user.getUsername(),"utf-8")+"#login#"+
                                URLEncoder.encode( user.getPassword(),"utf-8"));
                cookie.setMaxAge(60);
                cookie.setPath("/");
                ServletActionContext.getResponse().addCookie(cookie);
            }
        }


        //同浏览器同一账户注销
//        ServletActionContext.getRequest().getSession().invalidate();
        //struts中Session清除
        ActionContext.getContext().getSession().clear();
        Map<User, Map<String, Object>> map = (Map<User, Map<String, Object>>) ActionContext.getContext().getApplication().get("userMap");
        if (map.containsKey(user)) {
            Map<String, Object> session = map.get(user);
            //不同浏览器同一账户注销
            session.clear();
        }
        System.out.println(ServletActionContext.getRequest().getSession());
//        map.put(user, ServletActionContext.getRequest().getSession());
        map.put(user, ActionContext.getContext().getSession());

        //改方法获取到的session是一个map 不同于原生session
        ActionContext.getContext().getSession().put("existUser", user);
        //使用原生session
//        ServletActionContext.getRequest().getSession().setAttribute("existUser", user);

        return "Home";
    }

    public String showBlog() {
        /*String page = request.getParameter("page");
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
        request.getRequestDispatcher("/home.jsp").forward(request, response);*/
        User user = (User)ActionContext.getContext().getSession().get("existUser");
        if (user == null) {
            ActionContext.getContext().put("msg","身份信息已过期，请重新登陆！");
            return "Login";
        }

        BlogService bs = new BlogService();
        BlogPage blogPage = bs.showPage(page, user);

        ActionContext.getContext().getSession().put("blogPage", blogPage);
        ActionContext.getContext().getSession().put("blog", blogPage.getList());

        return "toHome";
    }



    @Override
    public User getModel() {
        return user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
