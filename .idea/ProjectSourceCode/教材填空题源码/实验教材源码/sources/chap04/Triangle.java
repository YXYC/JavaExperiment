public class Triangle extends Shape{
    private int h;
    public Triangle() {
        this(0,0,7);
    }
    public Triangle(int x,int y,int h) {
    	super(x,y);
    	this.h=h;
    }
    public void printme(Screen myscreen) {  //覆盖父类中printme方法
        myscreen.setY(y);
        for(int i=1;i<=h;i++)
        {
        	myscreen.setX(x+h-i);        	
        	myscreen.repeat('*',2*i-1);
            myscreen.println();
        }
    }
}