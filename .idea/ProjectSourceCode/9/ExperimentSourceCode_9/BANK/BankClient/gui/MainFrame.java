package ExperimentSourceCode_9.BANK.BankClient.gui;

import ExperimentSourceCode_9.BANK.BankClient.Client.C_Connect;

import ExperimentSourceCode_9.BANK.BankClient.gui.util.ErrorPrompt;
import ExperimentSourceCode_9.BANK.bean.Request;
import ExperimentSourceCode_9.BANK.bean.bank;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//维护表格

/**
 * 主页面部分
 */
public class MainFrame extends JFrame {

    private DefaultTableModel tableModel;   //表格模型对象
    private JTable table;
    private JTextField fTextField;//账号
    private JTextField sTextField;//密码
    private JTextField aTextField;//姓名
    private JTextField bTextField;//学号
    private JTextField cTextField;//电话号码
    private JTextField dTextField;//性别
    private JTextField eTextField;//生日
    private C_Connect connect =new C_Connect();

    public MainFrame() throws Exception {
        super();
        setTitle("用户列表");
        //将所有信息存到banks中
        Request request =new Request();
        request.setA("find_bank");
        request=connect.in_out(request);
        setBounds(100, 100, 1200, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] columnNames = {"账号","密码","姓名", "学号", "电话号码", "性别", "生日", "余额"};   //列名
        int col;
        col =request.getBanks().size();
        String[][] tableVales = new String[col][8];//数据
        for(int i=0;i<col;i++)
        {
            tableVales [i][0] =request.getBanks().get(i).getUsername();
            tableVales [i][1] =request.getBanks().get(i).getPassword();
            tableVales [i][2] = request.getBanks().get(i).getName();
            tableVales [i][3] = request.getBanks().get(i).getId();
            tableVales [i][4] = request.getBanks().get(i).getNumber();
            tableVales [i][5] = request.getBanks().get(i).getSex();
            DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            tableVales [i][6] = dateformat.format(request.getBanks().get(i).getBrithday());
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            tableVales [i][7] = nf.format(request.getBanks().get(i).getBalance());
        }
        tableModel = new DefaultTableModel(tableVales, columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);   //支持滚动
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        table.addMouseListener(new MouseAdapter() {    //鼠标事件
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow(); //获得选中行索引
                Object of = tableModel.getValueAt(selectedRow, 0);
                Object os = tableModel.getValueAt(selectedRow, 1);
                Object oa = tableModel.getValueAt(selectedRow, 2);
                Object ob = tableModel.getValueAt(selectedRow, 3);
                Object oc = tableModel.getValueAt(selectedRow, 4);
                Object od = tableModel.getValueAt(selectedRow, 5);
                Object oe = tableModel.getValueAt(selectedRow, 6);
                fTextField.setText(of.toString());  //给文本框赋值
                sTextField.setText(os.toString());
                aTextField.setText(oa.toString());  //给文本框赋值
                bTextField.setText(ob.toString());
                cTextField.setText(oc.toString());  //给文本框赋值
                dTextField.setText(od.toString());
                eTextField.setText(oe.toString());  //给文本框赋值

            }
        });
        scrollPane.setViewportView(table);
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        panel.add(new JLabel("账号: "));
        fTextField = new JTextField("", 10);
        panel.add(fTextField);

        panel.add(new JLabel("密码: "));
        sTextField = new JTextField("", 10);
        panel.add(sTextField);

        panel.add(new JLabel("姓名: "));
        aTextField = new JTextField("", 10);
        panel.add(aTextField);

        panel.add(new JLabel("学号: "));
        bTextField = new JTextField("", 10);
        panel.add(bTextField);

        panel.add(new JLabel("电话号码: "));
        cTextField = new JTextField("", 10);
        panel.add(cTextField);

        panel.add(new JLabel("性别: "));
        dTextField = new JTextField(" ", 10);
        panel.add(dTextField);

