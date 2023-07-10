package chap09.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class CreateWord2 {
	public static void main(String[] args) {
		// �ĵ����ɷ���
		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph p1 = doc.createParagraph(); // ��������
        p1.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r1 = p1.createRun(); // ���������ı�
		r1.setText("Ŀ¼"); // �����ı�
		r1.setFontSize(18);
		r1.addCarriageReturn();
		r1.setUnderline(UnderlinePatterns.THICK);
		FileOutputStream out = null; // ���������
		try {
			// ��word�ĵ����������
			XWPFParagraph p2 = doc.createParagraph(); // ��������
            p2.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun r2 = p2.createRun(); // ���������ı�
			// �����ı�
			r2.setText("����");
			r2.setColor("ff0000");
			r2.setFontSize(20);
			r2.setBold(true);
            
			XWPFTable table1 = doc.createTable(8, 10);
			table1.setWidth(100);
			XWPFTableRow row1 = table1.getRow(0);
			// ���õ�Ԫ������
			row1.getCell(0).setText("�ֶ���  ");
			row1.getCell(1).setText("�ֶ�˵��  ");
			row1.getCell(2).setText("��������  ");
			row1.getCell(3).setText("��   ��  ");
			row1.getCell(4).setText("��   ��  ");
			row1.getCell(5).setText("�Ƿ�Ϊ��  ");
			row1.getCell(6).setText("��   ��  ");
			row1.getCell(7).setText("��   ��  ");
			row1.getCell(8).setText(" ȱʡֵ  ");
			row1.getCell(9).setText("��    ע ");
			
			String filePath = "D:\\temp\\simple.docx";
			out = new FileOutputStream(new File(filePath));
			doc.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
 
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
 
					e.printStackTrace();
				}
			}
 
		}
 
	}
}