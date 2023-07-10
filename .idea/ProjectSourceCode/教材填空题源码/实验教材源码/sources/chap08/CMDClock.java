import java.util.Timer;
import java.util.TimerTask;
public class CMDClock {
	public static void main(String[] args){
		System.out.println("这是一个命令行时钟，每隔1秒，输出当前时钟时间！");
		Timer timer=new Timer();  //创建时钟对象
		PrintTime task=new PrintTime();
		int delay=1000;  //延迟1秒任务开始
		int period=1000;  //每隔1秒执行1次
		timer.schedule(task, delay, period);

	}
}
class PrintTime extends TimerTask{
	public void run(){
		System.out.println("当前时间："+new java.util.Date());
	}
}