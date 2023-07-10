package ExperimentSourceCode_9.BANK.BankServer;

import ExperimentSourceCode_9.BANK.BankServer.JDBC.service.reportService;
import ExperimentSourceCode_9.BANK.bean.Request;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class S_Connect {

    private final static Logger logger = Logger.getLogger(S_Connect.class.getName());

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5555);
        System.out.println("********服务端已启动，等待客户端连接*********");
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

            private Server_manage serverManage = new Server_manageLmpl();
            private ExperimentSourceCode_9.BANK.BankServer.JDBC.service.reportService reportService =new reportService();
            public void run() {
                ObjectInputStream is = null;
                ObjectOutputStream os = null;
                try {
                    is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    os = new ObjectOutputStream(socket.getOutputStream());
                    Object obj = is.readObject();
                    //if(obj==null) System.out.println("数据接受失败");
                    Request request = (Request) obj;
                    //System.out.println("Server:user: " + user.getName() + "/" + user.getPassword());
                   // user.setName(user.getName() + "_new");
                    //user.setPassword(user.getPassword() + "_new");
                    //User user2 = new User("user_" + 666, "password_" + 666);
                    if(request.getA()!=null) System.out.println("从客户端接受数据成功！");
                    /**
                     * request 改变的具体方法编写处
                     */
                    switch (request.getA())
                    {
                        case"get_pdf":
                            System.out.println("开始服务："+request.getA());
                            request.setReports(reportService.findAll());
                            break;

                            //批量开户
                        case"list_bank":
                            System.out.println("开始服务："+request.getA());
                            int n = request.getBanks().size();
                            reportService.change_user(2023,n);
                            request.setB(serverManage.list_bank(request));
                            request.setA("Complete!");
                            break;

                        case"delect_user", "delect"://销户
                            System.out.println("开始服务："+request.getA());
                            reportService.change_user(2023,-1);
                            serverManage.delect_bank(request);
                            request.setA("Complete!");
                            break;

                        case"register", "add_bank"://开户
                            System.out.println("开始服务："+request.getA());
                            reportService.change_user(2023,1);
                            serverManage.add_bank(request);
                            request.setA("Complete!");
                            break;

                        case"login_user":
                            System.out.println("开始服务："+request.getA());
                            request=serverManage.login_user(request);
                            request.setA("Complete!");
                            break;

                        case "login":
                            System.out.println("开始服务："+request.getA());
                            request=serverManage.login_test(request);
                            request.setA("Complete!");
                        break;

                        case "find_bank":
                            System.out.println("开始服务："+request.getA());
                            request=serverManage.find_bank();
                            request.setA("Complete!");
                        break;

                        case"modify_bank":
                            System.out.println("开始服务："+request.getA());
                            serverManage.modify_bank(request);
                            request.setA("Complete!");
                            break;

                        case "store"://存钱
                            System.out.println("开始服务："+request.getA());
                            reportService.change_balance(2023,request.getB());
                            request=serverManage.store(request);
                            request.setA("Complete!");
                            break;

                        case"take"://取钱
                            System.out.println(request.getB());
                            System.out.println("开始服务："+request.getA());
                            request=serverManage.take(request);
                            reportService.change_balance(2023,0-request.getB());
                            if(request.getBank().getBalance()<0){
                                request.setA("no_enough");//余额不足
                                request=serverManage.store(request);
                                reportService.change_balance(2023,request.getB());
                                //request.getBank().setBalance(request.getBank().getBalance()+ Double.valueOf(request.getB()));
                            }
                            else request.setA("Complete!");
                            break;
                    }
                    os.writeObject(request);
                    System.out.println("正在返回数据"+request.getA()+"到客户端");
                    os.flush();
                } catch (IOException ex) {
                    logger.log(Level.SEVERE, null, ex);
                } catch(ClassNotFoundException ex) {
                    logger.log(Level.SEVERE, null, ex);
                } catch (SQLException e) {
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
        }).start();
    }
}
