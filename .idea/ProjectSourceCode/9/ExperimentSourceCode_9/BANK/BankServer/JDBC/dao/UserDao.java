package ExperimentSourceCode_9.BANK.BankServer.JDBC.dao;

import ExperimentSourceCode_9.BANK.bean.User;

import java.util.List;

/**
 * 功能：用户数据访问接口
 */
public interface UserDao {
    int insert(User user);
    int delete(int id);
    int update(User user);
    int findById(int id);
    List<User> findAll();
    User login(String username, String password);
}
