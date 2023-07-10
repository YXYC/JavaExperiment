package ExperimentSourceCode_9.BANK.BankClient.gui;

import ExperimentSourceCode_9.BANK.BankClient.Client.C_Connect;
import ExperimentSourceCode_9.BANK.BankClient.gui.util.ErrorPrompt ;
import ExperimentSourceCode_9.BANK.bean.Request;
import ExperimentSourceCode_9.BANK.bean.bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CountFrame extends JFrame {
    private final bank user;
    private final Container container = getContentPane();
    private C_Connect connect = new C_Connect();
    public CountFrame(bank user){
        this.user = user;
        container.setLayout(null);
        setContent();
        setWindow();
    }
    private void setWindow(){
        this.setVisible(true);                                          //窗口可见
        this.setSize(400,350);                                          //窗口大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            //窗口可关闭
        this.setLocationRelativeTo(null);                               //窗口居中
        this.setTitle("飞马银行");                                     //窗口标题
    }
    private void setContent(){
        /* 标签 */
        JLabel jLabel1 = new JLabel("转账系统");
        jLabel1.setBounds(140,30,100,50);
        jLabel1.setFont(new Font("楷体",Font.PLAIN,20));


        JLabel jLabel3 = new JLabel("目标卡号:");
        jLabel3.setBounds(60,80,100,50);
        jLabel3.setFont(new Font("楷体",Font.PLAIN,20));

        JLabel jLabel4 = new JLabel("转账金额:");
        jLabel4.setBounds(60,120,100,50);
        jLabel4.setFont(new Font("楷体",Font.PLAIN,20));

        /* 文本域*/

        JTextField jTextField2 = new JTextField(20);
        jTextField2.setBounds(170,90,150,30);

        JTextField jTextField3 = new JTextField(20);
        jTextField3.setBounds(170,130,150,30);

        /* 按钮 */
        JButton jButton= new JButton("转账");
        jButton.setBounds(100,220,80,40);
        jButton.setFont(new Font("楷体",Font.PLAIN,20));

        JButton jButton1= new JButton("退出");
        jButton1.setBounds(200,220,80,40);
        jButton1.setFont(new Font("楷体",Font.PLAIN,20));
        /* 转账 按钮的监听事件 */
        /**
         * 银行卡号为12位纯数字
         *     1.不为空
         *     2.12位
         *     3.不含有字母
         *
         * 转账金额不大于余额
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
                    bank bankout =new bank();//转出金额的账户
                    bankout.setId(text1);

                    bank bankin =new bank();//转入金额的账户
                    bankin.setId(text2);

                    Request request_out = new Request();
                    request_out.setBank(bankout);
                    Request request_in =new Request();
                    request_in.setBank(bankin);
                    String text3 = jTextField3.getText();
                    if (text3.equals("")){
                        new ErrorPrompt("请输入纯数字！");
                        return;
                    }

                    double num = Double.parseDouble(text3);//转账金额
                    request_in.setB(num);
                    request_in.setA("store");
                    request_out.setA("take");
                    request_out.setB(num);
                    request_out=connect.in_out(request_out);
                    System.out.println(request_out.getA());
                    //转账账户不存在或者剩余金额不足时应该报错
                    if( request_out.getA().equals("no_enough")){
                        new ErrorPrompt("余额不足！");
                    }
                    else{
                        if (request_out.getBank().getName()==null)
                        {
                            new ErrorPrompt("查无此账户！");
                        }
                        //System.out.println("开始存钱"+request_in.getBank().getId());
                        else {
                            connect.in_out(request_in);
                        }
                    }
                }catch (Exception exception) {
                    throw new RuntimeException(exception);
                }
            }
        });

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();//退出当前窗口
            }
        });
        container.add(jLabel1);
        //container.add(jLabel2);
        container.add(jLabel3);
        container.add(jLabel4);
        //container.add(jTextField1);
        container.add(jTextField2);
        container.add(jTextField3);
        container.add(jButton);
        container.add(jButton1);
    }

    /**
     * 测试功能
     * @param args
     */
    public static void main(String[] args) {
        bank user = new bank();
        CountFrame C =new CountFrame(user);
    }
}

