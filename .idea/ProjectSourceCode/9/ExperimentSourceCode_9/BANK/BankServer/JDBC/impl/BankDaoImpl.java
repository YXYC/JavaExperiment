package ExperimentSourceCode_9.BANK.BankServer.JDBC.impl;

import ExperimentSourceCode_9.BANK.BankServer.JDBC.dao.BankDao;
import ExperimentSourceCode_9.BANK.BankServer.JDBC.utils.ConnectionManager;
import ExperimentSourceCode_9.BANK.bean.bank;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 银行管理接口实例化
 */
public class BankDaoImpl implements BankDao {

    /**
     * 导出数据库中所有成员列表
     *
     * @return
     */

    @Override
    public void delect(String id) {
        //1.获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 创建SQL字符串
        String strSQL = "DELETE FROM bank WHERE user_id =?;";
        try {
            // 3. 创建预备语句对象（准备提供参数）
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            //4.获取执行sql的对象statement
            Statement stt = conn.createStatement();
            // 5. 用参数值设置占位符的值
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public List<bank> findAll() {
        // 定义用户列表
        List<bank> users = new ArrayList<>();

        // 1. 获取数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 创建SQL字符串
        String strSQL = "select * from bank";
        try {
            // 3. 创建语句对象
            Statement stmt = conn.createStatement();
            // 4. 执行SQL查询，返回结果集
            ResultSet rs = stmt.executeQuery(strSQL);
            // 5. 遍历结果集，构建用户列表
            while (rs.next()) {
                // 创建用户对象
                bank user = new bank();
                // 用当前记录字段值设置用户对象属性
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getString("user_id"));
                user.setName(rs.getString("user_name"));
                user.setBalance(rs.getDouble("balance"));
                user.setBrithday(rs.getDate("birthday"));
                user.setSex(rs.getString("sex"));
                user.setNumber(rs.getString("phone_number"));
                // 将用户对象添加到用户列表
                //System.out.println(""+user.getPassword());
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

    /**
     * 向数据库中添加新成员
     *
     * @param bank
     */
    @Override
    public void insert(bank bank) {
        //1.获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 创建SQL字符串
        String strSQL = "INSERT INTO bank VALUES(?,?,?,?,?,?,?,2000);";
        try {
            // 3. 创建预备语句对象（准备提供参数）
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            //4.获取执行sql的对象statement
            Statement stt = conn.createStatement();
            // 5. 用参数值设置占位符的值
            pstmt.setString(1, bank.getUsername());
            pstmt.setString(2, bank.getPassword());
            pstmt.setString(3, bank.getName());
            pstmt.setString(4, bank.getId());
            pstmt.setString(5, bank.getNumber());
            pstmt.setString(6, bank.getSex());
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            pstmt.setString(7, dateformat.format(bank.getBrithday()));
            //NumberFormat nf = NumberFormat.getInstance();
            //pstmt.setString(6, nf.format(bank.getBalance()));
            System.out.println(pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    public void update_name(String name, String id) {
        //1.获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 创建SQL字符串
        String strSQL = "UPDATE bank SET user_name=? WHERE user_id=?;";
        try {
            // 3. 创建预备语句对象（准备提供参数）
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            //4.获取执行sql的对象statement
            Statement stt = conn.createStatement();
            // 5. 用参数值设置占位符的值
            pstmt.setString(1, name);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }//修改姓名

    public void update_sex(String sex, String id) {
        //1.获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 创建SQL字符串
        String strSQL = "UPDATE bank SET sex=? WHERE user_id=?;";
        try {
            // 3. 创建预备语句对象（准备提供参数）
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            //4.获取执行sql的对象statement
            Statement stt = conn.createStatement();
            // 5. 用参数值设置占位符的值
            pstmt.setString(1, sex);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }//修改学号

    public void update_birthday(Date birth, String id) {
        //1.获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 创建SQL字符串
        String strSQL = "UPDATE bank SET birthday=? WHERE user_id=?;";
        try {
            // 3. 创建预备语句对象（准备提供参数）
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            //4.获取执行sql的对象statement
            Statement stt = conn.createStatement();
            // 5. 用参数值设置占位符的值
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            pstmt.setString(1, dateformat.format(birth));
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }//修改

    public void update_number(String number, String id) {
        //1.获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 创建SQL字符串
        String strSQL = "UPDATE bank SET phone_number=? WHERE user_id=?;";
        try {
            // 3. 创建预备语句对象（准备提供参数）
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            //4.获取执行sql的对象statement
            Statement stt = conn.createStatement();
            // 5. 用参数值设置占位符的值
            pstmt.setString(1, number);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public bank store(bank bank, double b) throws SQLException {
        //1.获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 创建SQL字符串
        String strSQL = "UPDATE bank SET balance=balance+? WHERE user_id=?;";
        try {
            // 3. 创建预备语句对象（准备提供参数）
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            //4.获取执行sql的对象statement
            Statement stt = conn.createStatement();
            // 5. 用参数值设置占位符的值
            pstmt.setDouble(1, b);
            pstmt.setString(2, bank.getId());
            System.out.println("执行操作："+pstmt);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return bank;
    }

    @Override
    public bank take(bank bank, double b) {
        //1.获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 创建SQL字符串
        String strSQL = "UPDATE bank SET balance=balance-? WHERE user_id=?;";
        String strSQL2 = "select * from bank WHERE user_id=?;";
        try {
            // 3. 创建预备语句对象（准备提供参数）
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            //4.获取执行sql的对象statement
            Statement stt = conn.createStatement();
            // 5. 用参数值设置占位符的值
            //nf.setGroupingUsed(false);
            pstmt.setDouble(1, b);
            pstmt.setString(2, bank.getId());
            System.out.println("执行操作："+pstmt);
            int count=pstmt.executeUpdate();
            System.out.println("受影响的行数："+count);
            pstmt = conn.prepareStatement(strSQL2);
            pstmt.setString(1, bank.getId());

            ResultSet rs = pstmt.executeQuery();
            // 5. 遍历结果集，构建用户列表
            while (rs.next()) {
                // 创建用户对象
                bank user = new bank();
                // 用当前记录字段值设置用户对象属性
                user.setId(rs.getString("user_id"));
                user.setName(rs.getString("user_name"));
                user.setBalance(rs.getDouble("balance"));
                user.setBrithday(rs.getDate("birthday"));
                user.setSex(rs.getString("sex"));
                user.setNumber(rs.getString("phone_number"));
                // 将用户对象添加到用户列表
                //users.add(user);
                bank=user;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        //System.out.println(bank.toString());
        return bank;
    }

    @Override
    public bank login(String username, String password) {
        // 定义用户对象
        bank user = null;

        // 1. 获取数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 创建SQL字符串
        String strSQL = "select * from bank where username = ? and password = ?";
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
                user = new bank();
                // 用当前记录字段值设置用户对象属性
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setId(rs.getString("user_id"));
                user.setName(rs.getString("user_name"));
                user.setBalance(rs.getDouble("balance"));
                user.setBrithday(rs.getDate("birthday"));
                user.setSex(rs.getString("sex"));
                user.setNumber(rs.getString("phone_number"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        // 返回用户对象
        return user;
    }

    /**
     * 批量开户的实现
     * @param banks
     * @return
     */
    @Override
    public double list_bank(List<bank> banks) {
        //1.获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        double count = 0;
        // 2. 创建SQL字符串
        String strSQL = "INSERT INTO bank VALUES(?,?,?,?,?,?,?,2000);";
        try {
            // 3. 创建预备语句对象（准备提供参数）
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            //4.获取执行sql的对象statement
            Statement stt = conn.createStatement();
            // 5. 用参数值设置占位符的值
            for(int i=0;i<banks.size();i++)
            {
                count++;
                bank bank = banks.get(i);
                pstmt.setString(1, bank.getUsername());
                pstmt.setString(2, bank.getPassword());
                pstmt.setString(3, bank.getName());
                pstmt.setString(4, bank.getId());
                pstmt.setString(5, bank.getNumber());
                pstmt.setString(6, bank.getSex());
                DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                pstmt.setString(7, dateformat.format(bank.getBrithday()));
                //NumberFormat nf = NumberFormat.getInstance();
                //pstmt.setString(6, nf.format(bank.getBalance()));
                System.out.println(pstmt);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }
        return count;
    }
}
