import java.awt.*;
import java.awt.event.*;
class frametest {
     public static void main(String[] args)  {
            Frame myframe=new Frame("���Դ���");
            Panel p1=new Panel();//����һ��Panel����
            Panel p11=new Panel();
            p1.setBackground(Color.red);
            p1.setLayout(new BorderLayout());//����p1�Ĳ��ֹ�����ΪBorderLayout
            myframe.add(p1);
            Label lbl=new Label("���ƣ�");
            TextField txt=new TextField();//����һ��TextField����
             p11.add(lbl);p11.add(txt);
             p1.add(p11,BorderLayout.NORTH);
             final TextArea ta=new TextArea(5,20);
             Font myfont=new Font("TimesNewRome",Font.BOLD,24);
             ta.setFont(myfont);
             p1.add(ta,BorderLayout.CENTER);
             Panel p12=new Panel();
             Button btn=new Button("ȷ��");
             Button btn2=new Button("ȡ��");//����"ȡ��"��ť����
             p12.add(btn);p12.add(btn2);
             p1.add(p12,BorderLayout.SOUTH);
             p1.add(new Button("��"),BorderLayout.EAST);
             p1.add(new Button("��"),BorderLayout.WEST);
             btn.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e){
                          ta.setText("���Ѱ���ȷ����ť");
                   }
              });
            btn2.addActionListener(new ActionListener() {
                   public void actionPerformed(ActionEvent e) {
                          ta.setText("");
                   }
              });
             Panel p2=new Panel();
             Checkbox b1=new Checkbox("�ϻ�");//����"�ϻ�"ѡ��
             Checkbox b2=new Checkbox("ʨ��");
             Checkbox b3=new Checkbox("����",true);
             p2.add(b1);p2.add(b2);p2.add(b3);
             p2.setBackground(Color.blue);
             myframe.add("South",p2);
             Choice moviestars=new Choice();//����Choice����
             moviestars.addItem("�������.�����˹");
             moviestars.addItem("�����ɶ�.�Ͽ������");
             moviestars.addItem("��������");//���"��������"��Ŀ
             moviestars.addItem("��.������");
             moviestars.addItem("������.���`��"); 
             p2.add(moviestars);
             myframe.add(new Button("myframe north"),BorderLayout.NORTH);
             myframe.add(new Button("myframe east"),BorderLayout.EAST);
             myframe.add(new Button("myframe west"),BorderLayout.WEST);
             myframe.setSize(360,300);
             myframe.setVisible(true);
             myframe.addWindowListener(new WindowDestroyer());
     }
}
