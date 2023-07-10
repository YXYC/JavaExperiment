import java.awt.*;
import java.awt.event.*;//�����¼���
class MyPanel extends Panel {
  String msg="Pressed Key: ";
  public MyPanel() {
      MyKeyAdapter bAction = new MyKeyAdapter();//����MyKeyAdapter����������
      addKeyListener(bAction);//����Panel�����¼�ί�и�bAction
   }
   public void paint(Graphics g) {
          g.setFont(new Font("����",Font.BOLD,24));
          g.drawString(msg,20,50);
 }
 private class MyKeyAdapter extends KeyAdapter {//������������
       public void keyPressed(KeyEvent kevent){
        int keycode=kevent.getKeyCode();//ȡ���̵�ɨ����
        msg="Pressed Key: "+keycode+"char: "+(char)keycode;
        repaint();//ˢ��
 }
      public void keyReleased(KeyEvent kevent) {
         setBackground(Color.red); repaint();  }
      public void keyTyped(KeyEvent kevent) {
        if (kevent.getKeyChar()== 'x')  System.exit(0); }//ȡ��������ַ�
}} 
public class keytest1 {
     public static void main(String [] args){
              Frame myframe=new Frame("���̲���");
              MyPanel  mypanel=new MyPanel();
              myframe.add(mypanel);//��mypanel����myframe
              myframe.addWindowListener(new WindowDestroyer());
              myframe.setSize(300,300);
              myframe.show();
}}