        panel.add(new JLabel("生日: "));
        eTextField = new JTextField("", 10);
        panel.add(eTextField);

        final JButton addButton = new JButton("开户");   //开户按钮
        Request finalRequest = request;
        Request finalRequest1 = request;
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] rowValues = {fTextField.getText(),sTextField.getText(),aTextField.getText(), bTextField.getText(),cTextField.getText(),dTextField.getText(),eTextField.getText(),"2000"};
                //数据库中也应添加相应的值
                bank a = new bank();
                a.setUsername(fTextField.getText());
                a.setPassword(sTextField.getText());
                a.setName(aTextField.getText());
                a.setId(bTextField.getText());
                a.setNumber(cTextField.getText());
                a.setSex(dTextField.getText());
                Date date1;
                try {
                    date1=new SimpleDateFormat("yyyy-dd-MM").parse(eTextField.getText());
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                a.setBrithday(date1);
                a.setBalance(2000);
                Request res =new Request();
                res.setA("login_user");
                res.setBank(a);
                try {
                    res=connect.in_out(res);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                if(res.getBank()!=null){
                    new ErrorPrompt("用户名已存在！");
                }
                else{
                    tableModel.addRow(rowValues);  //添加一行
                    int rowCount = table.getRowCount() + 1;   //行数加上1
                    res.setA("add_bank");
                    res.setBank(a);
                    try {
                        connect.in_out(res);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                //bankdao.insert(a);
            }
        });
        panel.add(addButton);

        final JButton updateButton = new JButton("修改");   //修改按钮
        bank bank =new bank();
        updateButton.addActionListener(new ActionListener() {//添加事件
            public void actionPerformed(ActionEvent e) {
                bank.setId(bTextField.getText());//id不允许进修改
                int selectedRow = table.getSelectedRow();//获得选中行的索引
                if (selectedRow != -1)   //是否存在选中行
                {
                    //修改指定的值：
                    tableModel.setValueAt(aTextField.getText(), selectedRow, 0);
                    tableModel.setValueAt(cTextField.getText(), selectedRow, 2);
                    tableModel.setValueAt(dTextField.getText(), selectedRow, 3);
                    tableModel.setValueAt(eTextField.getText(), selectedRow, 4);

                    DateFormat dateformat2 = new SimpleDateFormat("yyyy-MM-dd");
                    bank.setName(aTextField.getText());
                    bank.setNumber(cTextField.getText());
                    bank.setSex(dTextField.getText());
                    try {
                        bank.setBrithday(dateformat2.parse(eTextField.getText()));
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }

                    Request res =new Request();
                    res.setA("modify_bank");
                    res.setBank(bank);
                    try {
                        connect.in_out(res);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    //数据库中也要进行修改
                    //bankdao.update_name(aTextField.getText(),bTextField.getText());
                    //bankdao.update_number(cTextField.getText(),bTextField.getText());
                   // bankdao.update_sex(dTextField.getText(),bTextField.getText());

                    //bankdao.update_birthday(dateformat2.parse(eTextField.getText()),bTextField.getText());
                    //table.setValueAt(arg0, arg1, arg2)
                }
            }
        });
        panel.add(updateButton);

        final JButton delButton = new JButton("删除");
        delButton.addActionListener(new ActionListener() {//添加事件
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();//获得选中行的索引
                if (selectedRow != -1)  //存在选中行
                {
                    bank.setId(bTextField.getText());//通过唯一的id对用户进行删除
                    System.out.println(bTextField.getText());
                    tableModel.removeRow(selectedRow);  //删除行
                }
               //在数据库中也要进行删除
                Request request3 = new Request();
                request3.setBank(bank);
                request3.setA("delect");
                try {
                    connect.in_out(request3);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(delButton);

        final JButton outButton = new JButton("退出");   //开户按钮
        outButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(outButton);
    }




    /**
     * 测试菜单界面功能
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        MainFrame MainFrame = new MainFrame();
        MainFrame.setVisible(true);
    }

}
