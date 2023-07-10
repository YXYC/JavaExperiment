package chap09.poi;
//https://www.e-learn.cn/tag/poi
//https://www.sxt.cn/apache_poi/apache_poi.html
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

public class Excel {    //ֱ�Ӷ�ȡExcel��ȫ������
    public static String readDoc1(InputStream is)throws IOException{
        HSSFWorkbook wb=new HSSFWorkbook(new POIFSFileSystem(is));
        ExcelExtractor extractor=new ExcelExtractor(wb);
        extractor.setFormulasNotResults(false);
        extractor.setIncludeSheetNames(true);
        return extractor.getText();
    }

    //��ȡʱϸ����Sheet����������Ԫ��
    public static double getAvg(InputStream is)throws IOException{
        HSSFWorkbook wb=new HSSFWorkbook(new POIFSFileSystem(is));
        //��ȡ��һ��sheet
        HSSFSheet sheet=wb.getSheetAt(0);
        double molecule=0.0;
        double denominator=0.0;
        //���б���sheet
        Iterator<Row> riter=sheet.rowIterator();
        while(riter.hasNext()){
            HSSFRow row=(HSSFRow)riter.next();
            HSSFCell cell1=row.getCell(4);
            HSSFCell cell2=row.getCell(4);
            if(cell1.getCellType()!=HSSFCell.CELL_TYPE_NUMERIC){
                System.err.println("�������ʹ���");
                System.exit(-2);
            }
            if(cell2.getCellType()!=HSSFCell.CELL_TYPE_NUMERIC){
                System.err.println("�������ʹ���");
                System.exit(-2);
            }
            denominator+=Double.parseDouble(cell2.toString().trim());
            molecule+=Double.parseDouble(cell2.toString().trim())*Float.parseFloat(cell1.toString().trim());
        }
        return molecule/denominator;
    }

    public static void main(String[] args){
        File file = new File("d:/temp/3.xls");
        try{
            FileInputStream fin=new FileInputStream(file);
            String cont=readDoc1(fin);
            System.out.println(cont);
            fin.close();
            fin=new FileInputStream(file);
            System.out.println("��Ȩƽ����"+getAvg(fin));
            fin.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}