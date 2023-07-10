package chap03.encapsulation;
public class Screen {
	private final int SCREEN_WIDTH;
	private final int SCREEN_HEIGHT;
	private int x;
	private int y;
	private char[][] data;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		if (x < SCREEN_WIDTH)	this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		if (y < SCREEN_HEIGHT)	this.y = y;
	}
	public Screen() {
		SCREEN_HEIGHT = 50;
		SCREEN_WIDTH = 80;
		data = new char[SCREEN_HEIGHT][SCREEN_WIDTH];
	}
	public Screen(int r, int c) { //通过判断对输入的数据进行过滤
		if (r >= 1 && r <= 1000)
			SCREEN_HEIGHT = r;
		else
			SCREEN_HEIGHT = 50;
		if (c >= 1 && c <= 1000)
			SCREEN_WIDTH = c;
		else
			SCREEN_WIDTH = 80;
		data = new char[SCREEN_HEIGHT][SCREEN_WIDTH];
	}

	public void init() {
		for (int i = 0; i < SCREEN_HEIGHT; i++) {
			for (int j = 0; j < SCREEN_WIDTH; j++) {
				data[i][j] = ' ';
			}
		}
	}
	public void display() {
		for (int i = 0; i < SCREEN_HEIGHT; i++) {
			for (int j = 0; j < SCREEN_WIDTH; j++) {
				System.out.print(data[i][j]);
			}
			System.out.println();
		}
	}

	public void repeat(char ch, int m) {
		for (int i = 1; i <= m; i++)
			print(ch);
	}
	public void print(char ch) {
		if (y < SCREEN_HEIGHT && x < SCREEN_WIDTH) {
			data[y][x] = ch;
			x++;
			if (x == SCREEN_WIDTH) {
				y++;
				if (y == SCREEN_HEIGHT) {
					scroll();
					y = SCREEN_HEIGHT - 1;
				}
				x = 0;
			}
		} else {
			System.out.println("错误：超出屏幕了！");
		}
	}
	public void println() {
		y++;
		if (y == SCREEN_HEIGHT) {
			scroll();
			y = SCREEN_HEIGHT - 1;
		}
		x = 0;
	}
	public void scroll() {
		for (int i = 0; i < data.length - 1; i++) {
			data[i] = data[i + 1];
		}
		data[data.length - 1] = new char[SCREEN_WIDTH];
	}
}