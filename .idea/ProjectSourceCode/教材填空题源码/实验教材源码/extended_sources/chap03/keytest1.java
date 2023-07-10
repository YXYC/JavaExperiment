import java.awt.*;
import java.awt.event.*;//导入事件类
class MyPanel extends Panel {
  String msg="Pressed Key: ";
  public MyPanel() {
      MyKeyAdapter bAction = new MyKeyAdapter();//创建MyKeyAdapter监听器对象
      addKeyListener(bAction);//将此Panel键盘事件委托给bAction
   }
   public void paint(Graphics g) {
          g.setFont(new Font("宋体",Font.BOLD,24));
          g.drawString(msg,20,50);
 }
 private class MyKeyAdapter extends KeyAdapter {//创建监听器类
       public void keyPressed(KeyEvent kevent){
        int keycode=kevent.getKeyCode();//取键盘的扫描码
        msg="Pressed Key: "+keycode+"char: "+(char)keycode;
        repaint();//刷新
 }
      public void keyReleased(KeyEvent kevent) {
         setBackground(Color.red); repaint();  }
      public void keyTyped(KeyEvent kevent) {
        if (kevent.getKeyChar()== 'x')  System.exit(0); }//取所键入的字符
}} 
public class keytest1 {
     public static void main(String [] args){
              Frame myframe=new Frame("键盘测试");
              MyPanel  mypanel=new MyPanel();
              myframe.add(mypanel);//将mypanel放入myframe
              myframe.addWindowListener(new WindowDestroyer());
              myframe.setSize(300,300);
              myframe.show();
}}
