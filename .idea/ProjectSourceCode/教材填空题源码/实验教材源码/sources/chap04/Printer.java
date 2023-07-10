//Printer.java
public class Printer {
    private String brand;
    public Printer(String brand) {
    	this.brand = brand;  //用局部变量brand初始化成员变量brand
    }
	public void print(String msg) {
		System.out.println("在"+brand+"打印机上打印："+msg);
	}
}
