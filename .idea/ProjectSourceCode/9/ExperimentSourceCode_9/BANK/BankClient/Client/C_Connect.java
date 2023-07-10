package ExperimentSourceCode_9.BANK.BankClient.Client;
import ExperimentSourceCode_9.BANK.bean.Request;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class C_Connect {

    private final static Logger logger = Logger.getLogger(C_Connect.class.getName());

    public Request in_out(Request request)throws Exception {
        for (int i = 0; i < 1; i++) {
            Socket socket = null;
            ObjectOutputStream os = null;
            ObjectInputStream is = null;
            try {
                socket = new Socket("127.0.0.1", 5555);

                os = new ObjectOutputStream(socket.getOutputStream());
                //request = new Request();
                os.writeObject(request);
                System.out.println("正在发送数据到服务端");
                os.flush();
                is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                Object obj = is.readObject();
                if (obj != null) {
                    System.out.println("从服务端接收数据成功！");
                    request = (Request)obj;
                    //System.out.println("Client:user: " + user.getName() + "/" + user.getPassword());
                }
            } catch(IOException ex) {
                logger.log(Level.SEVERE, null, ex);
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

    /*
    测试
     */
    public static void main(String[] args) throws Exception {
        C_Connect connect =new C_Connect();
        Request request =null;
        connect.in_out(request);
    }
}