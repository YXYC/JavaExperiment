package ExperimentSourceCode_9.BANK.BankServer.JDBC.test;

import ExperimentSourceCode_9.BANK.BankServer.JDBC.dao.BankDao;
import ExperimentSourceCode_9.BANK.BankServer.JDBC.impl.BankDaoImpl;
import ExperimentSourceCode_9.BANK.bean.bank;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class TestBankDao {
    private BankDao bankdao;
    @Before
    public void init() {
        bankdao = new BankDaoImpl();
    }

    @Test
    public void testFindAll() {
        List<bank> banks = bankdao.findAll();
        // 判断用户表是否有记录
        if (banks.size() > 0) {
            for(bank bank : banks) {
                System.out.println(bank);
            }
        } else {
            System.out.println("提示：用户表里没有记录~");
        }
    }

    @Test
    public void insert()  {
        bank bank = new bank();
        bank.setName("测试");
        bank.setNumber("1000100010");
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        bank.setBalance(1234);
        bank.setId("320210987432");
        bankdao.insert(bank);
    }
}
