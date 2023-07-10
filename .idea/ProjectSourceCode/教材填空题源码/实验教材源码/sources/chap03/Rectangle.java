//Rectangle.java
public class Rectangle {
    int x,y,w,h;
    Rectangle() {
       ______________;//调用另一个构造方法传递参数(0,0,1,1)
    }
    public Rectangle(int x,int y,int w,int h) {
        this.x=x;
    	   this.y=y;
        this.w=w;
        this.h=h;
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