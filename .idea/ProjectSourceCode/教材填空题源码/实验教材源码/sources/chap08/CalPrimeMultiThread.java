package threadexec;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CalPrimeMultiThread {
    public static CountDownLatch counter;
    public static AtomicInteger numOfPrime;
    
	public static void main(String[] args) throws InterruptedException {
	    long start=1000000;
	    long end=5000000;
	    int totalThreads=10;
	    CalPrimeMultiThread calPrime=new CalPrimeMultiThread();
	    calPrime.counter=new CountDownLatch(totalThreads);
	    calPrime.numOfPrime=new AtomicInteger(0);
	    //��������ʱ��
	    long startTime=System.nanoTime();
	    long len=(end-start)/totalThreads;
	    //����10���̣߳��ֶμ�������ε�����
	    for(long i=0;i<totalThreads;i++) {
	    	Thread t=new Thread(new PrimeThread(start+i*len,start+(i+1)*len-1,calPrime));
	    	t.start();
	    }
	    //�ȴ����߳�ȫ�����
	    calPrime.counter.await();
	    double estimatedTime=(System.nanoTime()-startTime)/1000000000.0;
		System.out.printf("ʹ�ö��̼߳���%d~%d֮�������\n",start,end);
		System.out.printf("��������%d��������ʱ��Ϊ%.2f��",calPrime.numOfPrime.get(),estimatedTime);
	}
}

class PrimeThread implements Runnable {
	long startPos;
	long endPos;
	CalPrimeMultiThread result;
	public PrimeThread(long start,long end,CalPrimeMultiThread result) {
		this.startPos=start;
		this.endPos=end;
		this.result=result;
	}
	   public boolean isPrime(long n) {
	    	for(long i=2;i<=Math.sqrt(n);++i) {
	    		if(n%i==0) return false;
	    	}
	    	return true;
	    }
	   public void run() {
		   for(long i=startPos;i<=endPos;++i) {
			   if(isPrime(i)) result.numOfPrime.incrementAndGet();
		   }
		   result.counter.countDown();
	   }
}