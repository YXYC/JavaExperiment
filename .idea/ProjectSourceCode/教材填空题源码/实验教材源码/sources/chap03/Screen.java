//Screen.java
public class Screen {
	int SCREEN_WIDTH; 
	int SCREEN_HEIGHT;
     int x,y;
     char[][] data;
	int getX(){
		return x;
	}
	public void setX(int x){
		 this.x = x;
	}
	public int getY(){
		return y;
	}
	public void setY(int y){
		this.y = y;
	}
	public Screen(){
		SCREEN_HEIGHT=50;
		SCREEN_WIDTH=80;
    	     data=new char[SCREEN_HEIGHT][SCREEN_WIDTH];
    }
    public Screen(int r,int c) {
        SCREEN_HEIGHT=r;
        SCREEN_WIDTH=c;
    	   data=new char[SCREEN_HEIGHT][SCREEN_WIDTH];
    }
    public void cls() {
    	for(int i=0;i<SCREEN_HEIGHT;i++) {
    		for(int j=0;j<SCREEN_WIDTH;j++){
    			data[i][j]=' ';
    		}
    	}
    }
    public void display() {
    	for(int i=0;i<SCREEN_HEIGHT;i++){
    		for(int j=0;j<SCREEN_WIDTH;j++){
    			System.out.print(data[i][j]);
    		}
    		System.out.println();
    	}    	
    }
    public  void repeat(char ch,int m)  {
        for(int i=1;i<=m;i++) print(ch);
    }
	public void print(char ch){
		if (y < SCREEN_HEIGHT && x < SCREEN_WIDTH){
			data[y][x] = ch;
			x++;
			if (x == SCREEN_WIDTH){
				y++;
				if (y == SCREEN_HEIGHT){
					scroll();//屏幕上滚一行
                       y=SCREEN_HEIGHT-1;
				}
				x = 0;
			}
		}else {
			System.out.println("错误：超出屏幕了！");
		}
    }
    public void println() {
    	if(++y==SCREEN_HEIGHT) {
scroll();
        y=SCREEN_HEIGHT-1;
     }
    	x=0;
}
    public void scroll() {
    	for(int i=0;i<data.length-1;i++){
    		data[i]=data[i+1];
    	}
    	data[data.length-1]=new char[SCREEN_WIDTH];
}
}