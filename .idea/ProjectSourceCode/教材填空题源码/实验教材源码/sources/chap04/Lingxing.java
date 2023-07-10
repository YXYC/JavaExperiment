public class Lingxing extends Shape {
	private int h;
	public Lingxing() {
	        this(0,0,7);
	    }
	public Lingxing(int x,int y,int h) {
	    	super(x,y);
	    	this.h=h;
	    }
	public void printme(Screen myscreen) { // 覆盖父类中printme方法
		myscreen.setY(y);
		for (int i = 1; i <= (h + 1) / 2; i++) {
			myscreen.setX(x);
			myscreen.repeat(' ', h / 2 + 1 - i);
			myscreen.repeat('*', 2 * i - 1);
			myscreen.println();
		}
		for (int i = h / 2; i >= 1; i--) {
			myscreen.setX(x);
			myscreen.repeat(' ', h / 2 + 1 - i);
			myscreen.repeat('*', 2 * i - 1);
			myscreen.println();
		}
	}
}
