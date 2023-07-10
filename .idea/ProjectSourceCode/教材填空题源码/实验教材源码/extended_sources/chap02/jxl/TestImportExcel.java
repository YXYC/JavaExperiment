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
			// ��ȡExcel����
			book = book.getWorkbook(new File(path));
			// ��ȡExcel��һ��ѡ�����
			Sheet sheet = book.getSheet(0);
			int rows=sheet.getRows()-1;
			list=new Customer[rows];
			for (int i = 0; i < rows; i++) {
				Customer customer = new Customer();
				// ��ȡ��һ�еڶ��е�Ԫ�����
				Cell cell = sheet.getCell(0, i + 1);
				customer.setName(cell.getContents());
				// ��ȡ�ڶ�����������
				customer.setAge(Integer.parseInt(sheet.getCell(1, i + 1).getContents()));
				customer.setTelephone(sheet.getCell(2, i + 1).getContents());
				customer.setAddress(sheet.getCell(3, i + 1).getContents());
				list[i]=customer;
			}
			// ���ص�������ݼ���
			return list;
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
		System.out.println("�˿�������"+rs+"\t�˿�ƽ������Ϊ��"+agesum/rs);
	}

}
