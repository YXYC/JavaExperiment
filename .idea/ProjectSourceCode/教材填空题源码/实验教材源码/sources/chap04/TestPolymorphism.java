//TestPolymorphism.java   
public class TestPolymorphism {
	public static void main(String[] args) {
        Screen myscreen=new Screen(25,80);
        myscreen.cls();
        Shape shapes[]=new Shape[5]; //通过父类定义了有5个引用变量的数组
        shapes[0]=new Lingxing(0,0,9);  //指向一个菱形对象
        shapes[1]=new Lingxing(20,1,12);
        shapes[2]=new Rectangle(14,1,5,7);  //指向一个矩形对象     
        shapes[3]=new Triangle(56,2,7);  //指向一个三角形对象
        shapes[4]=new Circle(34,0,10);  //指向一个圆形对象
        for(int i=0;i<shapes.length;i++) {
            shapes[i].printme(myscreen);  //方法调用相同，但因对象不同执行代码也不同，这就是多态性原理
        }
        myscreen.display();
	}
}