public class Shape {
	protected int x;
	protected int y;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		if(x>=0&&x<1000) this.x = x;
		else this.x=0;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		if(y>=0&&y<1000) this.y = y;
		else this.y=0;
	}
    public Shape() {}
	public Shape(int x,int y) {
		if(x>=0&&x<1000) this.x = x;
		else this.x=0;
		if(y>=0&&y<1000) this.y = y;
		else this.y=0;
	}
public void printme(Screen sc) {
     sc.setY(y);
   	 sc.setX(x);
     System.out.println();
}
    public void move(int x,int y) {
		if(x>=0&&x<1000) this.x = x;
		else this.x=0;
		if(y>=0&&y<1000) this.y = y;
		else this.y=0;
    }
}