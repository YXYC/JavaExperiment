//Keyboard.java
import java.util.Scanner;
public class Keyboard {  //抽象输入键盘模块
	private Scanner keyin=new Scanner(System.in);
    public double inputDouble() {
    	   return keyin.nextDouble();    }
    public String inputString() {
        return keyin.next();    }
}
