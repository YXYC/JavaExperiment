import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class sheji1 extends JFrame implements ActionListener{
   JTextField tf;
   JButton ok,clr;
   public sheji1(){
     super("�ı���Action�¼�����");
     setLayout(new FlowLayout());
     add(new JLabel("����:"));
     tf=new JTextField(40);
     ok=new JButton("ȷ��");
     clr=new JButton("���");
     add(tf);add(ok);add(clr);
     tf.addActionListener(this);
     ok.addActionListener(this);
     clr.addActionListener(this);
   }
   public void actionPerformed(ActionEvent e){
      if(e.getSource()==tf) tf.setText("���,"+tf.getText()+",��ӭ��ʹ��Java���!");
      if(e.getSource()==ok) tf.setText("���,"+tf.getText()+",��ӭ��ʹ��Java���!");
      if(e.getSource()==clr) tf.setText("");
   }
   public static void main(String[] args){
      sheji1 myapp=new sheji1();
      myapp.setSize(500,100);
      myapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      myapp.setVisible(true);
   }
}