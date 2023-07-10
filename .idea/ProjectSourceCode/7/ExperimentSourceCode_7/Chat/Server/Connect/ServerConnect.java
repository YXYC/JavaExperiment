package ExperimentSourceCode_7.Chat.Server.Connect;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端：
 * 1.可能有多个服务端链接，所以需要多个线程进行处理，为方便管理使用线程池
 * 2.对于服务端每个线程，总是接受一条消息，并将需要转发的消息传回客户端（公共资源 需要判断是否更新），再更新需要转发的消息
 *
 */
public class ServerConnect extends Thread{

    /**
     * 检测服务器链接
     */
    private ServerSocket server = new ServerSocket(6665);//服务器Socket对象
    private ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();//创建线程池
    public static ArrayList <String> News = new ArrayList<String>(Arrays.asList("[ 系统消息 ]:"+"\t"+"Chat Start!"));//聊天记录，每接受到新的聊天就要把添加
    public static Object lock = new Object();//同步锁标志

    public ServerConnect() throws IOException {
        System.out.println("-------服务端已经启动 等待客户端链接--------");
    }

    public void run(){
        Socket socket = null;
        //使用while循环，使其一直处于监听状态
        while(true){
            try {
                //阻塞方法
                socket = server.accept();
                ServerThread serverThread = new ServerThread(socket);
                newCachedThreadPool.execute(serverThread);//加入线程池
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 测试
     */
    public static void main(String[] args) throws IOException {
        ServerConnect s = new ServerConnect();
        s.start();
        //GetDataBase_S.start();
    }

}
