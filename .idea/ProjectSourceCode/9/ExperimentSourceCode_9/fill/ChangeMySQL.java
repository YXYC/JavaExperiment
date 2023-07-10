package ExperimentSourceCode_9.fill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangeMySQL {
	public static Connection getConnection() throws SQLException, java.lang.ClassNotFoundException {
		String url = "jdbc:mysql://123.249.95.52:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false"; // 连接MySQL中的test数据库,传输编码为utf8
		Class.forName("com.mysql.jdbc.Driver");
		String userName = "root"; // 登录用户名
		String password = "wangweiyyds"; // 密码
		Connection con = DriverManager.getConnection(url, userName, password);
		return con;
	}
	public static void main(String[] args) {
		try {
			Connection con = getConnection();
			Statement sql = con.createStatement();		
			String changestatement = "update student set Stu_score=700 where Stu_id='100002'"; // 查询表stu中的记录
			int result = sql.executeUpdate(changestatement);
			result=sql.executeUpdate("delete from student where Stu_id='100001'");
			sql.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException ex) {
			System.err.println("SQLException:" + ex.getMessage());
		}
	}
}
