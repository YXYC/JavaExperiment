//Singleton.java
public class Singleton {
	private static Singleton instance = null;
	private Singleton() {}//封装构造方法
	
	public static Singleton getInstance(){
		if (instance == null) {
			instance = new Singleton();//创建Singleton对象
		}
		return instance;
	}
	
	public void displayinfo(){
		System.out.println("Hello, I am a Singleton Object!");
	}
}

