class BreadSeller         //������������࣬һ�����5ԪǮ��
{
   int fiveNum=1,tenNum=0,twentyNum=0; // ���ʦ������Ǯ����������
   public synchronized void  sellBread(int receiveMoney,int buyNumber)
   { 
       if(receiveMoney==5)
       {
          fiveNum=fiveNum+1; 
          System.out.printf("\n%s����5ԪǮ����������1�����",Thread.currentThread().getName()); 
       }
       else if(receiveMoney==10&&buyNumber==2) 
       {
           tenNum=tenNum+1; 
           System.out.printf("\n%s����10ԪǮ����������2�����",
                             Thread.currentThread().getName()); 
       }
       else if(receiveMoney==10&&buyNumber==1)           
        {  
          while(fiveNum<1)
            { 
                try { 
                       System.out.printf("\n%30s���ߵ�",Thread.currentThread().getName());
                       wait();    //����߳�ռ��CPU�ڼ�ִ����wait,�ͽ���ȴ�״̬��
                       System.out.printf("\n%30s�����ȴ�\n",Thread.currentThread().getName());
                    }
               catch(InterruptedException e)
                    {
                    }
            }
           fiveNum=fiveNum-1;
           tenNum=tenNum+1;
           System.out.printf("\n%s����10ԪǮ������5Ԫ����������1�����",Thread.currentThread().getName());
        }
       else if(receiveMoney==20&&buyNumber==1)           
        {  
          while((fiveNum<1||tenNum<1)&& !(fiveNum>3))
            { 
                try { 
                       System.out.printf("\n%30s���ߵ�",Thread.currentThread().getName());
                       wait();    //����߳�ռ��CPU�ڼ�ִ����wait,�ͽ����ж�״̬��
                       System.out.printf("\n%30s�����ȴ�",Thread.currentThread().getName());
                    }
               catch(InterruptedException e)
                    {
                    }
            }
           if(fiveNum>3)
           {
           fiveNum=fiveNum-3;
           twentyNum=twentyNum+1;
           System.out.printf("\n%s��20ԪǮ����������5Ԫ����������1�����",
                             Thread.currentThread().getName());
           }
           else
           { 
           fiveNum=fiveNum-1;
           tenNum=tenNum-1;
           twentyNum=twentyNum+1;
           System.out.printf("\n%s��20ԪǮ������һ��5Ԫ��һ��10Ԫ����������1�����",
                             Thread.currentThread().getName());
        }}
       else if(receiveMoney==20&&buyNumber==2) 
        {
            while(tenNum<1&&fiveNum<2)
            {
                try { 
                       System.out.printf("\n%30s���ߵ�\n",Thread.currentThread().getName());
                       wait();    //����߳�ռ��CPU�ڼ�ִ����wait,�ͽ����ж�״̬��
                       System.out.printf("\n%30s�����ȴ�",Thread.currentThread().getName());
                    }
               catch(InterruptedException e)
                    {
                    }
            }
            if(fiveNum<2)
            {
            tenNum=tenNum-1;
            twentyNum=twentyNum+1;
            System.out.printf("\n%s��20ԪǮ������һ��10Ԫ����������2�����",
                             Thread.currentThread().getName());
        } 
          }else{
            fiveNum=fiveNum-2;
            twentyNum=twentyNum+1;
            System.out.printf("\n%s��20ԪǮ����������5Ԫ����������2�����",
                             Thread.currentThread().getName());
          }
       notifyAll();
   }
}
class Breadshop implements Runnable            
{  
   Thread zhao,qian,sun,li,zhou;             
   BreadSeller seller;                      
  Breadshop()
   {
      zhao=new Thread(this);
      qian=new Thread(this);
      sun=new Thread(this);
      li=new Thread(this);
      zhou=new Thread(this);
      zhao.setName("��");
      qian.setName("Ǯ");
      sun.setName("��");
      li.setName("��");
      zhou.setName("��");
      seller=new BreadSeller();
   } 
   public void run()
   {
       if(Thread.currentThread()==zhao)
       {
          seller.sellBread(20,2);
       }
       else if(Thread.currentThread()==qian)
       {
          seller.sellBread(20,1);
       }
       else if(Thread.currentThread()==sun)
       {
          seller.sellBread(10,1);
       }
       else if(Thread.currentThread()==li)
       {
          seller.sellBread(10,2);
       }
       else if(Thread.currentThread()==zhou)
       {
          seller.sellBread(5,1);
       }
   }
}
public class SaleExample
{
   public static void main(String args[])
    {
        Breadshop myshop=new Breadshop();      
        myshop.zhao.start();
        try
          {
              Thread.sleep(1000); 
          } 
          catch(InterruptedException e)
          {
          }
        myshop.qian.start();
        try 
          {
             Thread.sleep(1000); 
          } 
          catch(InterruptedException e)
          {
          }
        myshop.sun.start();
        try 
           {
                 Thread.sleep(1000); 
           } 
          catch(InterruptedException e)
           {
           }
        myshop.li.start();
        try 
           {
                 Thread.sleep(1000); 
           } 
        catch(InterruptedException e)
           {
           } 
        myshop.zhou.start();
    }
}
