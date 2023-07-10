package threadexec;

public class CalPrimeSingleThread {
    long primeCount=0;
    public void calPrime(long start,long end) throws Exception {
    	for(long i=start;i<end;++i) {
    		if(isPrime(i)) {
    			primeCount++;
    			//System.out.printf("%ld ", i);
    		}
    	}
    }
    public boolean isPrime(long n) {
    	for(long i=2;i<=Math.sqrt(n);++i) {
    		if(n%i==0) return false;
    	}
    	return true;
    }
	public static void main(String[] args) throws Exception {
		long start=1000000;
		long end=5000000;
		CalPrimeSingleThread prime=new CalPrimeSingleThread();
		long startTime=System.nanoTime();
		prime.calPrime(start, end);
		double estimatedTime=(System.nanoTime()-startTime)/1000000000.0;
		System.out.printf("使用单线程计算%d~%d之间的素数\n",start,end);
		System.out.println("共有素数"+prime.primeCount+"个，花费时间为"+estimatedTime+"秒。");
	}
}
