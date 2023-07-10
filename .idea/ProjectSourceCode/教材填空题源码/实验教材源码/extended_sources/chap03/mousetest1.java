import java.awt.*;
import java.awt.event.*;
class MPanel  extends Panel {
     int[] x,y;
      int count;
      public MPanel(){
            x=new int[50];y=new int[50];count=0;
            MyMouseAdapter mAction = new MyMouseAdapter();
            addMouseListener(mAction);//事件委托
      }
      public void paint(Graphics g) {
             for(int i=1;i<=count;i++)
             g.fillOval(x[i],y[i],5,5);
      }
      private class MyMouseAdapter extends MouseAdapter  {
            public void mouseClicked(MouseEvent e){//监听鼠标事件
                        count++;
                        x[count] = e.getX();//取鼠标x点坐标
                        y[count] = e.getY();//取鼠标y点坐标
                        repaint();
            }
      }
}
class mousetest1 {
       public static void main(String args[])    {
               Frame myframe=new Frame("鼠标测试");
               MPanel mp=new MPanel();//创建Mpanel对象
               myframe.add(mp);
               myframe.setSize(400,400);
               myframe.show();
               myframe.addWindowListener(new WindowDestroyer());
       }
}
