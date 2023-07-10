import java.util.Date;
public class SleepClock {
	public static void main(String[] args){
		while(true){
			System.out.println("当前时间："+new Date());
			try{
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}