package Junit;

import Action.UserAction;
import Bean.BlogPage;
import DAO.DAOImp.UserDAOImp;
import Service.BlogService;
import Service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringJUnit {
    @Resource(name = "userService")
    private UserService us;

    @Resource(name = "userDAOImp")
    private UserDAOImp userDAOImp;

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Resource(name = "blogService")
    private BlogService blogService;

    @Resource(name = "userAction")
    private UserAction userAction;

    @Test
    public void userServiceIsExistOrNot() {
        System.out.println(us);
    }

    @Test
    public void userDaoIsExistOrNot() {
        System.out.println(userDAOImp);
    }

    @Test
    public void daoConnectionTest() {
        Session session = sessionFactory.openSession();

        List list = session.createQuery("from User where username = ?")
                .setParameter(0, "xc")
                .list();

        System.out.println(list);

        session.close();
    }

    @Test
    public void blogServiceTest() {
        BlogPage blogPage = blogService.showPage("1");
        System.out.println(blogPage);
    }

    @Test
    public void UserActionTest() {
        userAction.showBlog();
    }
}
