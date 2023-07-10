package ExperimentSourceCode_5.Philosopher_dining;

public class PhilosopherDining {
    /**
     * 五个哲学家即五个线程，五双筷子即五个线程间的共同资源
     * =>线程同步机制
     * =>处理死锁
     */
    public static void main(String[] args) {
        Philosopher t1 = new Philosopher("哲学家1",1);
        Philosopher t2 = new Philosopher("哲学家2",2);
        Philosopher t3 = new Philosopher("哲学家3",3);
        Philosopher t4 = new Philosopher("哲学家4",4);
        Philosopher t5 = new Philosopher("哲学家5",5);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
