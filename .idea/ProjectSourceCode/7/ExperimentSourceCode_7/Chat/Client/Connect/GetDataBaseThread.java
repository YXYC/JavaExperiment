package ExperimentSourceCode_7.Chat.Client.Connect;

import ExperimentSourceCode_7.Chat.Bean.Request;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GetDataBaseThread {
    public Request in_out(Request request) {
        for (int i = 0; i < 1; i++) {
            Socket socket = null;
            ObjectOutputStream os = null;
            ObjectInputStream is = null;
            try {
                socket = new Socket("127.0.0.1", 6666);
                os = new ObjectOutputStream(socket.getOutputStream());
                //request = new Request();
                os.writeObject(request);
                System.out.println("正在发送数据到服务端");
                os.flush();
                is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                Object obj = is.readObject();
                if (obj != null) {
                    request = (Request)obj;
                    System.out.println("从服务端接收数据成功！"+request.getNews());
                    //System.out.println("Client:user: " + user.getName() + "/" + user.getPassword());
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    is.close();
                } catch(Exception ex) {}
                try {
                    os.close();
                } catch(Exception ex) {}
                try {
                    socket.close();
                } catch(Exception ex) {}
            }
        }
        return request;
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        GetDataBaseThread getDataBaseThread = new GetDataBaseThread();
        Request request = new Request(null,"来自客户端的测试消息",null);
        try {
            getDataBaseThread.in_out(request);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
