package threadexec;

import java.util.Random;

public class WithDrawMoney implements Runnable {
	private static int total;
	private static int left;
	Random rand=new Random();
	public WithDrawMoney(int t) {
		this.total=t;
		left=total;
	}
    public synchronized void run() {
    	if(left>0) {
    		int take_out=rand.nextInt(100);
    		if(take_out>left) {
    			take_out=left;
    		}
    		System.out.printf("线程%s取钱,目前余额为%d元，取走了%d元，剩余%d元\n", Thread.currentThread().getName(),left,take_out,left-take_out);
    		left-=take_out;
    	}
    }
    public static void main(String[] args) {
    	WithDrawMoney bank=new WithDrawMoney(200);
    	for(int i=0;i<10;++i) {
    		Thread t=new Thread(bank);
    		t.start();
    	}
    }
}
