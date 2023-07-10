package ExperimentSourceCode_7.Chat.Client.GUI;

import ExperimentSourceCode_7.Chat.Client.Connect.ClientConnect;

import static java.lang.Thread.sleep;

public class HistoryThread implements Runnable{
    @Override
    public void run() {
        int i=0;
        while(true){
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("---------同步消息中-------");
            if(ClientConnect.GetData!=null){
                String news = "消息记录:";
                for(int k=0;k<ClientConnect.GetData.getHistory().size();k++){
                    news = news+"\n"+ClientConnect.GetData.getHistory().get(k);
                }
                MainScreen.outArea.setText(news);
            i++;
        }
    }
}
}
