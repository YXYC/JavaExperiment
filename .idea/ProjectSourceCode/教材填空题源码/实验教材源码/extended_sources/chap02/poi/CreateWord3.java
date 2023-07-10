package chap09.poi;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
public class CreateWord3 {
	public static void main(String[] args)throws Exception {
	      XWPFDocument document= new XWPFDocument();
	      try(FileOutputStream out = new FileOutputStream(new File("d:/temp/table.docx"))){
	    	  // Creating Table
	    	  XWPFTable tab = document.createTable();
	    	  tab.setWidth(100);
	    	  XWPFTableRow row = tab.getRow(0); // First row
	    	  // Columns
	    	  row.getCell(0).setText("–£‘∞ø®∫≈");
	          row.addNewTableCell().setText("–’√˚");
	          row.addNewTableCell().setText("” œ‰");
	          row = tab.createRow(); // Second Row
	          row.getCell(0).setText("819940600531");
	          row.getCell(1).setText("¬Ìø°");
	          row.getCell(2).setText("majun@lzu.edu.cn");
	          row = tab.createRow(); // Third Row
	          row.getCell(0).setText("819906600533");
	          row.getCell(1).setText("‘∆÷–∑…¬Ì");
	          row.getCell(2).setText("flyhorse@126.com");	  
	          document.write(out);
	      }catch(Exception e) {
	    	  System.out.println(e);
	      }
	   }
}