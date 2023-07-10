package chap03.encapsulation;

public class Rectangle { 
    int x,y,w,h;
    public Rectangle(int x,int y,int w,int h) {
    	this.x=x;
    	this.y=y;
    	this.w=w;
    	this.h=h;
    }   
    public Rectangle() {
       this(0,0,1,1);
    }

	public void printme(Screen myscreen) {
    	myscreen.setY(y);
        for(int i=1;i<=h;i++)        {
        	myscreen.setX(x);
        	myscreen.repeat('*',w);
            myscreen.println();
        }
    }
}