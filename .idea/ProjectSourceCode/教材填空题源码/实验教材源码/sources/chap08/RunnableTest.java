import javax.swing.*;
import java.awt.*;
public class RunnableTest extends JFrame implements Runnable 
{
    JLabel prompt1 = new JLabel("��һ�����߳�");
    JLabel prompt2 = new JLabel("�ڶ������߳�");     
    JTextField threadFirst = new JTextField(14);           
    JTextField threadSecond = new JTextField(14);    
    Thread thread1,thread2; //����Thread���̶߳���
    int count1=0,count2=0; //����������
    public RunnableTest( )
    {
        super("�̲߳���");
        setLayout(new FlowLayout());
        add(prompt1);
        add(threadFirst);
        add(prompt2);
        add(threadSecond);
    }
    public void start( )
    {       
        thread1 = new Thread(this,"FirstThread");
        thread2 = new Thread(this,"SecondThread");
        thread1.start( );//�����̶߳��󣬽������״̬
        thread2.start( );
    }
    public void run( ) //ʵ��Runnable�ӿڵ�run( )����
    {
        String currentRunning;
        while(true) {   //����ѭ��
           try {              //ʹ��ǰ��߳�����0��3��
               Thread.sleep((int)(Math.random( ) * 3000));
           }
           catch(InterruptedException e){}
           currentRunning = Thread.currentThread( ).getName( );
           if(currentRunning.equals("FirstThread")){
                count1++;
                threadFirst.setText("�߳�1��"+count1+"�α�����");
           }
           else if(currentRunning.equals("SecondThread")){
                count2++;
                threadSecond.setText("�߳�2��"+count2+"�α�����");
           }
        }   
    }   
    public static void main(String[] args){
        RunnableTest myapp=new RunnableTest();
        myapp.setSize(300,100);
        myapp.setVisible(true);
        myapp.start();
        myapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }      
}
