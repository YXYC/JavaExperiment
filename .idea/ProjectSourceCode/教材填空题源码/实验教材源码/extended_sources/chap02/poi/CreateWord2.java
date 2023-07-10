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
		// 文档生成方法
		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph p1 = doc.createParagraph(); // 创建段落
        p1.setAlignment(ParagraphAlignment.CENTER);
		XWPFRun r1 = p1.createRun(); // 创建段落文本
		r1.setText("目录"); // 设置文本
		r1.setFontSize(18);
		r1.addCarriageReturn();
		r1.setUnderline(UnderlinePatterns.THICK);
		FileOutputStream out = null; // 创建输出流
		try {
			// 向word文档中添加内容
			XWPFParagraph p2 = doc.createParagraph(); // 创建段落
            p2.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun r2 = p2.createRun(); // 创建段落文本
			// 设置文本
			r2.setText("表名");
			r2.setColor("ff0000");
			r2.setFontSize(20);
			r2.setBold(true);
            
			XWPFTable table1 = doc.createTable(8, 10);
			table1.setWidth(100);
			XWPFTableRow row1 = table1.getRow(0);
			// 设置单元格内容
			row1.getCell(0).setText("字段名  ");
			row1.getCell(1).setText("字段说明  ");
			row1.getCell(2).setText("数据类型  ");
			row1.getCell(3).setText("长   度  ");
			row1.getCell(4).setText("索   引  ");
			row1.getCell(5).setText("是否为空  ");
			row1.getCell(6).setText("主   键  ");
			row1.getCell(7).setText("外   键  ");
			row1.getCell(8).setText(" 缺省值  ");
			row1.getCell(9).setText("备    注 ");
			
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