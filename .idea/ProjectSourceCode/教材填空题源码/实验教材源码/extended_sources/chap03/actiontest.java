import java.awt.*;
import java.awt.event.*;
public class actiontest extends Frame {
    public actiontest(){
           super("�����¼�");//�����ڱ����Ϊ"�����¼�"
              Button b1=new Button("��ɫ");
              Button b2=new Button("��ɫ");
              setLayout(new FlowLayout());
              add(b1);add(b2);
              MyListener mylistener=new MyListener();//��������������
              b1.addActionListener(mylistener);
             b2.addActionListener(mylistener);//��b2��ActionEvent�¼�ί�и�mylistener
              addWindowListener(new WindowDestroyer());
     }
     private class MyListener implements ActionListener {//������������MyListener
              public void actionPerformed(ActionEvent e)  {
                    String str=e.getActionCommand();
                    if(str.equals("��ɫ"))  setBackground(Color.red);
                    else setBackground(Color.blue);
              }
      }
      public static void main(String [] args)
      {
           actiontest myapp=new actiontest();
           myapp.setSize(300,300);
           myapp.setVisible(true);//��myapp��ʾ����
      }}
