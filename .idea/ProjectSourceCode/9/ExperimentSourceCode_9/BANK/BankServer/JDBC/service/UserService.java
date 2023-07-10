package ExperimentSourceCode_9.BANK.BankServer.JDBC.service;

import ExperimentSourceCode_9.BANK.BankServer.JDBC.impl.UserDaoImpl;
import ExperimentSourceCode_9.BANK.BankServer.JDBC.dao.UserDao;
import ExperimentSourceCode_9.BANK.bean.User;

import java.util.List;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDaoImpl();
    }

    public int addUser(User user) {
        return userDao.insert(user);
    }

    public int deleteUser(int id) {
        return userDao.delete(id);
    }

    public int updateUser(User user) {
        return userDao.update(user);
    }

    public int findUserById(int id) {
        return userDao.findById(id);
    }

    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    public User login(String username, String password) {
        return userDao.login(username, password);
    }

}