class kuai{
    String name;
    boolean bState;
    public kuai(String name){
       this.name = name;
       bState=true;
    }
   public synchronized void pickup() {           
       while(!bState) {   //����˿����ѱ��ã���ȴ�    
           try { 
               wait(); 
           }catch(Exception e) { 
        	   e.printStackTrace();
           } 
       } 
       bState=false;       //�Ѵ˿��ӵ�״̬��Ϊ�ѱ�����        
   } 
   public synchronized void putdown() {  //���¿��ӣ��ѿ���״̬��Ϊ����
       bState=true;                          
       notifyAll();    //���������ڵȴ����̣߳�����еȴ��˿��ӵ��̣߳�����Լ����ж�
   } 
}
class Philosopher extends Thread{
    String name;
    kuai left,right;
    int thinkTime, eatTime; // ˼�����Է���ʱ��
    public Philosopher(String name, kuai l, kuai r){
       this.name = name;
       left = l;
       right = r;
    }
    public void run(){
        do {
            thinkTime = (int) (Math.random() * 10000); // ��������˼��ʱ��
        } while (thinkTime < 1500);
        try {
            sleep(thinkTime); // ���߳�˯�ߵķ�ʽ����ѧ��˼��һЩʱ��
        } catch (Exception e) {	}
        left.pickup(); // �����ߵĿ���
        System.out.println("��ѧ��"+name+"������ߵĿ���:"+left.name); 
        right.pickup(); // ����ұߵĿ���
        System.out.println("��ѧ��"+name+"�����ұߵĿ���:"+right.name); 
        System.out.println("��ѧ��"+name+"���ڿ�ʼ�Է���!"); 		
        do {
            eatTime = (int) (Math.random() * 10000); // �������ĳԷ�ʱ��
        } while (eatTime < 1500);        
        try {
                sleep(eatTime); // ���߳�˯�ߵķ�ʽ����ѧ�ҳԷ�һЩʱ��
        } catch (Exception e) {}
        synchronized (left) {
            left.putdown(); // ������ߵĿ���
            System.out.println("��ѧ��"+name+"������ߵĿ���:"+left.name); 
        }
        synchronized (right) {
            right.putdown(); // �����ұߵĿ���
            System.out.println("��ѧ��"+name+"�����ұߵĿ���:"+right.name); 
        }
    }
}

public class Shiyan9_3_1 {
    public static void main(String args[]){
        kuai k1 = new kuai("����1��");
        kuai k2 = new kuai("����2��");
        kuai k3 = new kuai("����3��");
        kuai k4 = new kuai("����4��");
        kuai k5 = new kuai("����5��");
        Philosopher p1 = new Philosopher("�ϴ�", k1, k2);
        Philosopher p2 = new Philosopher("�϶�", k2, k3);
        Philosopher p3 = new Philosopher("����", k3, k4);
        Philosopher p4 = new Philosopher("����", k4, k5);
        Philosopher p5 = new Philosopher("����", k5, k1);
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }
}
