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
    		System.out.printf("�߳�%sȡǮ,Ŀǰ���Ϊ%dԪ��ȡ����%dԪ��ʣ��%dԪ\n", Thread.currentThread().getName(),left,take_out,left-take_out);
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
