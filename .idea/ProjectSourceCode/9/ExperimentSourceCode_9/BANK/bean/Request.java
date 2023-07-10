package ExperimentSourceCode_9.BANK.bean;
import java.io.Serializable;
import java.util.List;

/**
 * 服务端与客户端进行消息互通进行Object类统一封装
 *     1.user
 *     2.List<user>
 *     3.bank
 *     4.List<bank>
 *     5.String a
 *     6.String b
 *     7.report
 */
public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    User user;//用户个人信息--Login校验
    List<User> users;
    ExperimentSourceCode_9.BANK.bean.bank bank;//用户个人信息
    List<bank> banks;//用户表
    String a;
    double b;
    report report;//需要传输的年终报表
    List<report> reports;//用户表


    /**
     * 默认构造方法
     */
    public Request() {
        this.a =null;
        this.b =0;
        this.banks =null;
        this.user = null;
        this.bank =null;
        this.report =null;
        this.users=null;
    }

    /**
     * getter 方法
     * @return
     */
    public User getUser() {
        return user;
    }

    public bank getBank() {
        return bank;
    }

    public List<ExperimentSourceCode_9.BANK.bean.report> getReports() {
        return reports;
    }

    public void setReports(List<ExperimentSourceCode_9.BANK.bean.report> reports) {
        this.reports = reports;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<ExperimentSourceCode_9.BANK.bean.bank> getBanks() {
        return banks;
    }

    public String getA() {
        return a;
    }

    public double getB() {
        return b;
    }


    /**
     * setter方法
     */
    public void setUser(User user) {
        this.user = user;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setBank(ExperimentSourceCode_9.BANK.bean.bank bank) {
        this.bank = bank;
    }

    public void setBanks(List<ExperimentSourceCode_9.BANK.bean.bank> banks) {
        this.banks = banks;
    }

    public void setA(String a) {
        this.a = a;
    }

    public void setB(double b) {
        this.b = b;
    }

    public ExperimentSourceCode_9.BANK.bean.report getReport() {
        return report;
    }

    public void setReport(ExperimentSourceCode_9.BANK.bean.report report) {
        this.report = report;
    }
}
