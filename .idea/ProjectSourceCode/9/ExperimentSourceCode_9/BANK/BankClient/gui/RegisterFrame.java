package ExperimentSourceCode_9.BANK.BankClient.gui;


import ExperimentSourceCode_9.BANK.BankClient.Client.C_Connect;

import ExperimentSourceCode_9.BANK.BankClient.Client.gui.LogOnJFrame;
import ExperimentSourceCode_9.BANK.BankClient.gui.util.ErrorPrompt;
import ExperimentSourceCode_9.BANK.bean.Request;
import ExperimentSourceCode_9.BANK.bean.bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterFrame extends JFrame{
    ImageIcon back;
    private C_Connect connect = new C_Connect();
    /*
     * 登录界面
     */
    public RegisterFrame() {
        /*
         * 对窗口的操作，包括创建，设置标题，设置大小以及位置
         */


        JFrame frame = new JFrame();// 创建一个窗口
        frame.setTitle("注册");// 设置窗口标题
        frame.setBounds(250, 100, 825, 800);// 设置窗口位置和大小

        /*
         * 这是对整个窗口布局的格式化，已达到可以任意放面板，标签，文本框，按钮等东西
         */
        // FlowLayout fl = new FlowLayout(FlowLayout.CENTER, 10, 10);//
        // 实例化FlowLayout流式布局类的对象
        frame.setLayout(null);// 布局为空

        /*
         * 创建面板，以达到良好的布局
         */
        JPanel panel = new JPanel();// JPanel：面板组件，非顶层容器
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();
        JPanel panel9 = new JPanel();
        JPanel panel10 = new JPanel();
        /*
         * 设置一个大标题，即图书管理系统，放到面板panel1里面
         */
        JLabel labTitle = new JLabel("飞马银行");//设置窗口标题
        Font font1 = new Font("宋体", Font.BOLD, 50);//设置字形，字体和字号
        labTitle.setFont(font1);
        panel1.add(labTitle);
        panel1.setBounds(260, 20, 300, 80);//设置窗口位置和大小
        frame.add(panel1);

        /*
         * 设置身份标签
         */
        Font font = new Font("宋体", Font.BOLD, 16);
        JLabel labCard = new JLabel("出生日期  ");// 用标签来表示文本或图片
        labCard.setFont(font);// 设置标签字体的大小
        panel.add(labCard);// 将lable标签添加到面板上

        /*
         * 设置下拉框
         */
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);//读取当前年份
        JComboBox<String> jcb = new JComboBox<String>();
        Dimension dim0 = new Dimension(90, 30);//设置组件的宽和高
        Dimension dim = new Dimension(200, 30);//设置组件的宽和高
        jcb.setPreferredSize(dim0);

        JComboBox<String> jcb1 = new JComboBox<String>();
        //jcb1.setPreferredSize(dim0);

        JComboBox<String> jcb2= new JComboBox<String>();
        //jcb.setPreferredSize(dim0);
        for(int i=0;i<70;i++){
            jcb.addItem(year-i+"年");
        }
        jcb.setFont(font);
        panel.add(jcb);
        panel.setBounds(250, 100, 350, 50);
        //String s=jcb.getSelectedItem().toString().trim();//获取下拉框的值
        for(int i=1;i<13;i++){
            jcb1.addItem(i+"月  ");
        }
        jcb1.setFont(font);
        panel.add(jcb1);
        //panel.setBounds(250, 100, 300, 50);
        frame.add(panel);
        for(int i=1;i<31;i++){
            jcb2.addItem(i+"日  ");
        }
        jcb2.setFont(font);
        panel.add(jcb2);
        //panel.setBounds(250, 100, 300, 50);
        /*
         * /* 创建一个账号标签，并设置字体以及字体大小
         */
        JLabel labName = new JLabel("账号    ");// 用标签来表示文本或图片
        labName.setFont(font);// 设置标签字体的大小
        panel2.add(labName);// 将lable标签添加到面板上

        /*
         * 创建一个文本框，并设置大小
         */
        JTextField txtUsername = new JTextField();
        txtUsername.setPreferredSize(dim);

        panel2.add(txtUsername);
        panel2.setBounds(250, 150, 300, 50);// 设置面板的位置和大小
        frame.add(panel2);// 添加面板到窗口中
        /*
         * 创建一个密码标签
         */
        JLabel labPass = new JLabel("密码    ");
        labPass.setFont(font);
        panel3.add(labPass);
        /*
         * 设置密码文本框
         */
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(dim);
        panel3.add(txtPassword);
        panel3.setBounds(250, 200, 300, 50);
        frame.add(panel3);

        /**
         * 确认密码
         */
        JLabel labtruePass = new JLabel("确定密码");
        labtruePass.setFont(font);
        panel6.add(labtruePass);
        /*
         * 设置确定密码文本框
         */
        JPasswordField txttruePassword = new JPasswordField();
        txttruePassword.setPreferredSize(dim);
        panel6.add(txttruePassword);
        panel6.setBounds(250, 250, 300, 50);
        frame.add(panel6);

        /**
         * 姓名
         */
        JLabel labtruename = new JLabel("姓名  ");
        labtruename.setFont(font);
        panel7.add(labtruename);
        /*
         * 设置确定密码文本框
         */
        JTextField txtname = new JTextField();
        txtname.setPreferredSize(dim);
        panel7.add(txtname);
        panel7.setBounds(250, 300, 300, 50);
        frame.add(panel7);

        /**
         * 银行卡号
         */
        JLabel labid= new JLabel("银行卡号");
        labid.setFont(font);
        panel8.add(labid);
        /*
         * 银行卡号
         */
        JTextField txtid = new JTextField();
        txtid.setPreferredSize(dim);
        panel8.add(txtid);
        panel8.setBounds(250, 350, 300, 50);
        frame.add(panel8);

        /**
         * 性别
         */
        JLabel labsex = new JLabel("性别  ");
        labsex.setFont(font);
        panel9.add(labsex);
        /*
         * 设置性别
         */
        JComboBox<String> jcb4 = new JComboBox<String>();
        jcb4.setPreferredSize(dim);
        panel9.setBounds(250,400,300,50);
        jcb4.addItem("女");
        jcb4.addItem("男");
        //jcb4.setFont(font);
        panel9.add(jcb4);
        frame.add(panel9);


        /**
         * 电话号码
         */
        JLabel labnumber= new JLabel("电话号码");
        labnumber.setFont(font);
        panel10.add(labnumber);

        /*
         * 电话号码文本框
         */
        JTextField txtnume = new JTextField();
        txtnume.setPreferredSize(dim);
        panel10.add(txtnume);
        panel10.setBounds(250, 450, 300, 50);
        frame.add(panel10);


        /*
         * 设置按钮
         */
        Dimension dim1 = new Dimension(80, 30);
        JButton jb1 = new JButton("确定");
        JButton jb2 = new JButton("取消");
        jb1.setFont(font);
        jb2.setFont(font);
        jb1.setPreferredSize(dim1);
        jb2.setPreferredSize(dim1);
        panel4.add(jb1);
        panel5.add(jb2);
        panel4.setBounds(325, 500, 80, 50);
        panel5.setBounds(445, 500, 80, 50);
        frame.add(panel4);
        frame.add(panel5);
        frame.setVisible(true);// 显示窗口


        /**
         * 对确定按钮进行监听
         */
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bank bank = new bank();
                Request request =new Request();

                //出生日期
                String s1=jcb.getSelectedItem().toString().trim();//获取下拉框的值
                s1=s1.substring(0,s1.length()-1);
                String s2=jcb1.getSelectedItem().toString().trim();//获取下拉框的值
                s2=s2.substring(0,s2.length()-1);
                String s3=jcb2.getSelectedItem().toString().trim();
                s3=s3.substring(0,s3.length()-1);
                String s4=jcb4.getSelectedItem().toString().trim();
                if(s4.equals("男")) s4="M";
                else s4="W";
                System.out.println(s1+s2+s3);
                String s = s1+"-"+s2+"-"+s3;
                System.out.println(s);
                DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = dateformat.parse(s);
                    bank.setBrithday(date);
                    System.out.println(date);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                //账户导入
                String username=txtUsername.getText();
                bank.setUsername(username);

                //密码导入
                String password=new String(txtPassword.getPassword());
                if(password.equals("")){
                    new ErrorPrompt("密码不可为空！");
                }
                String password2=new String(txttruePassword.getPassword());
                if(password2.equals(password)) bank.setPassword(password);
                else new ErrorPrompt("确认密码不一致");

                //姓名导入
                String name =txtname.getText();
                if(name.equals("")){
                    new ErrorPrompt("姓名不可为空！");
                }
                bank.setName(name);
                //银行卡号导入
                String id = txtid.getText();
                if(id.length()>12) new ErrorPrompt("银行卡号最多只有12位！");
                else {
                    if (id.equals("")||!(id.matches("[0-9]+"))){
                        new ErrorPrompt("请输入纯数字！");
                        return;
                    }
                    else bank.setId(id);
                }
                request.setBank(bank);

                //电话号码导入
                String number = txtnume.getText();
                System.out.println(number+number.length());
                if(number.length()>11) new ErrorPrompt("电话号码最多只有11位！");
                else {
                    if (number.equals("")||!(number.matches("[0-9]+"))){
                        new ErrorPrompt("请输入纯数字！");
                        return;
                    }
                    else bank.setNumber(number);
                }

                //性别导入
                bank.setSex(s4);

                //初始余额为2000元
                bank.setBalance(2000);
                request.setBank(bank);

                request.setA("login_user");
                try {
                    request=connect.in_out(request);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                if(request.getBank()!=null){
                    new ErrorPrompt("账号名重复！");
                }
                else {
                    //开始注册过程
                    request.setBank(bank);
                    request.setA("register");
                    try {
                        connect.in_out(request);
                        new ErrorPrompt("注册成功！");
                        dispose();
                        new LogOnJFrame();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        /**
         * 对返回按键的监听
         */
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new LogOnJFrame();
            }
        });
    }


    /**
     * 对注册窗口进行测试
     * @param args
     */
    public static void main(String[] args) {
        RegisterFrame log =new RegisterFrame();
    }
}