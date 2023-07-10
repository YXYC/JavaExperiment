package threadexec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ShuffleByTimer {

	public static void main(String[] args) {
		System.out.println("使用java.util.Timer定时器\n");
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<10;i++) {
			list.add(100+i*10);
		}
        System.out.println("原始数组数据为:"+list);
        Timer timer=new Timer();
        //创建TimerTask任务
        MyTask task=new MyTask(list);
        int delay=1000;
        int period=500;
        timer.schedule(task, delay,period);
	}
}

//任务类，需要继承TimerTask
class MyTask extends TimerTask {
	ArrayList<Integer> list;
	public MyTask(List list) {
		this.list=(ArrayList<Integer>)list;
	}
	public void run() {
		Collections.shuffle(list);
		System.out.println(list);
	}
}
