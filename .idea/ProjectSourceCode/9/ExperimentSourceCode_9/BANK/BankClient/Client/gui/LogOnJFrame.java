package ExperimentSourceCode_9.BANK.BankClient.Client.gui;


import ExperimentSourceCode_9.BANK.BankClient.Client.C_Connect;
import ExperimentSourceCode_9.BANK.BankClient.gui.Main_ManageFrame;
import ExperimentSourceCode_9.BANK.BankClient.gui.Main_UserFrame;
import ExperimentSourceCode_9.BANK.BankClient.gui.RegisterFrame;
import ExperimentSourceCode_9.BANK.BankClient.gui.util.ErrorPrompt;
import ExperimentSourceCode_9.BANK.bean.Request;
import ExperimentSourceCode_9.BANK.bean.User;
import ExperimentSourceCode_9.BANK.bean.bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogOnJFrame extends JFrame{
    ImageIcon back;
    private C_Connect connect = new C_Connect();
    /*
     * 登录界面
     */
    public LogOnJFrame() {
        /*
         * 对窗口的操作，包括创建，设置标题，设置大小以及位置
         */


        JFrame frame = new JFrame();// 创建一个窗口
        frame.setTitle("登录");// 设置窗口标题
        frame.setBounds(250, 100, 825, 600);// 设置窗口位置和大小

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
        JLabel labCard = new JLabel("身份  ");// 用标签来表示文本或图片
        labCard.setFont(font);// 设置标签字体的大小
        panel.add(labCard);// 将lable标签添加到面板上

        /*
         * 设置下拉框
         */
        JComboBox<String> jcb = new JComboBox<String>();
        Dimension dim = new Dimension(200, 30);//设置组件的宽和高
        jcb.setPreferredSize(dim);

        jcb.addItem("用户");
        jcb.addItem("管理员");
        jcb.setFont(font);
        panel.add(jcb);
        panel.setBounds(250, 100, 300, 50);
        frame.add(panel);

        /*
         * /* 创建一个账号标签，并设置字体以及字体大小
         */
        JLabel labName = new JLabel("账号  ");// 用标签来表示文本或图片
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
        JLabel labPass = new JLabel("密码  ");
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

        /*
         * 设置按钮
         */
        Dimension dim1 = new Dimension(80, 30);
        JButton jb1 = new JButton("登录");
        JButton jb2 = new JButton("注册");
        jb1.setFont(font);
        jb2.setFont(font);
        jb1.setPreferredSize(dim1);
        jb2.setPreferredSize(dim1);
        panel4.add(jb1);
        panel5.add(jb2);
        panel4.setBounds(325, 250, 80, 50);
        panel5.setBounds(445, 250, 80, 50);
        frame.add(panel4);
        frame.add(panel5);
        frame.setVisible(true);// 显示窗口

        //对登录按钮的监听
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s=jcb.getSelectedItem().toString().trim();//获取下拉框的值
                String username,password;//分别对应账号密码的值
                username = txtUsername.getText();
                password = new String(txtPassword.getPassword());
                if(s.equals("管理员")){
                    Request res = new Request();
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    res.setA("login");
                    res.setUser(user);
                    //System.out.println(res.getA());
                    res.setUser(user);
                    try {
                        res=connect.in_out(res);
                        //UserService userServiceser = new UserService();
                        //user =userServiceser.login(username,password);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    // 创建用户服务对象
                    // 调用服务对象的登录方法
                    // 判断用户登录是否成功
                    if (res.getUser()!= null) {
                        // 弹出消息框提示用户
                        JOptionPane.showMessageDialog(null, "恭喜，[" + username + "]登录成功~");
                        frame.dispose(); // 关闭登录窗口
                        User okk  =new User();
                        okk.setUsername(username);
                        Main_ManageFrame mainUserFrame = new Main_ManageFrame(okk);
                        mainUserFrame.pack();
                        mainUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        mainUserFrame.setVisible(true);
                    } else {
                        // 弹出消息框提示用户
                        JOptionPane.showMessageDialog(null, "遗憾，用户名或密码错误~");
                        // 清空两个文本框
                        txtUsername.setText("");
                        txtPassword.setText("");
                        // 让姓名文本框获取焦点
                        txtUsername.requestFocus();
                    }
                }
                if(s.equals("用户")){
                    Request res = new Request();
                    bank user = new bank();
                    user.setUsername(username);
                    user.setPassword(password);
                    res.setA("login_user");
                    res.setBank(user);
                    //System.out.println(res.getA());
                    res.setBank(user);
                    try {
                        res=connect.in_out(res);
                        //System.out.println(res.getBank().getUsername());
                        //UserService userServiceser = new UserService();
                        //user =userServiceser.login(username,password);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    // 创建用户服务对象
                    // 调用服务对象的登录方法
                    // 判断用户登录是否成功
                    if (res.getBank()!= null) {
                        // 弹出消息框提示用户
                        JOptionPane.showMessageDialog(null, "恭喜，[" + username + "]登录成功~");
                        frame.dispose(); // 关闭登录窗口
                        Main_UserFrame mainUserFrame = new Main_UserFrame(res.getBank());
                        mainUserFrame.pack();
                        mainUserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        mainUserFrame.setVisible(true);
                    } else {
                        // 弹出消息框提示用户
                        JOptionPane.showMessageDialog(null, "遗憾，用户名或密码错误~");
                        // 清空两个文本框
                        txtUsername.setText("");
                        txtPassword.setText("");
                        // 让姓名文本框获取焦点
                        txtUsername.requestFocus();
                    }
                }

            }
        });

        //对注册按钮进行监听
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s=jcb.getSelectedItem().toString().trim();//获取下拉框的值
                if(s.equals("管理员")){
                    new ErrorPrompt("管理员无法注册！");
                }
                if(s.equals("用户")){
                    frame.dispose();
                    new RegisterFrame();//转到登录界面
                }
            }
        });
    }


    public static void main(String[] args) {
        LogOnJFrame log =new LogOnJFrame();
    }
}

