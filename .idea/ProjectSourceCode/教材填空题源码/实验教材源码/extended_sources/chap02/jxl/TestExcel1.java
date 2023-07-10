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
			// ��ȡExcel����
			book = book.getWorkbook(new File("d://temp//readme.xls"));
			// ��ȡExcel��һ��ѡ�����
			Sheet sheet = book.getSheet(0);
			int cols=sheet.getColumns();  //ȡ����������
			for (int i = 0; i < cols; i++) {
				System.out.print(sheet.getCell(i, 0).getContents()+"\t");
			}
			System.out.println();
            int rows=sheet.getRows();//ȡ����������
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
			System.out.println("����"+(rows-1)+"��ͬѧ�μӱ���,ƽ����Ϊ��"+avg);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// �ر�
				book.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
