package chap09.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 
public class ExcelOperate {
 
  public static void main(String[] args) {
    // ����Excel���
    createExcel(getStudent());
     // ��ȡExcel���
    readExcel();
  }
 
  private static Student[] getStudent() {
    Student[] stus=new Student[4];
    stus[0] = new Student("������", 8, "���꼶",90);
    stus[1] = new Student("������", 9, "���꼶",92);
    stus[2] = new Student("��С��", 10, "���꼶",94);
    stus[3] = new Student("��С��", 10, "���꼶",98);    
    return stus;
  }
 
  private static void createExcel(Student[] list) {
    // ����һ��Excel�ļ�
    HSSFWorkbook workbook = new HSSFWorkbook();
    // ����һ��������
    HSSFSheet sheet = workbook.createSheet("ѧ����һ");
    // ��ӱ�ͷ��
    HSSFRow hssfRow = sheet.createRow(0);
    // ��ӱ�ͷ����
    HSSFCell headCell = hssfRow.createCell(0);
    headCell.setCellValue("����");

    headCell = hssfRow.createCell(1);
    headCell.setCellValue("����");

    headCell = hssfRow.createCell(2);
    headCell.setCellValue("�꼶");
  
    headCell = hssfRow.createCell(3);
    headCell.setCellValue("����");
    // �����������
    for (int i = 0; i < list.length; i++) {
      hssfRow = sheet.createRow((int) i + 1);
      Student student = list[i];
      // ������Ԫ�񣬲�����ֵ
      HSSFCell cell = hssfRow.createCell(0);
      cell.setCellValue(student.getName());
      cell = hssfRow.createCell(1);
      cell.setCellValue(student.getAge());
      cell = hssfRow.createCell(2);
      cell.setCellValue(student.getGrade());
      cell = hssfRow.createCell(3);
      cell.setCellValue(student.getScore());
    }
 
    // ����Excel�ļ�
    try {
      OutputStream outputStream = new FileOutputStream("D:/temp/students.xls");
      workbook.write(outputStream);
      outputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
 
  /**
   * ��ȡExcel
   * 
   * @return ���ݼ���
   */
  private static void readExcel() {
    HSSFWorkbook workbook = null;
    try {
      // ��ȡExcel�ļ�
      InputStream inputStream = new FileInputStream("D:/temp/students.xls");
      workbook = new HSSFWorkbook(inputStream);
      inputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
 
    // ѭ��������
    for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
      HSSFSheet hssfSheet = workbook.getSheetAt(numSheet);
      if (hssfSheet == null) {
        continue;
      }
      // ѭ����
      for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
        HSSFRow hssfRow = hssfSheet.getRow(rowNum);
        if (hssfRow == null) {
          continue;
        }
 
        // ����Ԫ���е����ݴ��뼯��
        Student student = new Student();
 
        HSSFCell cell = hssfRow.getCell(0);
        if (cell == null) {
          continue;
        }
        student.setName(cell.getStringCellValue());
 
        cell = hssfRow.getCell(1);
        if (cell == null) {
          continue;
        }
        student.setAge((int) cell.getNumericCellValue());
 
        cell = hssfRow.getCell(2);
        if (cell == null) {
          continue;
        }
        student.setGrade(cell.getStringCellValue());
 
        cell = hssfRow.getCell(3);
        if (cell == null) {
          continue;
        }
        student.setScore((int)cell.getNumericCellValue());
        
        System.out.println(student);
      }
    }
  }

}