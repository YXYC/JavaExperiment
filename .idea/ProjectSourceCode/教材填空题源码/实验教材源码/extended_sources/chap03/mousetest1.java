import java.awt.*;
import java.awt.event.*;
class MPanel  extends Panel {
     int[] x,y;
      int count;
      public MPanel(){
            x=new int[50];y=new int[50];count=0;
            MyMouseAdapter mAction = new MyMouseAdapter();
            addMouseListener(mAction);//�¼�ί��
      }
      public void paint(Graphics g) {
             for(int i=1;i<=count;i++)
             g.fillOval(x[i],y[i],5,5);
      }
      private class MyMouseAdapter extends MouseAdapter  {
            public void mouseClicked(MouseEvent e){//��������¼�
                        count++;
                        x[count] = e.getX();//ȡ���x������
                        y[count] = e.getY();//ȡ���y������
                        repaint();
            }
      }
}
class mousetest1 {
       public static void main(String args[])    {
               Frame myframe=new Frame("������");
               MPanel mp=new MPanel();//����Mpanel����
               myframe.add(mp);
               myframe.setSize(400,400);
               myframe.show();
               myframe.addWindowListener(new WindowDestroyer());
       }
}
