package chap09.jxl;

import java.io.File;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class TestImportExcel {
	public static Customer[] excelImport(String path) {
		Customer[] list =null;
		Workbook book = null;
		try {
			// 获取Excel对象
			book = book.getWorkbook(new File(path));
			// 获取Excel第一个选项卡对象
			Sheet sheet = book.getSheet(0);
			int rows=sheet.getRows()-1;
			list=new Customer[rows];
			for (int i = 0; i < rows; i++) {
				Customer customer = new Customer();
				// 获取第一列第二行单元格对象
				Cell cell = sheet.getCell(0, i + 1);
				customer.setName(cell.getContents());
				// 获取第二行其他数据
				customer.setAge(Integer.parseInt(sheet.getCell(1, i + 1).getContents()));
				customer.setTelephone(sheet.getCell(2, i + 1).getContents());
				customer.setAddress(sheet.getCell(3, i + 1).getContents());
				list[i]=customer;
			}
			// 返回导入的数据集合
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭
				book.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		String path = "D:\\temp\\customer.xls";
		Customer[] list = excelImport(path);
		int rs=0;
		double agesum=0;
		for (Customer customer : list) {
			System.out.println(customer);
			rs++;
			agesum+=customer.getAge();
		}
		System.out.println("顾客人数："+rs+"\t顾客平均年龄为："+agesum/rs);
	}

}
