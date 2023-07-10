class ShopWorker implements Runnable
{
    static Thread  zhangSan,liSi,boss;
    ShopWorker()
    {
       boss=new Thread(this);  // ����boss����
       zhangSan=new Thread(this);  // ����zhangSan����
       liSi=new Thread(this); // ����liSi����
       zhangSan.setName("����");
       liSi.setName("����");
       boss.setName("�ϰ�");
    } 
    public void run() {     
       int i=0;
       if(Thread.currentThread()==zhangSan){
          while(true){
            try{
              i++;
              System.out.println(Thread.currentThread().getName()+" �Ѱ���"+i+" ������Ϣһ���");
              if(i==3)  return;
              Thread.sleep(10000);      // zhangSan˯��10�루10000���룩
            }catch(InterruptedException e){
              System.out.println(boss.getName()+" ��"+Thread.currentThread().getName()+" ��������");
            }
          }
        }else if(Thread.currentThread()==liSi){
            while(true){
              try{
                i++;
                System.out.println(Thread.currentThread().getName()+" �Ѱ���"+i+" ������Ϣһ���");
                if(i==3)  return;
                Thread.sleep(10000);   // liSi˯��10�루10000���룩
              }catch(InterruptedException e){
                 System.out.println(boss.getName()+" ��"+Thread.currentThread().getName()+" ��������");
              }
            }
        }else if(Thread.currentThread()==boss){
           while(true){
             zhangSan.interrupt();  // ����zhangSan
             liSi.interrupt();    // ����liSi
             if(!(zhangSan.isAlive()||liSi.isAlive())){
                 System.out.println("�°���"); 
                 return;
             }
           }
        }
    }
}
class ShopWork
{
    public static void main(String args[])
    {
        ShopWorker shop=new ShopWorker();
        shop.zhangSan.start();
        shop.liSi.start();
        shop.boss.start();
    }
}

