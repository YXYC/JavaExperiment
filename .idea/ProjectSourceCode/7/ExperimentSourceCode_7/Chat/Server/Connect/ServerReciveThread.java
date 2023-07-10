package ExperimentSourceCode_7.Chat.Server.Connect;

import ExperimentSourceCode_7.Chat.Bean.Request;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 该线程用于接受客户端消息 => 一直监听 => 有新消息就加入历史聊天记录
 */
public class ServerReciveThread implements Runnable{
    private boolean exit = false; //防止while一直不结束
    private Socket socket;
    private Request DataGet; //接受到的数据

    public ServerReciveThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        Thread t = Thread.currentThread();//当前线程
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        //一直监听
        while(!exit){
            try {
                    System.out.println(t.getName()+": 等待接受数据中"); //接收的是所有的消息
                    while(!socket.isConnected()){System.out.println("用户端链接关闭");}
                    ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    Object obj = null;//阻塞方法 ==> 如果没有就一直停在这里
                    obj = is.readObject();
                    DataGet = (Request) obj;
                    System.out.println(t.getName()+":成功接收到数据---"+DataGet.getNews());
                    synchronized (ServerConnect.lock) {
                        ServerConnect.News.add(formatter.format(date) + " " + "[" + DataGet.getUser().getName() + "]:\t" + DataGet.getNews()); //加入历史聊天记录
                    }
            } catch (IOException e) {
                System.out.println(t.getName()+"用户端链接关闭 线程已关闭");
                break;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}
