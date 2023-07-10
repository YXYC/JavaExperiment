package chap09.jxl;
//https://sourceforge.net/projects/jexcelapi/files/jexcelapi/
//http://jexcelapi.sourceforge.net/resources/javadocs/2_6_10/docs/index.html
//https://blog.csdn.net/yanghongchang_/article/details/8064616
//https://www.e-learn.cn/tag/jxl
import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

public class TestExcel1 {
	public static void main(String[] args) {
		Workbook book = null;
		try {
			// 获取Excel对象
			book = book.getWorkbook(new File("d://temp//readme.xls"));
			// 获取Excel第一个选项卡对象
			Sheet sheet = book.getSheet(0);
			int cols=sheet.getColumns();  //取到表格的列数
			for (int i = 0; i < cols; i++) {
				System.out.print(sheet.getCell(i, 0).getContents()+"\t");
			}
			System.out.println();
            int rows=sheet.getRows();//取到表格的行数
            String[] contents=new String[cols];
            double sum=0,avg=0;
			for (int i = 1; i < sheet.getRows(); i++) {
				for(int j=0;j<cols;j++) {
	                contents[j]=sheet.getCell(j,i).getContents();
	    			System.out.print(contents[j]+"\t");
				}
				sum+=Double.parseDouble(contents[3]);
                System.out.println();
			}
			avg=sum/(rows-1);
			System.out.println("共有"+(rows-1)+"个同学参加比赛,平均分为："+avg);
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
