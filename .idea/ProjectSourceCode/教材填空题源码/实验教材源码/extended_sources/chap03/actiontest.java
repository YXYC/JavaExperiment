import java.awt.*;
import java.awt.event.*;
public class actiontest extends Frame {
    public actiontest(){
           super("测试事件");//将窗口标题改为"测试事件"
              Button b1=new Button("红色");
              Button b2=new Button("蓝色");
              setLayout(new FlowLayout());
              add(b1);add(b2);
              MyListener mylistener=new MyListener();//创建监听器对象
              b1.addActionListener(mylistener);
             b2.addActionListener(mylistener);//将b2的ActionEvent事件委托给mylistener
              addWindowListener(new WindowDestroyer());
     }
     private class MyListener implements ActionListener {//创建监听器类MyListener
              public void actionPerformed(ActionEvent e)  {
                    String str=e.getActionCommand();
                    if(str.equals("红色"))  setBackground(Color.red);
                    else setBackground(Color.blue);
              }
      }
      public static void main(String [] args)
      {
           actiontest myapp=new actiontest();
           myapp.setSize(300,300);
           myapp.setVisible(true);//将myapp显示出来
      }}
