package chap09.jxl;

import java.io.File;  
import java.io.IOException;  
import jxl.Workbook;  
import jxl.write.Label;  
import jxl.write.WritableSheet;  
import jxl.write.WritableWorkbook;  
import jxl.write.WriteException;  
import jxl.write.biff.RowsExceededException;  
  
public class WriteExcel {  
    public static void main(String[] args) throws IOException,  
            RowsExceededException, WriteException {  
        // 1、创建工作簿(WritableWorkbook)对象，打开excel文件，若文件不存在，则创建文件  
        WritableWorkbook writeBook = Workbook.createWorkbook(new File(  
                "D://temp//write.xls"));  
  
        // 2、新建工作表(sheet)对象，并声明其属于第几页  
        WritableSheet firstSheet = writeBook.createSheet("第一个工作簿", 1);// 第一个参数为工作簿的名称，第二个参数为页数  
        WritableSheet secondSheet = writeBook.createSheet("第二个工作簿", 0);  
  
        // 3、创建单元格(Label)对象，  
        Label label1 = new Label(1, 2, "test1");// 第一个参数指定单元格的列数、第二个参数指定单元格的行数，第三个指定写的字符串内容  
        firstSheet.addCell(label1);  
        Label label2 = new Label(1, 2, "test2");  
        secondSheet.addCell(label2);  
  
        // 4、打开流，开始写文件  
        writeBook.write();  
  
        // 5、关闭流  
        writeBook.close();  
    }  
  
}  