package ExperimentSourceCode_7.Chat.Client.Connect;


import ExperimentSourceCode_7.Chat.Bean.Request;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 客户端：
 * 发送数据 => 需要允许发送的标志（flag），否则会重复消息一直发送
 * 接受数据 => 不需要接受标志，因为接受数据是一个阻塞的过程
 * 对于客户端而言，发送数据和接收数据并不对等，相互独立，可能发送一条消息，接受了很多条消息 => 接受消息使用单独线程
 */

public class ClientConnect{
    private Thread t1 ; // 接受消息的线程
    private Thread t2;  //发送消息的线程
    final private String IP = "127.0.0.1";
    final private int   port = 6665;
    private Socket socket ;
    public static Request GetData;//从服务端接受的聊天记录
    private ExecutorService ThreadPool = Executors.newCachedThreadPool();//创建线程池 =>发送消息

    //静态方法，回调接收到的消息
    public static void GetNews(Request DataGet){
        GetData = DataGet;
    }

    //构造方法
    public ClientConnect(){
        System.out.println("-------正在链接服务端-----");
        try {
            socket = new Socket(IP,port);//获取套接字
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-----链接服务端成功-----");
    }

    /**
     * 开启接收消息线程
     */
    public void ReceiveNews(){
        ClientReciveThread reciveThread = new ClientReciveThread(socket);
        t1 = new Thread(reciveThread);
        t1.setName("Receive-Client");
        t1.start();
    }

    /**
     * 发送消息线程
     * @param request 需要发送的消息
     */
    public void SendNews(Request request){
        ClientSendThread sendThread = new ClientSendThread(socket,request);
        ThreadPool.execute(sendThread);
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        ClientConnect connect = new ClientConnect();
        connect.ReceiveNews();
        Request request = new Request(null,"这是测试消息！",null);
        connect.SendNews(request);
        while(ClientConnect.GetData==null){};
        System.out.println(ClientConnect.GetData.getHistory().toString());
    }
}
