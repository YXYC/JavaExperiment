package ExperimentSourceCode_9.BANK.BankServer;

import ExperimentSourceCode_9.BANK.bean.Request;

import java.sql.SQLException;

public interface Server_manage {
    Request login_test(Request request);//验证登录账户
    Request find_bank();//导出所有银行用户
    void add_bank(Request request);

    void modify_bank(Request request);

    void delect_bank(Request request);

    Request store(Request request) throws SQLException;

    Request take(Request request);

    Request login_user(Request request);

    double list_bank(Request request);
}
