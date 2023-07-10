package chap17;

import java.io.*;
import java.sql.*;

public class DispStruct {
	static String colLabel[];
	static int colCount;

	public static void showStruct(ResultSet rs) throws IOException, SQLException {
		ResultSetMetaData md = rs.getMetaData();
		colCount = md.getColumnCount();
		colLabel = new String[colCount + 1];
		System.out.println("============================================");
		for (int i = 1; i <= colCount; i++) {
			colLabel[i] = md.getColumnLabel(i);
			System.out.print("" + colLabel[i] + "\t");
		}
		System.out.println("\n============================================");
	}

	public static void showData(ResultSet rs) throws IOException, SQLException {
		if (rs != null) {
			while (rs.next()) { // ������¼��
				System.out.print("" + rs.getString(colLabel[1]));
				System.out.print("\t" + rs.getString(colLabel[2]));
				System.out.print("\t" + rs.getString(colLabel[3]));
				System.out.print("\t" + rs.getInt(colLabel[4]));
				System.out.println("\t" + rs.getString(colLabel[5]));
			}
		}
		System.out.println("============================================");
	}

	public static void main(String args[]) {
		try {
			String url = "jdbc:mysql://124.70.66.251:3306/lzu2021students?characterEncoding=utf8";
			String userName = "lzuuser"; // ��¼�û���
			String password = "lzu1909"; // ����
			Class.forName("com.mysql.jdbc.Driver"); // ����JdbcOdbcDriver����
			Connection con = DriverManager.getConnection(url, userName, password);
			Statement stmt = con.createStatement();// ����������
			boolean status = stmt.execute("select * from student");
			ResultSet rs = stmt.getResultSet();
			showStruct(rs);
			showData(rs);
			stmt.close();
			con.close();// �ر�����
		} catch (SQLException e) {
			System.out.println(e.getSQLState());
		} catch (IOException e2) {
			System.out.println(e2.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
