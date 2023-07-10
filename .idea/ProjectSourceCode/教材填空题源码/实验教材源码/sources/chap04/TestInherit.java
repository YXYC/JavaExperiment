//TestInherit.java  ≤‚ ‘¿‡
public class TestInherit {
	public static void main(String[] args) {
        Screen myscreen=new Screen(25,80);
        myscreen.cls();
        Lingxing mylx=new Lingxing(0,0,9);
        mylx.printme(myscreen);
        Lingxing mylx2=new Lingxing(20,1,12);
        mylx2.printme(myscreen);
        Rectangle rc=new Rectangle(14,1,5,7);
        rc.printme(myscreen);
        Triangle tr=new Triangle(56,2,7);
        tr.printme(myscreen);
        Circle c=new Circle(34,0,10);
        c.printme(myscreen);
        myscreen.display();
	}
}
