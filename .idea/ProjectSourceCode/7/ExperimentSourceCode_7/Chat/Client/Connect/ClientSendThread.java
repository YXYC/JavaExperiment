package ExperimentSourceCode_7.Chat.Client.Connect;

import ExperimentSourceCode_7.Chat.Bean.Request;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 向服务端发送消息的线程：一次只发送一条消息
 */
public class ClientSendThread implements Runnable{
    private Request request;//需要发送的信息
    private Socket socket;//套接字
    public  ClientSendThread(Socket socket, Request request){
        this.request = request;
        this.socket  = socket;
    }

    @Override
    public void run() {
        Thread t = Thread.currentThread();//当前线程
        try {
            System.out.println(t.getName()+":正在向服务端发送消息："+request.getNews()); //发送的是一条消息
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(request);
            os.flush();
            //sleep(10);//保证服务端接受到了数据再关闭
            //os.close(); //关闭输出流
            System.out.println("消息发送成功！");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(t.getName()+"：线程关闭！");
    }
}
