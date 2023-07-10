package ExperimentSourceCode_9.BANK.BankServer.JDBC.service;

import ExperimentSourceCode_9.BANK.BankServer.JDBC.dao.BankDao;
import ExperimentSourceCode_9.BANK.BankServer.JDBC.impl.BankDaoImpl;
import ExperimentSourceCode_9.BANK.bean.bank;

import java.sql.SQLException;
import java.util.List;

public class BankService {
    private BankDao bankDao = new BankDaoImpl();
    public List<bank> findAllBanks() {
        return bankDao.findAll();
    }
    public void addBank(bank bank) {
        bankDao.insert(bank);
    }

    public void modify_bank(bank bank){
        //System.out.println(bank.toString());
        bankDao.update_name(bank.getName(), bank.getId());
        bankDao.update_number(bank.getNumber(), bank.getId());
        bankDao.update_sex(bank.getSex(), bank.getId());
        bankDao.update_birthday(bank.getBrithday(), bank.getId());
        //System.out.println(bank.toString());
    }

    public void delect(bank bank){
        bankDao.delect(bank.getId());
    }

    public bank store(bank bank, double b) throws SQLException {
        bank=bankDao.store(bank,b);
        return bank;
    }

    public bank take(bank bank, double b) {
        bank=bankDao.take(bank,b);
        return bank;
    }

    public bank login(String username, String password) {
        return bankDao.login(username, password);
    }

    public double list_bank(List<bank> banks) {
        return bankDao.list_bank(banks);
    }
}
