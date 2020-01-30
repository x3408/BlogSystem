package DAO;

import Bean.User;

public interface UserDAO {
    void addUser(User user);

    User findUserbyName(String username, String password);

    boolean checkUser(String username);
}
