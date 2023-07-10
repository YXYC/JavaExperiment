package chap17;
//ImportExcelToMysql.java
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
public class ImportExcelToMysql {
	public static Connection getConnection() throws SQLException, java.lang.ClassNotFoundException {
		String url = "jdbc:mysql://www.lzucclab.net:3306/lzu2021students?characterEncoding=utf8"; // 连接MySQL中的数据库
		Class.forName("com.mysql.jdbc.Driver");
		String userName = "lzuuser"; // 登录用户名
		String password = "Lzu1909"; // 密码
		Connection con = DriverManager.getConnection(url, userName, password);
		return con;
	}
	public static void main(String[] args) {
		Workbook book = null;
		try {
			Connection con = getConnection();
			Statement sql = con.createStatement();
			/* 创建表 */
			sql.execute("drop table if exists student"); // 如果原来存在student表，则删除
			sql.execute("create table student(姓名  varchar(12),学院  varchar(30),科目   varchar(30),成绩  int,订单编号   varchar(15)) ENGINE=InnoDB   DEFAULT   CHARSET=utf8;");
			PreparedStatement pstmt = con.prepareStatement("insert into student values(?,?,?,?,?)");
			/* 读取本地Excel文件内容 */
			book = Workbook.getWorkbook(new File("d://temp//readme.xls"));
			// 获取Excel第一个选项卡对象
			Sheet sheet = book.getSheet(0);
			int cols = sheet.getColumns(); // 取到表格的列数
			int rows = sheet.getRows();// 取到表格的行数
			String[] contents = new String[cols];

			for (int i = 1; i < sheet.getRows(); i++) {
				for (int j = 0; j < cols; j++) {
					contents[j] =sheet.getCell(j, i).getContents();
					if (j == 3)
						pstmt.setInt(4, Integer.parseInt(contents[j]));
					else
						pstmt.setString(j + 1, contents[j]);
					System.out.print(contents[j]+ "\t");
				}
				pstmt.executeUpdate();
				System.out.println();
			}
			System.out.println("共有" + (rows - 1) + "记录导入。");
			sql.close();
			pstmt.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException ex) {
			System.err.println("SQLException:" + ex.getMessage());
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}