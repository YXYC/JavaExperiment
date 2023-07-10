package chap09.itext;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class TestItextPdf {
	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		// TODO Auto-generated method stub
		Document document = new Document(PageSize.A4);
		PdfWriter write = PdfWriter.getInstance(document, new FileOutputStream("D:\\temp\\兰州大学.pdf"));
		document.open();
		try {
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font fontChinese = new Font(bfChinese, 13, Font.NORMAL);
			document.addHeader("itext", "flyhorse");
			
			document.add(new Paragraph("姓名：马俊", fontChinese));
			document.add(new Paragraph("单位：兰州大学", fontChinese));
			document.add(new Paragraph("专业：计算机应用", fontChinese));
			Paragraph t1=new Paragraph("乘法口诀表",fontChinese);
			t1.setAlignment(Paragraph.ALIGN_CENTER);
			document.add(t1);
			document.add(new Paragraph("  ",fontChinese));
			PdfPTable table = new PdfPTable(10);
			table.addCell("*");
			for (int i = 1; i < 10; i++) {
				table.addCell(""+i);
			}
			for(int i=1;i<=9;i++) {
				table.addCell(""+i);
				for(int j=1;j<=9;j++) {
					if(j<=i) table.addCell(""+i*j);
					else table.addCell(" ");
				}
			}
			document.add(table);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.close();

	}

}
