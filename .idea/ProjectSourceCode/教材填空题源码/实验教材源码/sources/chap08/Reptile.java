public class Reptile extends Thread{
  public void run(){
    for(int a=0;a<50;a++){
      System.out.println(currentThread().getName()+": I am crawling "+a+" step!");
      try{
        sleep(100);  //给其他线程运行的机会
      }catch(InterruptedException e){throw new RuntimeException(e);}
    }
  }
  public static void main(String args[]){
    Reptile reptile1=new Reptile();  //创建第一个Reptile对象
    reptile1.setName("Reptile1");
    Reptile reptile2=new Reptile();  //创建第二个Reptile对象
    reptile2.setName("Reptile2");
    reptile1.start();  //启动第一个Reptile线程
    reptile2.start();  //启动第二个Reptile线程
  }
}
