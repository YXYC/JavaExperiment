package ExperimentSourceCode_9.BANK.BankServer.JDBC.service;

import ExperimentSourceCode_9.BANK.BankServer.JDBC.utils.ConnectionManager;
import ExperimentSourceCode_9.BANK.bean.report;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 对年终表单内容进行整理
 */
public class reportService {

    /**
     * 特定年份的新开户人数和销户人数导入
     */
    public void change_user(int year,int n){
            //1.获得数据库连接
            Connection conn = ConnectionManager.getConnection();
            // 2. 创建SQL字符串
            String strSQL = new String();
            if(n>0) strSQL= "UPDATE report SET count=count+?,newuser=newuser+? WHERE year=?;";//开户
            else strSQL="UPDATE report SET count=count+?,dieuser=dieuser-? WHERE year=?;";//销户
            try {
                // 3. 创建预备语句对象（准备提供参数）
                PreparedStatement pstmt = conn.prepareStatement(strSQL);
                //4.获取执行sql的对象statement
                Statement stt = conn.createStatement();
                // 5. 用参数值设置占位符的值
                pstmt.setInt(1, n);
                pstmt.setInt(2, n);
                pstmt.setInt(3, year);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            } finally {
                ConnectionManager.closeConnection(conn);
            }
    }

    /**
     * 特定年份的总余额更新
     */
    public void change_balance(int year,double n){
        //1.获得数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 创建SQL字符串
        String strSQL = "UPDATE report SET all_balance=all_balance+? WHERE year=?;";
        try {
            // 3. 创建预备语句对象（准备提供参数）
            PreparedStatement pstmt = conn.prepareStatement(strSQL);
            //4.获取执行sql的对象statement
            Statement stt = conn.createStatement();
            // 5. 用参数值设置占位符的值
            pstmt.setDouble(1, n);
            pstmt.setInt(2, year);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection(conn);
        }
    }

    /**
     * 返回年终报表
     */

    public List<report> findAll() {
        // 定义用户列表
        List<report> users = new ArrayList<>();

        // 1. 获取数据库连接
        Connection conn = ConnectionManager.getConnection();
        // 2. 创建SQL字符串
        String strSQL = "select * from report";
        try {
            // 3. 创建语句对象
            Statement stmt = conn.createStatement();
            // 4. 执行SQL查询，返回结果集
            ResultSet rs = stmt.executeQuery(strSQL);
            // 5. 遍历结果集，构建用户列表
            while (rs.next()) {
                // 创建用户对象
                report user = new report();
                // 用当前记录字段值设置用户对象属性
                user.setYear(rs.getInt("year"));
                user.setCount(rs.getInt("count"));
                user.setAll_balance(rs.getDouble("all_balance"));
                user.setNewuser(rs.getInt("newuser"));
                user.setDieuser(rs.getInt("dieuser"));
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

}
