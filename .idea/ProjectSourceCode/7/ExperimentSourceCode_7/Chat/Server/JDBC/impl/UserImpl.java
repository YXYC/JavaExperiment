package ExperimentSourceCode_7.Chat.Server.JDBC.impl;

import ExperimentSourceCode_7.Chat.Bean.user;
import ExperimentSourceCode_7.Chat.Server.JDBC.ConnectionManager;
import ExperimentSourceCode_7.Chat.Server.JDBC.Dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImpl implements UserDao {
    @Override
    public synchronized user Login(String Username, String password) {
        // 定义用户对象
        user user = null;

        // 1. 获取数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 创建SQL字符串
        String strSQL = "select * from user where username = ? and password = ?";
        try {
            // 3. 创建预备语句对象（准备提供参数）
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            // 4. 用参数值设置占位符的值
            pstmt.setString(1, Username);
            pstmt.setString(2, password);
            // 5. 执行SQL查询，返回结果集
            System.out.println(strSQL);
            ResultSet rs = pstmt.executeQuery();
            // 6. 遍历结果集，用记录值填充用户对象
            while (rs.next()) {
                // 创建用户对象
                user = new user(null,null,null);
                // 用当前记录字段值设置用户对象属性
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));
                user.setIsOline(1);
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
