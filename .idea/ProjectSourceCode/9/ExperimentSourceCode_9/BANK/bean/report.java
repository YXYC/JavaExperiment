package ExperimentSourceCode_9.BANK.bean;

import java.io.Serializable;

/**
 * 年终报表对象
 */
public class report implements Serializable {
    private int year;//当前年份
    private int count;//当前账户总数
    private double all_balance;//当前账户余额
    private int newuser;//新开户数
    private  int dieuser;//销户数

    public double getAll_balance() {
        return all_balance;
    }

    public int getCount() {
        return count;
    }

    public int getDieuser() {
        return dieuser;
    }

    public int getNewuser() {
        return newuser;
    }

    public int getYear() {
        return year;
    }

    public void setAll_balance(double all_balance) {
        this.all_balance = all_balance;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setDieuser(int dieuser) {
        this.dieuser = dieuser;
    }

    public void setNewuser(int newuser) {
        this.newuser = newuser;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "report{" +
                "year=" + year +
                ", count=" + count +
                ", all_balance=" + all_balance +
                ", newuser=" + newuser +
                ", dieuser=" + dieuser +
                '}';
    }
}
