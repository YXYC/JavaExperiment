package ExperimentSourceCode_7.Chat.Server.Connect;

import ExperimentSourceCode_7.Chat.Bean.Request;
import ExperimentSourceCode_7.Chat.Server.JDBC.Dao.UserDao;
import ExperimentSourceCode_7.Chat.Server.JDBC.impl.UserImpl;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Logger;

public class GetDataBase_S {


    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6666);
        ServerConnect s = new ServerConnect();
        s.setName("Chat - ExperimentSourceCode_7.Chat.Server");
        s.start();
        //System.out.println("********服务端已启动，等待客户端连接*********");
        while (true) {
            Socket socket = server.accept();
            InetAddress ia= socket.getInetAddress();
            String ip=ia.getHostAddress();
            System.out.println("\n\n");
            Date time=new Date();
            System.out.println(time+":"+"客户端"+ip+"已连接");
            invoke(socket);
        }
    }

    private static void invoke(final Socket socket) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                Request request = new Request(null,null,null);
                ObjectInputStream is = null;
                ObjectOutputStream os = null;
                try {
                    is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    os = new ObjectOutputStream(socket.getOutputStream());
                    Object obj = is.readObject();
                    request = (Request) obj;
                    System.out.println("接收到数据："+request.getNews());
                    switch (request.getNews()){
                        case "login":
                            UserDao userDao = new UserImpl();
                            request.setUser(userDao.Login(request.getUser().getUsername(),request.getUser().getPassword()));
                            break;
                    }
                    os.writeObject(request);
                    os.flush();//返回数据
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
