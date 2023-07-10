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
    // 创建Excel表格
    createExcel(getStudent());
     // 读取Excel表格
    readExcel();
  }
 
  private static Student[] getStudent() {
    Student[] stus=new Student[4];
    stus[0] = new Student("张晓龙", 8, "二年级",90);
    stus[1] = new Student("王大鹏", 9, "三年级",92);
    stus[2] = new Student("吴小花", 10, "四年级",94);
    stus[3] = new Student("朱小波", 10, "四年级",98);    
    return stus;
  }
 
  private static void createExcel(Student[] list) {
    // 创建一个Excel文件
    HSSFWorkbook workbook = new HSSFWorkbook();
    // 创建一个工作表
    HSSFSheet sheet = workbook.createSheet("学生表一");
    // 添加表头行
    HSSFRow hssfRow = sheet.createRow(0);
    // 添加表头内容
    HSSFCell headCell = hssfRow.createCell(0);
    headCell.setCellValue("姓名");

    headCell = hssfRow.createCell(1);
    headCell.setCellValue("年龄");

    headCell = hssfRow.createCell(2);
    headCell.setCellValue("年级");
  
    headCell = hssfRow.createCell(3);
    headCell.setCellValue("分数");
    // 添加数据内容
    for (int i = 0; i < list.length; i++) {
      hssfRow = sheet.createRow((int) i + 1);
      Student student = list[i];
      // 创建单元格，并设置值
      HSSFCell cell = hssfRow.createCell(0);
      cell.setCellValue(student.getName());
      cell = hssfRow.createCell(1);
      cell.setCellValue(student.getAge());
      cell = hssfRow.createCell(2);
      cell.setCellValue(student.getGrade());
      cell = hssfRow.createCell(3);
      cell.setCellValue(student.getScore());
    }
 
    // 保存Excel文件
    try {
      OutputStream outputStream = new FileOutputStream("D:/temp/students.xls");
      workbook.write(outputStream);
      outputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
 
  /**
   * 读取Excel
   * 
   * @return 数据集合
   */
  private static void readExcel() {
    HSSFWorkbook workbook = null;
    try {
      // 读取Excel文件
      InputStream inputStream = new FileInputStream("D:/temp/students.xls");
      workbook = new HSSFWorkbook(inputStream);
      inputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
 
    // 循环工作表
    for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
      HSSFSheet hssfSheet = workbook.getSheetAt(numSheet);
      if (hssfSheet == null) {
        continue;
      }
      // 循环行
      for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
        HSSFRow hssfRow = hssfSheet.getRow(rowNum);
        if (hssfRow == null) {
          continue;
        }
 
        // 将单元格中的内容存入集合
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