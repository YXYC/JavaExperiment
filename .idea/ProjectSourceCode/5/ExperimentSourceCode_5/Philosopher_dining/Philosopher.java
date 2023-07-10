package ExperimentSourceCode_5.Philosopher_dining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * 哲学家类 => 进程：思考 -> 吃饭
 * 死锁状态：每个哲学家手中都有一根筷子，都无法拿到另一根筷子
 */

public class Philosopher extends Thread {
    //象征五把筷子（公共资源）对应的同步锁
    static private Lock[] ChopSticks = new Lock[]{new ReentrantLock(), new ReentrantLock(), new ReentrantLock(), new ReentrantLock(), new ReentrantLock()};
    //共同变量用static修饰，开始均是没有使用的状态
    private String name;
    private int ID;

    public Philosopher(String name, int ID) {
        super(name);
        this.name = name;
        this.ID = ID;
    }

    @Override
    public void run() {
        //System.out.println(name+" "+ID);
        try {
            thinking();
            eating();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //思考
    public void thinking() throws InterruptedException {
        System.out.println(name + ":正在思考");
        sleep(1000);
    }

    //就餐过程: 拿起左手筷子 -> 拿起右手筷子 （两把锁）
    public void eating() throws InterruptedException {
        if (ID % 2 == 0) {
            try {
                ChopSticks[ID - 1].lock();//左手筷子上锁
                System.out.println(name + "：左手拿起了筷子" + (ID - 1));
                sleep(1000);
                try {
                    ChopSticks[ID % 5].lock();//右手筷子上锁
                    System.out.println(name + "：右手拿起了筷子，开始就餐" );
                    sleep(1000);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    ChopSticks[ID % 5].unlock();
                    System.out.println(name + "：右手放下了筷子" + (ID % 5));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                ChopSticks[ID - 1].unlock();//左手筷子开锁
                System.out.println(name + "：左手放下了筷子" + (ID % 5));
            }
        }else{
            try {
                ChopSticks[ID%5].lock();//右手筷子上锁
                System.out.println(name + "：右手拿起了筷子" + (ID - 1));
                sleep(1000);
                try {
                    ChopSticks[ID-1].lock();//左手筷子上锁
                    System.out.println(name + "：左手拿起了筷子，开始就餐" );
                    sleep(1000);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    ChopSticks[ID-1].unlock();
                    System.out.println(name + "：左手放下了筷子" + (ID % 5));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                ChopSticks[ID%5].unlock();//右手筷子开锁
                System.out.println(name + "：右手放下了筷子" + (ID % 5));
            }
        }
    }
}
