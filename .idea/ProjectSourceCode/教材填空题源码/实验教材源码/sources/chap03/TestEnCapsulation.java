//TestEnCapsulation.java
public class TestEnCapsulation {
	public static void main(String[] args){
        Screen myscreen=new Screen();
        myscreen.cls();
        Rectangle rc1=new Rectangle(0,0,6,5);
        rc1.h=-3;          //试图直接修改数据，无法通过编译
        rc1.x=10;         //试图直接修改数据，无法通过编译
        rc1.printme(myscreen);
        Rectangle rc2=new Rectangle(32,4,5,7);
        rc2.w=10;         //试图直接修改数据，无法通过编译
        rc2.printme(myscreen);
        myscreen.data[5][33]=’中’; //试图直接修改数据
        myscreen.display();
	}
}
