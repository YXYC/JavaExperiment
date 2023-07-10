public class Rectangle extends Shape {
    private int w;
    private int h;
    public Rectangle()  {
    	this(0,0,1,1);
    }
    public Rectangle(int x,int y,int w,int h) {
    	super(x,y);
    	this.w=w;
    	this.h=h;
    }
    public void printme(Screen myscreen) {
    	myscreen.setY(y);
        for(int i=1;i<=h;i++)  {
        	myscreen.setX(x);
        	myscreen.repeat('*',w);
            myscreen.println();
        }
    }
}