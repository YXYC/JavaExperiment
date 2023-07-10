//TestSingleton.java
public class TestSingleton {
    public static void main(String[] args) {
    	//Singleton obj1 = new Singleton();   //无法创建对象
    	Singleton obj = Singleton.getInstance();//获得Singleton对象
        obj.displayinfo();//通过该对象调用displayinfo方法
    }
}