package ExperimentSourceCode_9.BANK.BankServer;

import ExperimentSourceCode_9.BANK.BankServer.JDBC.service.BankService;
import ExperimentSourceCode_9.BANK.BankServer.JDBC.service.UserService;
import ExperimentSourceCode_9.BANK.bean.Request;

import java.sql.SQLException;

/**
 * Server_mangge的实例化
 */
public class Server_manageLmpl implements Server_manage{
    @Override
    public Request login_test(Request res) {
        System.out.println("开始实现登陆验证：");
        UserService userService=new UserService();
        System.out.println(res.getUser().getUsername()+res.getUser().getPassword());
        res.setUser(userService.login(res.getUser().getUsername(),res.getUser().getPassword()));
        return res;
    }

    @Override
    public Request find_bank(){
        Request request = new Request();
        System.out.println("开始导出银行用户基本信息:");
        BankService bankService = new BankService();
        request.setBanks(bankService.findAllBanks());
        return request;
    }

    @Override
    public void add_bank(Request request){
        System.out.println("开始进行新用户开户：");
        System.out.println("用户名"+request.getBank().getUsername());
        BankService bankService = new BankService();
        bankService.addBank(request.getBank());
    }

    @Override
    public void modify_bank(Request request){
        System.out.println("正在对用户信息进行修改：");
        BankService bankService = new BankService();
        bankService.modify_bank(request.getBank());
    }

    @Override
    public void delect_bank(Request request){
        System.out.println("正在删除该用户");
        BankService bankService =new BankService();
        bankService.delect(request.getBank());
    }

    @Override
    public Request store(Request request) throws SQLException {
        System.out.println("开始存钱操作");
        BankService bankService =new BankService();
        request.setBank(bankService.store(request.getBank(),request.getB()));
        return request;
    }

    @Override
    public Request take(Request request){
        System.out.println("开始取钱操作");
        //System.out.println(request.getB());
        System.out.println(request.getBank().toString()+request.getB());
        BankService bankService = new BankService();
        request.setBank(bankService.take(request.getBank(),request.getB()));
        System.out.println(request.getBank().toString()+request.getB());
        return request;
    }

    @Override
    public Request login_user(Request res) {
        System.out.println("开始进行用户登陆验证：");
        BankService bankService = new BankService();
        System.out.println(res.getBank().getUsername()+res.getBank().getPassword());
        res.setBank(bankService.login(res.getBank().getUsername(),res.getBank().getPassword()));
        return res;
    }

    @Override
    public double list_bank(Request request) {
        System.out.println("开始进行批量开户");
        BankService bankService =new BankService();
        request.setB(bankService.list_bank(request.getBanks()));
        return request.getB();
    }
}
