package ExperimentSourceCode_7.Chat.Bean;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {
    /**
     * 服务端与客户端进行消息互通进行Object类统一封装
     * 1.用户信息（user）
     * 2.消息（String）
     */
    private user User;
    private String news;
    private ArrayList<String> history;
    private boolean isSended = false; //每条消息只能发送一次

    @Serial
    private static final long serialVersionUID = 1L;

    public Request(user User, String news,ArrayList<String> history){
        this.news = news;
        this.User = User;
        this.history = history;
    }

    //get方法

    public user getUser() {
        return User;
    }

    public String getNews() {
        return news;
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public boolean isSended() {
        return isSended;
    }

    //set方法
   public void setSended(boolean isSended){
        this.isSended = isSended;
    }

    public void setHistory(ArrayList<String> history) {
        this.history = history;
    }

    public void setUser(user user) {
        User = user;
    }

    public void setNews(String news) {
        this.news = news;
    }

    @Override
    public String toString() {
        return "Request{" +
                "User=" + User +
                ", news='" + news + '\'' +
                ", history=" + history +
                '}';
    }
}