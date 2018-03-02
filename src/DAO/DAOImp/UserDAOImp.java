package DAO.DAOImp;

import Bean.User;
import DAO.UserDAO;
import Utils.HibernateUtil;
import Utils.SqlHelper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.hibernate.Session;

import java.sql.SQLException;

public class UserDAOImp implements UserDAO{
    @Override
    public void addUser(User user) {
       /* QueryRunner queryRunner = new QueryRunner(SqlHelper.getInstance().getDataSource());

        try {
            queryRunner.update("insert into user values(?,?,?,?,?,?)",
                    user.getId(), user.getUsername(), user.getPassword(), user.getHobby(), user.getSpeciality(),user.getPath());
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

       Session session = HibernateUtil.getCurrentSession();

       session.save(user);
    }

    @Override
    public User findUserbyName(String username, String password) {
        /*QueryRunner queryRunner = new QueryRunner(SqlHelper.getInstance().getDataSource());
        User user = null;
        try {
            user = queryRunner.query("select * from user where username=? and password=?", new BeanHandler<User>(User.class),username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;*/

       Session session = HibernateUtil.getCurrentSession();

        String sql = "from User where username = ? and password = ?";
        User user = (User) session.createQuery(sql)
                .setParameter(0, username)
                .setParameter(1, password)
                .uniqueResult();

        return user;
    }

    @Override
    public boolean checkUser(String username) {
        /*QueryRunner queryRunner = new QueryRunner(SqlHelper.getInstance().getDataSource());

        try {
            User user = queryRunner.query("select * from user where username=?", new BeanHandler<>(User.class),username);
            if (user!=null)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;*/
        Session session = HibernateUtil.getCurrentSession();

        String sql = "from User where username = ?";

        User user = (User) session.createQuery(sql)
                .setParameter(0, username)
                .uniqueResult();

        if (user!=null)
            return true;
        return false;
    }
}
