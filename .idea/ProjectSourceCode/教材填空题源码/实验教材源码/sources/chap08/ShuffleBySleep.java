package threadexec;

import java.util.ArrayList;
import java.util.Collections;

public class ShuffleBySleep {
    public static void main(String[] args) throws InterruptedException {
    	ArrayList<Integer> list=new ArrayList<Integer>();
    	for(int i=0;i<10;i++) {
    		list.add(100+i*10);
    	}
    	System.out.println("原始数组数据为："+list);
    	while(true) {
    		Collections.shuffle(list);
    		System.out.println(list);
    		Thread.sleep(500);
    	}
    }
}
