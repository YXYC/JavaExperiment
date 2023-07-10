import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class sheji1 extends JFrame implements ActionListener{
   JTextField tf;
   JButton ok,clr;
   public sheji1(){
     super("文本框Action事件测试");
     setLayout(new FlowLayout());
     add(new JLabel("姓名:"));
     tf=new JTextField(40);
     ok=new JButton("确定");
     clr=new JButton("清除");
     add(tf);add(ok);add(clr);
     tf.addActionListener(this);
     ok.addActionListener(this);
     clr.addActionListener(this);
   }
   public void actionPerformed(ActionEvent e){
      if(e.getSource()==tf) tf.setText("你好,"+tf.getText()+",欢迎你使用Java编程!");
      if(e.getSource()==ok) tf.setText("你好,"+tf.getText()+",欢迎你使用Java编程!");
      if(e.getSource()==clr) tf.setText("");
   }
   public static void main(String[] args){
      sheji1 myapp=new sheji1();
      myapp.setSize(500,100);
      myapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myapp.setVisible(true);
   }
}