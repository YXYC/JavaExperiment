package ExperimentSourceCode_9.fill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToMySQL {
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
			String querystatement = "select * from student"; // 查询表stu中的记录
			ResultSet result = sql.executeQuery(querystatement);
			System.out.println("\tStu表数据如下：");
			System.out.println("--------------------------------");
			System.out.println("学   号\t" + "姓   名\t" +"年龄\t"+"成    绩");
			System.out.println("--------------------------------");
			String stu_id;
			String stu_name;
			int stu_age;
			float stu_score;
			while (result.next()) {
				stu_id = result.getString("Stu_id");
				stu_name = result.getString("Stu_name");
				stu_age = result.getInt("Stu_age");
				stu_score = result.getFloat("Stu_score");
				System.out.println(stu_id + "\t" + stu_name + "\t" +stu_age+"\t"+stu_score);
			}
			sql.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException ex) {
			System.err.println("SQLException:" + ex.getMessage());
		}
	}
}