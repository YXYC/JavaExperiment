package chap17;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToMySQL {
	public static Connection getConnection() throws SQLException, java.lang.ClassNotFoundException {
		String url = "jdbc:mysql://121.36.87.115:3306/test?characterEncoding=utf8"; // ����MySQL�е�test���ݿ�,�������Ϊutf8
		Class.forName("com.mysql.jdbc.Driver");
		String userName = "root"; // ��¼�û���
		String password = "Root1234"; // ����
		Connection con = DriverManager.getConnection(url, userName, password);
		return con;
	}

	public static void main(String[] args) {
		try {
			Connection con = getConnection();
			Statement sql = con.createStatement();
			String querystatement = "select * from stu"; // ��ѯ��stu�еļ�¼
			ResultSet result = sql.executeQuery(querystatement);
			System.out.println("\tStu���������£�");
			System.out.println("--------------------------------");
			System.out.println("ѧ   ��\t" + "��   ��\t" +"����\t"+"��    ��");
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