package chap09.jxl;

import java.io.File;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class TestExcel2 {
	public static void main(String[] args) {
		WritableWorkbook book = null;
		try {
			// 创建一个Excel文件对象
			book = Workbook.createWorkbook(new File("d://temp//chengfa.xls"));
			// 创建Excel第一个选项卡对象
			WritableSheet sheet = book.createSheet("乘法口诀表", 0);
			sheet.addCell(new Label(0, 0, "*"));
			for (int i = 1; i <= 9; i++) {
				sheet.addCell(new Label(i, 0, "" + i));
			}

			for (int i = 1; i <= 9; i++) {
				sheet.addCell(new Label(0,i,""+i));
				for (int j = 1; j <= i; j++) {
					sheet.addCell(new Label(j, i, ""+j+"*"+i+"="+i*j));
				}
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
}
