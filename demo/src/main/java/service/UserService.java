package service;

import dao.UserDao;
import domain.User;

import java.sql.SQLException;

public class UserService {

    public User getUserByUsername(String username) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.getUserByUsername(username);
    }
}
