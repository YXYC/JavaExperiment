package ExperimentSourceCode_9.BANK.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 构造银行用户实例
 */
public class bank implements Serializable {
    private String username;
    private String password;
    private String name;
    private String id;
    private String number;
    private String sex;

    private Date brithday;
    private double balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBrithday() {
        return brithday;
    }

    public double getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String toString() {
        return "name:"+name+",id:"+id+",number:"+number+",sex:"+sex+
                ",birthday:"+ brithday+",balance:"+balance;
    }
}
