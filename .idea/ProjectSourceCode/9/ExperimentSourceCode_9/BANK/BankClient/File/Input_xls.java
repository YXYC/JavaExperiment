package ExperimentSourceCode_9.BANK.BankClient.File;

import ExperimentSourceCode_9.BANK.BankClient.Client.C_Connect;
import ExperimentSourceCode_9.BANK.bean.Request;
import ExperimentSourceCode_9.BANK.bean.bank;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Input_xls {
    public Input_xls(String path) {
        Workbook book = null;
        try {
            /* 读取本地Excel文件内容 */
            book = Workbook.getWorkbook(new File(path));
            // 获取Excel第一个选项卡对象
            Sheet sheet = book.getSheet(0);
            int cols = sheet.getColumns(); // 取到表格的列数
            int rows = sheet.getRows();// 取到表格的行数
            String[] contents = new String[cols];
            List<bank> list = new ArrayList<bank>();
            for (int i = 1; i < sheet.getRows(); i++) {
                bank user = new bank();
                for (int j = 0; j < cols; j++) {
                    contents[j] = sheet.getCell(j, i).getContents();
                    if (j == 0) user.setUsername(contents[j]);
                    if (j == 1) user.setPassword(contents[j]);
                    if (j == 2) user.setName(contents[j]);
                    if (j == 3) user.setId(contents[j]);
                    if (j == 4) user.setNumber(contents[j]);
                    if (j == 5) user.setSex(contents[j]);
                    DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                    NumberFormat nf = NumberFormat.getInstance();
                    nf.setGroupingUsed(false);
                    if (j == 6) user.setBrithday(dateformat.parse(contents[j]));
                    if (j == 7) user.setBalance(Double.parseDouble(contents[j]));
                }
                list.add(user);
                //System.out.println(list.get(i-1).toString());
                //System.out.println();
            }
            int all = rows-1;
            System.out.println("共有" + all + "记录导入。");
            C_Connect connect = new C_Connect();
            Request request =new Request();
            request.setA("list_bank");//进行批量开户操作，返回开户成功数量
            request.setBanks(list);
            request=connect.in_out(request);
        } catch (BiffException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
