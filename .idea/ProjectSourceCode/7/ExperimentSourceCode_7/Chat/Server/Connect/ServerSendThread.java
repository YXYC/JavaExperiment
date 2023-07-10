package ExperimentSourceCode_7.Chat.Server.Connect;

import ExperimentSourceCode_7.Chat.Bean.Request;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static java.lang.Thread.sleep;


public class ServerSendThread implements Runnable {
    private Request request = new Request(null, null, null);//需要发送的信息
    private Socket socket;//套接字
    private boolean exit = false; //防止while循环不结束
    private int length = 0;

    public ServerSendThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Thread t = Thread.currentThread();//当前线程
        int k =0;
        while (!exit) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //System.out.println(t.getName()+k);
            while(length!=ServerConnect.News.size()){
                //System.out.println("start length:"+length+"start News:"+ServerConnect.News.size());
            try {
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                request.setHistory(ServerConnect.News);//读取聊天记录
                System.out.println(t.getName() + "正在向客户端发送消息：" + request.getHistory()); //发送的是一条消息
                os.writeObject(request);
                os.flush();
                //os.close(); //关闭输出流
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
                System.out.println("消息发送成功！");
                length ++;
        }
            //System.out.println(t.getName()+k);
            k++;
            //if(k==1) System.out.println(t.getName()+k);
        }
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

}
