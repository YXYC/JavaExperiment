package ExperimentSourceCode_9.BANK.BankClient.File;

import ExperimentSourceCode_9.BANK.BankClient.Client.C_Connect;
import ExperimentSourceCode_9.BANK.bean.Request;
import ExperimentSourceCode_9.BANK.bean.bank;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

public class ExportExcel {
    public  ExportExcel(List<bank> list, String path) {
        WritableWorkbook book = null;
        try {
            // 创建一个Excel文件对象
            book = Workbook.createWorkbook(new File(path));
            // 创建Excel第一个选项卡对象
            WritableSheet sheet = book.createSheet("第一页", 0);
            // 设置表头，第一行内容
            // Label参数说明：第一个是列，第二个是行，第三个是要写入的数据值，索引值都是从0开始
            Label label1 = new Label(0, 0, "账号");// 对应为第1列第1行的数据
            Label label2 = new Label(1, 0, "密码");// 对应为第2列第1行的数据
            Label label3 = new Label(2, 0, "姓名");// 对应第3列第1行的数据
            Label label4 = new Label(3, 0, "银行卡号");// 对应为第4列第1行的数据
            Label label5 = new Label(4, 0, "电话号码");// 对应为第1列第1行的数据
            Label label6 = new Label(5, 0, "性别");// 对应为第2列第1行的数据
            Label label7 = new Label(6, 0, "出生日期");// 对应第3列第1行的数据
            Label label8 = new Label(7, 0, "余额");// 对应为第4列第1行的数据
            // 添加单元格到选项卡中
            sheet.addCell(label1);
            sheet.addCell(label2);
            sheet.addCell(label3);
            sheet.addCell(label4);
            sheet.addCell(label5);
            sheet.addCell(label6);
            sheet.addCell(label7);
            sheet.addCell(label8);
            // 遍历集合并添加数据到行，每行对应一个对象
            for (int i = 0; i < list.size(); i++) {
                bank customer = list.get(i);
                // 表头占据第一行，所以下面行数是索引值+1
                // 跟上面添加表头一样添加单元格数据，这里为了方便直接使用链式编程
                sheet.addCell(new Label(0, i + 1, customer.getUsername()));
                sheet.addCell(new Label(1, i + 1, customer.getPassword()));
                sheet.addCell(new Label(2, i + 1, customer.getName()));
                sheet.addCell(new Label(3, i + 1, customer.getId()));
                sheet.addCell(new Label(4, i + 1, customer.getNumber()));
                String sex = new String();
                if(customer.getBrithday().toString().equals("M")) sex="男";
                else sex ="女";
                sheet.addCell(new Label(5, i + 1, sex));
                sheet.addCell(new Label(6, i + 1, customer.getBrithday().toString()));
                NumberFormat nf = NumberFormat.getInstance();
                nf.setGroupingUsed(false);
                sheet.addCell(new Label(7, i + 1, nf.format(customer.getBalance())));
            }
            // 写入数据到目标文件
            book.write();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭
                book.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        C_Connect connect = new C_Connect();
        Request request =new Request();
        request.setA("find_bank");
        request= connect.in_out(request);
        List<bank> list = request.getBanks();
        String path = "D:\\temp\\customer.xls";
        System.out.println("开始导出...");
        long s1 = new Date().getTime();
        // 开始导出
        new ExportExcel(list, path);
        long s2 = new Date().getTime();
        long time = s2 - s1;
        System.out.println("导出完成！消耗时间：" + time + "毫秒");
    }
}
