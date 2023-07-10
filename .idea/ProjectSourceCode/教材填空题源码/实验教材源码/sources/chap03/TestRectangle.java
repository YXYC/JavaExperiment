
public class TestRectangle {
	public static void main(String[] args) {
        Screen myscreen=new Screen();
        Rectangle rc1=new Rectangle(0,0,6,5);//第0行0列的5行6列的矩形
        rc1.printme(myscreen);
        Rectangle rc2=new Rectangle(32,4,5,7);//第4行32列的7行5列矩形
        rc2.printme(myscreen);
        myscreen.display();
	}
}