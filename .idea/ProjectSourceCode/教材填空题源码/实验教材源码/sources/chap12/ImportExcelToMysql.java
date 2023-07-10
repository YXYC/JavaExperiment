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
		String url = "jdbc:mysql://www.lzucclab.net:3306/lzu2021students?characterEncoding=utf8"; // ����MySQL�е����ݿ�
		Class.forName("com.mysql.jdbc.Driver");
		String userName = "lzuuser"; // ��¼�û���
		String password = "Lzu1909"; // ����
		Connection con = DriverManager.getConnection(url, userName, password);
		return con;
	}
	public static void main(String[] args) {
		Workbook book = null;
		try {
			Connection con = getConnection();
			Statement sql = con.createStatement();
			/* ������ */
			sql.execute("drop table if exists student"); // ���ԭ������student����ɾ��
			sql.execute("create table student(����  varchar(12),ѧԺ  varchar(30),��Ŀ   varchar(30),�ɼ�  int,�������   varchar(15)) ENGINE=InnoDB   DEFAULT   CHARSET=utf8;");
			PreparedStatement pstmt = con.prepareStatement("insert into student values(?,?,?,?,?)");
			/* ��ȡ����Excel�ļ����� */
			book = Workbook.getWorkbook(new File("d://temp//readme.xls"));
			// ��ȡExcel��һ��ѡ�����
			Sheet sheet = book.getSheet(0);
			int cols = sheet.getColumns(); // ȡ����������
			int rows = sheet.getRows();// ȡ����������
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
			System.out.println("����" + (rows - 1) + "��¼���롣");
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