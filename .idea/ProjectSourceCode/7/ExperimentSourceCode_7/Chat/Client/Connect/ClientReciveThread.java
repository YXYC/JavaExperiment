package ExperimentSourceCode_7.Chat.Client.Connect;

import ExperimentSourceCode_7.Chat.Bean.Request;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * 接受服务端数据
 */
public class ClientReciveThread implements Runnable{

    private Socket socket; //套接字
    private Request DataGet; //接受到的数据
    private boolean exit = false;//防止线程无法关闭

    //构造方法：传入套接字和当前线程
    public ClientReciveThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        Thread t = Thread.currentThread();//当前线程
        while(!exit){
            try {
                System.out.println(t.getName()+": 等待接受数据中"); //接收的是所有的消息
                ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                Object obj = is.readObject();//阻塞方法 ==> 如果没有就一直停在这里
                DataGet = (Request) obj;
                ClientConnect.GetNews(DataGet);//回调接收到的数据
                System.out.println(t.getName()+"成功接收到数据！"+ClientConnect.GetData.getHistory().toString());
                //is.close();//关闭输入流
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }//一直监听
        System.out.println(t.getName()+"：线程关闭！");
    }

    //获得当前线程收到的消息
    public Request getDataGet() {
        return DataGet;
    }
}
