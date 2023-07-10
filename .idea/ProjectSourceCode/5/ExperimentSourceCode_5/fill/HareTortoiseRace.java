package ExperimentSourceCode_5.fill;

class Tortoise extends Thread {
   int sleepTime=0,targetLength=100;
   Tortoise(int sleepTime,int targetLength){
      this.sleepTime=sleepTime;
      this.targetLength=targetLength;
      this.setName("Tortoise ");   // 设置线程的名字为Tortoise
   }
   public void run() {
      while(true){
          targetLength=targetLength-1;
          System.out.print("T");
          try { 
            sleep(sleepTime);  // 让线程调用 sleep方法进入休息状态，
                   // sleepTime毫秒后线程重新排队，等待CPU资源
            }
          catch(InterruptedException e) {}
           if(targetLength<=0){
                System.out.printf(getName()+"到达目的地！\n");
                interrupt();  // 结束线程
            }
      }
   }
}
class Hare extends Thread {
   int sleepTime=0,targetLength=100;
   Hare(int sleepTime,int targetLength){
      this.sleepTime=sleepTime;
      this.targetLength=targetLength;
      setName("Hare");   // 设置线程的名字为Hare
   }
   public void run() { 
      while(true) {
          targetLength=targetLength-3;
          System.out.printf("H");
          try { 
             sleep(sleepTime);  // 让线程调用 sleep方法进入中断状态，
                  //sleepTime毫秒后 线程重新排队，等待CUP资源
          }catch(InterruptedException e) {  }
           if(targetLength<=0)  {
                System.out.printf(getName()+"到达目的地！\n");
                interrupt();
            }
      }
   }}
public class HareTortoiseRace
{
   public static void main(String args[ ])
   {
       Hare  hare;
       hare=new Hare(500,100);  // 新建线程hare
       Tortoise tortoise;
       tortoise=new Tortoise(100,100); // 新建线程tortoise
       hare.start();  // 启动线程hare
       tortoise.start();    // 启动线程tortoise
   }
}