package chap09.jxl;

import java.io.File;
import java.util.*;
import jxl.*;
import jxl.write.*;

public class TestExportExcel {
	public static void excelExport(Customer[] list, String path) {
		WritableWorkbook book = null;
		try {
			// ����һ��Excel�ļ�����
			book = Workbook.createWorkbook(new File(path));
			// ����Excel��һ��ѡ�����
			WritableSheet sheet = book.createSheet("��һҳ", 0);
			// ���ñ�ͷ����һ������
			// Label����˵������һ�����У��ڶ������У���������Ҫд�������ֵ������ֵ���Ǵ�0��ʼ
			Label label1 = new Label(0, 0, "����");// ��ӦΪ��1�е�1�е�����
			Label label2 = new Label(1, 0, "����");// ��ӦΪ��2�е�1�е�����
			Label label3 = new Label(2, 0, "�ֻ�����");// ��ӦΪ��3�е�1�е�����
			Label label4 = new Label(3, 0, "סַ");// ��ӦΪ��4�е�1�е�����
			// ��ӵ�Ԫ��ѡ���
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			// �������ϲ�������ݵ��У�ÿ�ж�Ӧһ������
			for (int i = 0; i < list.length; i++) {
				Customer customer = list[i];
				// ��ͷռ�ݵ�һ�У�������������������ֵ+1
				// ��������ӱ�ͷһ����ӵ�Ԫ�����ݣ�����Ϊ�˷���ֱ��ʹ����ʽ���
				sheet.addCell(new Label(0, i + 1, customer.getName()));
				sheet.addCell(new Label(1, i + 1, customer.getAge().toString()));
				sheet.addCell(new Label(2, i + 1, customer.getTelephone()));
				sheet.addCell(new Label(3, i + 1, customer.getAddress()));
			}
			// д�����ݵ�Ŀ���ļ�
			book.write();
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

	public static void main(String[] args) {
		Customer[] list = new Customer[10];
		// ��������
		for (int i = 0; i < 10; i++) {
			Customer customer = new Customer();
			customer.setName("���з���" + i);
			customer.setAge(i);
			customer.setTelephone("1301234976" + i);
			customer.setAddress("����ʡ�������������");
			list[i] = customer;
		}
		String path = "D:\\temp\\customer.xls";
		System.out.println("��ʼ����...");
		long s1 = new Date().getTime();
		// ��ʼ����
		excelExport(list, path);
		long s2 = new Date().getTime();
		long time = s2 - s1;
		System.out.println("������ɣ�����ʱ�䣺" + time + "����");
	}

}
