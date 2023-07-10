//TestNoEnCapsulation.java
public class TestNoEnCapsulation {
	public static void main(String[] args){
        Screen myscreen=new Screen();
        myscreen.cls();
        Rectangle rc1=new Rectangle(0,0,6,5);
        rc1.h=3;    //数据被任意修改，对象被破坏
        rc1.x=10; //数据被任意修改，对象被破坏
        rc1.printme(myscreen);
        Rectangle rc2=new Rectangle(32,4,5,7);
        rc2.w=10;//数据被任意修改，对象被破坏
        rc2.printme(myscreen);
        myscreen.data[5][33]=’中’; //数据被非法修改，对象内容被破坏
        myscreen.display();
	}
}