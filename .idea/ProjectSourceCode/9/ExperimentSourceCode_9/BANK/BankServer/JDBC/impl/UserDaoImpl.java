package ExperimentSourceCode_9.BANK.BankServer.JDBC.impl;

import ExperimentSourceCode_9.BANK.BankServer.JDBC.dao.UserDao;
import ExperimentSourceCode_9.BANK.BankServer.JDBC.utils.ConnectionManager;
import ExperimentSourceCode_9.BANK.bean.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户访问接口实现类
 */
public class UserDaoImpl implements UserDao {
    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int findById(int id) {
        return 0;
    }

    @Override
    public List<User> findAll() {
        // 定义用户列表
        List<User> users = new ArrayList<>();

        // 1. 获取数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 创建SQL字符串
        String strSQL = "select * from t_user";
        try {
            // 3. 创建语句对象
            Statement stmt = conn.createStatement();
            // 4. 执行SQL查询，返回结果集
            ResultSet rs = stmt.executeQuery(strSQL);
            // 5. 遍历结果集，构建用户列表
            while (rs.next()) {
                // 创建用户对象
                User user = new User();
                // 用当前记录字段值设置用户对象属性
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                // 将用户对象添加到用户列表
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        // 返回用户列表
        return users;
    }

    @Override
    public User login(String username, String password) {
        // 定义用户对象
        User user = null;

        // 1. 获取数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 创建SQL字符串
        String strSQL = "select * from t_user where username = ? and password = ?";
        try {
            // 3. 创建预备语句对象（准备提供参数）
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 4. 用参数值设置占位符的值
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            // 5. 执行SQL查询，返回结果集
            System.out.println(strSQL);
            ResultSet rs = pstmt.executeQuery();
            // 6. 遍历结果集，用记录值填充用户对象
            while (rs.next()) {
                // 创建用户对象
                user = new User();
                // 用当前记录字段值设置用户对象属性
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        // 返回用户对象
        return user;
    }
}
