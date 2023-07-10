public class Circle extends Shape{
	private int r;
	public Circle(int x,int y,int r) {
		super(x,y);
		this.r=r;
	}
	public void printme(Screen sc) { //覆盖父类中的printme方法
		// x*x+y*y=r*r
        sc.setY(y);
        for(int y=0;y<=2*r;y+=2) {
        	int lx=(int)Math.round(r-Math.sqrt(2*r*y-y*y));
        	int len=2*(r-lx);
        	sc.setX(this.x+lx);
        	sc.print('*');
        	for(int j=0;j<=len;j++) {
        		sc.print('*');
        	}
        	sc.print('*');
        	sc.println();
        }
	}
}