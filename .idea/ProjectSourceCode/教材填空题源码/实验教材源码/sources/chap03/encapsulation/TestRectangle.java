package chap03.encapsulation;
public class TestRectangle {
	public static void main(String[] args) {
        Screen myscreen=new Screen();
        Rectangle rc1=new Rectangle(0,0,6,5);//��0��0�е�5��6�еľ���
        rc1.printme(myscreen);
        Rectangle rc2=new Rectangle(32,4,5,7);//��4��32�е�7��5�о���
        rc2.printme(myscreen);
        myscreen.display();
	}
}