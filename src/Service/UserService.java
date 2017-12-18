package Service;

import Bean.User;
import DAO.DAOImp.UserDAOImp;
import DAO.UserDAO;
import org.apache.commons.beanutils.BeanUtils;

import java.util.UUID;

public class UserService {
    private static UserDAO dao = new UserDAOImp();

    public boolean addUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);

        dao.addUser(user);

        return true;
    }

    public User findUserbyName(String username, String password) {
        User user = dao.findUserbyName(username, password);
        return user;
    }

    public boolean checkUser(String username) {
        boolean flag = dao.checkUser(username);
        return flag;
    }
}
