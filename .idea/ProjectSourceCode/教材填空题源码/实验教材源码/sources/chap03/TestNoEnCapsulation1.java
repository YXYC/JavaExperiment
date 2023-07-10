//TestNoEnCapsulation1.java
public class TestNoEnCapsulation1 {
	public static void main(String[] args){
        Screen myscreen=new Screen();
        myscreen.cls();
        myscreen.width=-3;//屏幕对象的宽带改为-3，程序报错！
        Rectangle rc1=new Rectangle(0,0,6,5);
        rc1.printme(myscreen);
        Rectangle rc2=new Rectangle(32,4,5,7);
        rc2.printme(myscreen);
        myscreen.display();
	}
}
