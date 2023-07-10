package ExperimentSourceCode_7.Chat.Server.Connect;


import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServerThread implements Runnable{
    private Socket socket;
    private static ArrayList<String> news = new ArrayList<String>(Arrays.asList("系统消息：Chat Start !"));
    private static Lock   NewsLock = new ReentrantLock();//消息属于所有线程的公共资源，开启同步锁

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date)+":"+socket.getInetAddress().getHostAddress()+"已连接！");
        ServerSendThread sendThread = new ServerSendThread(socket);
        ServerReciveThread reciveThread = new ServerReciveThread(socket);
        Thread t1 = new Thread(sendThread);//发送消息的线程
        Thread t2 = new Thread(reciveThread);//接受消息的线程
        t1.setName("Send - ExperimentSourceCode_7.Chat.Server -"+socket.getInetAddress().getHostAddress());
        t2.setName("Receive - ExperimentSourceCode_7.Chat.Server -"+socket.getInetAddress().getHostAddress());
        t1.start();
        t2.start();
    }
}
