package ExperimentSourceCode_9.BANK.BankServer.JDBC.test;

import ExperimentSourceCode_9.BANK.BankServer.JDBC.impl.UserDaoImpl;
import ExperimentSourceCode_9.BANK.BankServer.JDBC.dao.UserDao;
import ExperimentSourceCode_9.BANK.bean.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestUserDaoImpl {
    private UserDao userDao;
    @Before
    public void init() {
        userDao = new UserDaoImpl();
    }

    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        // 判断用户表是否有记录
        if (users.size() > 0) {
            for(User user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("提示：用户表里没有记录~");
        }
    }

    @Test
    public void testLogin() {
        String username = "admin";
        String password = "12345";

        User user = userDao.login(username, password);
        if (user != null) {
            System.out.println("恭喜，[" + username + "]登录成功~");
        } else {
            System.out.println("遗憾，[" + username + "]登录失败~");
        }
    }
}
