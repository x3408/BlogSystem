package Junit;

import DAO.DAOImp.UserDAOImp;
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
}
