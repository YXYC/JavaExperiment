import java.awt.*;
class Frame1 {
   public static void main(String[] args){
       Frame  myapp=new Frame("GUI���ڱ���");
       myapp.setSize(300,300);
       myapp.setVisible(true);
       myapp.addWindowListener(new WindowCloser());//����ǰ���WindowCloser
   }
}