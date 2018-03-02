package Service;

import Bean.User;
import DAO.UserDAO;
import DAO.DAOImp.UserDAOImp;
import Utils.HibernateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.UUID;

public class UserService {
//    private static UserDAO dao = new UserDAOImp();

    private UserDAOImp dao;
    public boolean addUser(User user) {
        /*String id = UUID.randomUUID().toString();
        user.setId(id);

        dao.addUser(user);*/

        //开启事务
        Session currentSession = HibernateUtil.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();


        dao.addUser(user);

        transaction.commit();

        return true;
    }

    public User findUserbyName(String username, String password) {
        /*User user = dao.findUserbyName(username, password);
        return user;*/

        Session currentSession = HibernateUtil.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();


        User user = dao.findUserbyName(username, password);

        transaction.commit();
        return user;
    }

    public boolean checkUser(String username) {
        /*boolean flag = dao.checkUser(username);
        return flag;*/
        Session currentSession = HibernateUtil.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();


        boolean flag = dao.checkUser(username);

        transaction.commit();
        return flag;
    }

    public void setDao(UserDAOImp dao) {
        this.dao = dao;
    }
}
