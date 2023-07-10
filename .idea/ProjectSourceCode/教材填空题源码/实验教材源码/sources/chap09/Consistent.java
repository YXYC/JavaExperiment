//Consistent.java
public class Consistent {
    static boolean started=false;
    public ______________ static void setStarted(){
        started=true;
    }
    public _______________ static boolean getStarted(){
        return started;
    }
        public static void main(String[] args){
        Thread thread1=new Thread(new Runnable(){
             public void run(){
                 try {
                     Thread.sleep(3000);
                 }catch(InterruptedException e){
                 }
                 setStarted();
                 System.out.println("started set to true");
             }
         });
         ___________________;//启动子线程
         while(!getStarted()){
         }
         System.out.println("Wait 3 seconds and exit");
    }
}