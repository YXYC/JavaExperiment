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
        // 1������������(WritableWorkbook)���󣬴�excel�ļ������ļ������ڣ��򴴽��ļ�  
        WritableWorkbook writeBook = Workbook.createWorkbook(new File(  
                "D://temp//write.xls"));  
  
        // 2���½�������(sheet)���󣬲����������ڵڼ�ҳ  
        WritableSheet firstSheet = writeBook.createSheet("��һ��������", 1);// ��һ������Ϊ�����������ƣ��ڶ�������Ϊҳ��  
        WritableSheet secondSheet = writeBook.createSheet("�ڶ���������", 0);  
  
        // 3��������Ԫ��(Label)����  
        Label label1 = new Label(1, 2, "test1");// ��һ������ָ����Ԫ����������ڶ�������ָ����Ԫ���������������ָ��д���ַ�������  
        firstSheet.addCell(label1);  
        Label label2 = new Label(1, 2, "test2");  
        secondSheet.addCell(label2);  
  
        // 4����������ʼд�ļ�  
        writeBook.write();  
  
        // 5���ر���  
        writeBook.close();  
    }  
  
}  