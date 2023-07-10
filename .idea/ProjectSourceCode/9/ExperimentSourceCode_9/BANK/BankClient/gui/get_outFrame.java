package ExperimentSourceCode_9.BANK.BankClient.gui;

import ExperimentSourceCode_9.BANK.BankClient.Client.C_Connect;
import ExperimentSourceCode_9.BANK.BankClient.gui.util.ErrorPrompt ;
import ExperimentSourceCode_9.BANK.bean.Request;
import ExperimentSourceCode_9.BANK.bean.bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class get_outFrame extends JFrame {
    private bank user = new bank();
    private final Container container = getContentPane();
    private C_Connect connect = new C_Connect();
    public get_outFrame(bank user){
        this.user=user;
        container.setLayout(null);
        setContent();
        setWindow();
    }
    private void setWindow(){
        this.setVisible(true);                                          //窗口可见
        this.setSize(400,450);                                          //窗口大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            //窗口可关闭
        this.setLocationRelativeTo(null);                               //窗口居中
        this.setTitle("飞马银行");                                     //窗口标题
    }
    private void setContent(){
        /* 标签 */
        JLabel jLabel1 = new JLabel("存钱/取钱");
        jLabel1.setBounds(130,30,100,50);
        jLabel1.setFont(new Font("楷体",Font.PLAIN,20));

        JLabel jLabel3 = new JLabel("金额:");
        jLabel3.setBounds(60,80,100,50);
        jLabel3.setFont(new Font("楷体",Font.PLAIN,20));

        /* 文本域 */

        JTextField jTextField2 = new JTextField(20);
        jTextField2.setBounds(170,90,150,30);

        /* 按钮 */
        JButton jButton3= new JButton("查");
        jButton3.setBounds(120,140,140,40);
        jButton3.setFont(new Font("楷体",Font.PLAIN,20));

        JButton jButton= new JButton("取");
        jButton.setBounds(120,260,140,40);
        jButton.setFont(new Font("楷体",Font.PLAIN,20));

        JButton jButton1 = new JButton("存");
        jButton1.setBounds(120,200,140,40);
        jButton1.setFont(new Font("楷体",Font.PLAIN,20));

        JButton jButton2 = new JButton("退出");
        jButton2.setBounds(120,320,140,40);
        jButton2.setFont(new Font("楷体",Font.PLAIN,20));

        /**
         * 取钱：
         */
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text1 = user.getId();

                    String text2 = jTextField2.getText();
                    if (text2.equals("")||!(text2.matches("[0-9]+"))){
                        new ErrorPrompt("请输入纯数字！");
                        return;
                    }

                    double num = Double.parseDouble(text2);//金额
                    if(num<user.getBalance()) user.setBalance(user.getBalance()-num);
                    Request request =new Request();
                    request.setA("take");
                    bank bank= new bank();
                    bank.setId(text1);
                    request.setBank(bank);
                    request.setB(num);
                    request=connect.in_out(request);

                    if(request.getBank().getName()==null){
                        new ErrorPrompt("账号不存在！");
                    }
                    else if(!request.getA().equals("no_enough")){
                        new ErrorPrompt("当前余额："+request.getBank().getBalance());
                    }
                    else new ErrorPrompt("余额不足！");
                }catch (Exception exception) {
                    throw new RuntimeException(exception);
                }
            }
        });

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text1 = user.getId();
                    if (text1.equals("")||!(text1.matches("[0-9]+"))){
                        new ErrorPrompt("请输入纯数字！");
                        return;
                    }

                    String text2 = jTextField2.getText();
                    if (text2.equals("")||!(text2.matches("[0-9]+"))){
                        new ErrorPrompt("请输入纯数字！");
                        return;
                    }

                    double num = Double.parseDouble(text2);//金额

                    Request request =new Request();
                    request.setA("store");
                    bank bank= new bank();
                    bank.setId(text1);
                    request.setBank(bank);
                    request.setB(num);
                    request=connect.in_out(request);
                     if(!request.getA().equals("no_enough")){
                        new ErrorPrompt("存钱成功！");
                    }
                }catch (Exception exception) {
                    throw new RuntimeException(exception);
                }
            }
        });
        /**
         * 查询余额
         */
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Request request = new Request();
                request.setA("login_user");
                request.setBank(user);
                C_Connect connect = new C_Connect();
                try {
                    request=connect.in_out(request);
                    user.setBalance(request.getBank().getBalance());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                new ErrorPrompt("当前余额："+user.getBalance());
            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        container.add(jLabel1);
        //container.add(jLabel2);
        container.add(jLabel3);
        //container.add(jTextField1);
        container.add(jTextField2);
        container.add(jButton1);
        container.add(jButton);
        container.add(jButton3);
        container.add(jButton2);
    }



    /**
     * 测试功能
     * @param args
     */
    public static void main(String[] args) {
        bank user = new bank();
        user.setId("320210942923");
        user.setBalance(10000);
        get_outFrame C =new get_outFrame(user);
    }
}
