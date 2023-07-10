import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
public class CalPrimeThreadPool {
    public static AtomicInteger numOfPrime;
    public static void main(String[] args) throws InterruptedException {
    	long start=1000000;
    	long end=5000000;
    	//创建线程池
    	ExecutorService pool = Executors.newFixedThreadPool(20);
    	CalPrimeThreadPool calPrime=new CalPrimeThreadPool();
    	calPrime.numOfPrime=new AtomicInteger(0);
    	//计算程序运行时间
    	long startTime=System.nanoTime();
    	//对计算任务进行划分，每个线程计算任务为10000个数
    	int range=100000;
    	for(long i=0;i<(end-start)/range;i++){
    		pool.submit(new WorkThread(start+i*range,start+(i+1)*range,calPrime));
    	}
    	pool.shutdown();    	//停止任务提交
    	//等待线程池的任务完成
    	pool.awaitTermination(10, TimeUnit.DAYS);
    	double estimatedTime=(System.nanoTime()-startTime)/1000000000.0;
		System.out.printf("使用线程池计算%d~%d的素数\n",start,end);
		System.out.printf("共有素数%d个，花费时间为%.2f秒",calPrime.numOfPrime.get(),estimatedTime);
    }
}
class WorkThread implements Runnable {
	long startPos;
	long endPos;
	CalPrimeThreadPool result;
	public WorkThread(long startPos,long endPos,CalPrimeThreadPool result){
		this.startPos=startPos;
		this.endPos=endPos;
		this.result=result;
	}
	public boolean isPrime(long n){
	    	for(long i=2;i<=Math.sqrt(n);++i) {
	    		if(n%i==0) return false;
	    	}
	    	return true;
	    }
	public void run(){
		for(long i=startPos;i<endPos;++i){
			 if(isPrime(i)) result.numOfPrime.incrementAndGet();
		 }
	 }}
