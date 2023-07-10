class kuai{
    String name;
    boolean bState;
    public kuai(String name){
       this.name = name;
       bState=true;
    }
   public synchronized void pickup() {           
       while(!bState) {   //如果此筷子已被用，则等待    
           try { 
               wait(); 
           }catch(Exception e) { 
        	   e.printStackTrace();
           } 
       } 
       bState=false;       //把此筷子的状态设为已被用了        
   } 
   public synchronized void putdown() {  //放下筷子，把筷子状态设为可用
       bState=true;                          
       notifyAll();    //唤醒其他在等待的线程，如果有等待此筷子的线程，则可以继续判断
   } 
}
class Philosopher extends Thread{
    String name;
    kuai left,right;
    int thinkTime, eatTime; // 思考，吃饭的时间
    public Philosopher(String name, kuai l, kuai r){
       this.name = name;
       left = l;
       right = r;
    }
    public void run(){
        do {
            thinkTime = (int) (Math.random() * 10000); // 获得随机的思考时间
        } while (thinkTime < 1500);
        try {
            sleep(thinkTime); // 以线程睡眠的方式让哲学家思考一些时间
        } catch (Exception e) {	}
        left.pickup(); // 获得左边的筷子
        System.out.println("哲学家"+name+"拿起左边的筷子:"+left.name); 
        right.pickup(); // 获得右边的筷子
        System.out.println("哲学家"+name+"拿起右边的筷子:"+right.name); 
        System.out.println("哲学家"+name+"现在开始吃饭了!"); 		
        do {
            eatTime = (int) (Math.random() * 10000); // 获得随机的吃饭时间
        } while (eatTime < 1500);        
        try {
                sleep(eatTime); // 以线程睡眠的方式让哲学家吃饭一些时间
        } catch (Exception e) {}
        synchronized (left) {
            left.putdown(); // 放下左边的筷子
            System.out.println("哲学家"+name+"放下左边的筷子:"+left.name); 
        }
        synchronized (right) {
            right.putdown(); // 放下右边的筷子
            System.out.println("哲学家"+name+"放下右边的筷子:"+right.name); 
        }
    }
}

public class Shiyan9_3_1 {
    public static void main(String args[]){
        kuai k1 = new kuai("筷子1号");
        kuai k2 = new kuai("筷子2号");
        kuai k3 = new kuai("筷子3号");
        kuai k4 = new kuai("筷子4号");
        kuai k5 = new kuai("筷子5号");
        Philosopher p1 = new Philosopher("老大", k1, k2);
        Philosopher p2 = new Philosopher("老二", k2, k3);
        Philosopher p3 = new Philosopher("老三", k3, k4);
        Philosopher p4 = new Philosopher("老四", k4, k5);
        Philosopher p5 = new Philosopher("老幺", k5, k1);
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }
}
