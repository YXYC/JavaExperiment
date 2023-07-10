import java.awt.*;
class Point {
     int x;
     private int y;
     private Color color;
     Point(int x1,int y1)
    {
       x=x1>=0&&x1<=800?x1:0;
       y=y1>=0&&y1<=600?y1:0;
     }
     Point(int x1,int y1,Color c1)
     {
       x=x1>=0&&x1<=800?x1:0;
       y=y1>=0&&y1<=600?y1:0;
       color=c1;
     }
     public int getX(){return x;}
     public int getY(){return y;}
     public Color getColor(){return color;}
     public void setX(int x1)
     { x=x1>=0&&x1<=800?x1:0;}
     public void setY(int y1)
     {y=y1>=0&&y1<=600?y1:0;}
     public void setColor(Color c){color=c;}
     public void display()
     {
          System.out.println("x:"+x+",y:"+y+",color:"+color);
      }
      public boolean equals(Point p)
      {
           if(this.x==p.x&&this.y==p.y)
                return true;
           else
                return false;
      }
      public String toString()
      {
          String str="("+x+","+y+") color="+color;
          return str;
      }
}