package chap09.jxl;

import java.io.File;
import java.util.*;
import jxl.*;
import jxl.write.*;

public class TestExportExcel {
	public static void excelExport(Customer[] list, String path) {
		WritableWorkbook book = null;
		try {
			// 创建一个Excel文件对象
			book = Workbook.createWorkbook(new File(path));
			// 创建Excel第一个选项卡对象
			WritableSheet sheet = book.createSheet("第一页", 0);
			// 设置表头，第一行内容
			// Label参数说明：第一个是列，第二个是行，第三个是要写入的数据值，索引值都是从0开始
			Label label1 = new Label(0, 0, "姓名");// 对应为第1列第1行的数据
			Label label2 = new Label(1, 0, "年龄");// 对应为第2列第1行的数据
			Label label3 = new Label(2, 0, "手机号码");// 对应为第3列第1行的数据
			Label label4 = new Label(3, 0, "住址");// 对应为第4列第1行的数据
			// 添加单元格到选项卡中
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			// 遍历集合并添加数据到行，每行对应一个对象
			for (int i = 0; i < list.length; i++) {
				Customer customer = list[i];
				// 表头占据第一行，所以下面行数是索引值+1
				// 跟上面添加表头一样添加单元格数据，这里为了方便直接使用链式编程
				sheet.addCell(new Label(0, i + 1, customer.getName()));
				sheet.addCell(new Label(1, i + 1, customer.getAge().toString()));
				sheet.addCell(new Label(2, i + 1, customer.getTelephone()));
				sheet.addCell(new Label(3, i + 1, customer.getAddress()));
			}
			// 写入数据到目标文件
			book.write();
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
	}

	public static void main(String[] args) {
		Customer[] list = new Customer[10];
		// 创建数据
		for (int i = 0; i < 10; i++) {
			Customer customer = new Customer();
			customer.setName("云中飞马" + i);
			customer.setAge(i);
			customer.setTelephone("1301234976" + i);
			customer.setAddress("甘肃省兰州市七里河区");
			list[i] = customer;
		}
		String path = "D:\\temp\\customer.xls";
		System.out.println("开始导出...");
		long s1 = new Date().getTime();
		// 开始导出
		excelExport(list, path);
		long s2 = new Date().getTime();
		long time = s2 - s1;
		System.out.println("导出完成！消耗时间：" + time + "毫秒");
	}

}
