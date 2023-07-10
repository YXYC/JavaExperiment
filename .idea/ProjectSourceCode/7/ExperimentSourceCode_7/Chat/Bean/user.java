package ExperimentSourceCode_7.Chat.Bean;

import java.io.Serializable;

/**
 * 用户:包括账号、密码、昵称
 */
public class user implements Serializable {
    private String username;
    private String password;
    private String name;
    private int isOline;//是否在线

    //构造方法
    public user(String username,String password,String name){
        this.name = name;
        this.password = password;
        this.username = username;
    }

    //get方法

    public String getName() {
        return name;
    }

    public int getIsOline() {
        return isOline;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    //set方法

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public void setIsOline(int isOline) {
        this.isOline = isOline;
    }

    @Override
    public String toString() {
        return "user{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", isOline=" + isOline +
                '}';
    }

}
