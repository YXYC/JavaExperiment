package ExperimentSourceCode_9.BANK.BankServer.JDBC.dao;
import ExperimentSourceCode_9.BANK.bean.bank;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 银行用户数据访问接口
 */
public interface BankDao {
    List<bank> findAll();
    void insert(bank bank);
    void delect(String id);//因为学号唯一，所以可以通过查找学号删除相应的内容
    void update_name(String name,String id);//修改姓名
    void update_sex(String sex,String id);//修改性别
    void update_birthday(Date birth,String id);//修改出生日期
    void update_number(String number,String id);

    bank store(bank bank, double b) throws SQLException;
    bank take (bank bank,double b);
    bank login(String username, String password);

    double list_bank(List<bank> banks);
}
