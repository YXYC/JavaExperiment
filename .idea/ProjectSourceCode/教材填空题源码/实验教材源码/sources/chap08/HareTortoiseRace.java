class Tortoise extends Thread {
   int sleepTime=0,targetLength=100;
   Tortoise(int sleepTime,int targetLength){
      this.sleepTime=sleepTime;
      this.targetLength=targetLength;
      this.setName("Tortoise ");   // �����̵߳�����ΪTortoise
   }
   public void run() {
      while(true){
          targetLength=targetLength-1;
          System.out.print("T");
          try { 
            sleep(sleepTime);  // ���̵߳��� sleep����������Ϣ״̬��
                   // sleepTime������߳������Ŷӣ��ȴ�CPU��Դ
            }
          catch(InterruptedException e) {}
           if(targetLength<=0){
                System.out.printf(getName()+"����Ŀ�ĵأ�\n");
                stop();  // �����߳�
            }
      }
   }
}
class Hare extends Thread {
   int sleepTime=0,targetLength=100;
   Hare(int sleepTime,int targetLength){
      this.sleepTime=sleepTime;
      this.targetLength=targetLength;
      setName("Hare");   // �����̵߳�����ΪHare
   }
   public void run() { 
      while(true) {
          targetLength=targetLength-3;
          System.out.printf("H");
          try { 
             sleep(sleepTime);  // ���̵߳��� sleep���������ж�״̬��
                  //sleepTime����� �߳������Ŷӣ��ȴ�CUP��Դ
          }catch(InterruptedException e) {  }
           if(targetLength<=0)  {
                System.out.printf(getName()+"����Ŀ�ĵأ�\n");
                stop();
            }
      }
   }}
public class HareTortoiseRace
{
   public static void main(String args[ ])
   {
       Hare  hare;
       hare=new Hare(500,100);  // �½��߳�hare
       Tortoise tortoise;
       tortoise=new Tortoise(100,100); // �½��߳�tortoise
       hare.start();  // �����߳�hare
       tortoise.start();    // �����߳�tortoise
   }
}
