class ShopWorker implements Runnable
{
    static Thread  zhangSan,liSi,boss;
    ShopWorker()
    {
       boss=new Thread(this);  // 创建boss对象
       zhangSan=new Thread(this);  // 创建zhangSan对象
       liSi=new Thread(this); // 创建liSi对象
       zhangSan.setName("张三");
       liSi.setName("李四");
       boss.setName("老板");
    } 
    public void run() {     
       int i=0;
       if(Thread.currentThread()==zhangSan){
          while(true){
            try{
              i++;
              System.out.println(Thread.currentThread().getName()+" 已搬了"+i+" 箱货物，休息一会儿");
              if(i==3)  return;
              Thread.sleep(10000);      // zhangSan睡眠10秒（10000毫秒）
            }catch(InterruptedException e){
              System.out.println(boss.getName()+" 让"+Thread.currentThread().getName()+" 继续工作");
            }
          }
        }else if(Thread.currentThread()==liSi){
            while(true){
              try{
                i++;
                System.out.println(Thread.currentThread().getName()+" 已搬了"+i+" 箱货物，休息一会儿");
                if(i==3)  return;
                Thread.sleep(10000);   // liSi睡眠10秒（10000毫秒）
              }catch(InterruptedException e){
                 System.out.println(boss.getName()+" 让"+Thread.currentThread().getName()+" 继续工作");
              }
            }
        }else if(Thread.currentThread()==boss){
           while(true){
             zhangSan.interrupt();  // 吵醒zhangSan
             liSi.interrupt();    // 吵醒liSi
             if(!(zhangSan.isAlive()||liSi.isAlive())){
                 System.out.println("下班了"); 
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

