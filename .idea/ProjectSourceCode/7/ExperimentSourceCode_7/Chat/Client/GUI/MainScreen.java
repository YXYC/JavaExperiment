package ExperimentSourceCode_7.Chat.Client.GUI;

import ExperimentSourceCode_7.Chat.Bean.Request;
import ExperimentSourceCode_7.Chat.Bean.user;
import ExperimentSourceCode_7.Chat.Client.Connect.ClientConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.System.exit;

public class MainScreen {
    private user User;
    private Request request = new Request(null,null,null);

    static public ArrayList<String> news = new ArrayList<String>();
    static JTextArea outArea = new JTextArea(20, 20);
    private ClientConnect clientConnect;
    public MainScreen(user user) {
        User = user;
    }

    public void init() {

        JFrame frame = new JFrame(User.getName());
        frame.setLayout(new BorderLayout());
        frame.setSize(700,700);
        frame.setLocation(300, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy
                (ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        outArea.setEditable(false);
        scrollPane.setViewportView(outArea);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel label = new JLabel("message");
        JTextField inField = new JTextField(20);
        JButton sentButton = new JButton("发送");
        JButton refreshButton = new JButton("退出");

        panel.add(label);
        panel.add(inField);
        panel.add(sentButton);
        panel.add(refreshButton);

        label.setVisible(true);  // 设置控件可见
        inField.setVisible(true);
        sentButton.setVisible(true);
        panel.setPreferredSize(new Dimension(400, 80));  // 设置面板大小

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.PAGE_END);

        System.out.println("页面接受历史聊天记录");
        HistoryThread history = new HistoryThread();
        ClientConnect connect = new ClientConnect();
        Thread t1 = new Thread(history);
        t1.start();
        connect.ReceiveNews();

        /**
         * 对发送按钮进行监听
         */
        sentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String news = inField.getText();//发送的信息
                inField.setText(null);
                Request request = new Request(User,news,null);
                connect.SendNews(request);//发送消息
            }
        });

        /**
         * 对退出按钮进行监听
         */
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                exit(0);
            }
        });
    }
    public void runChat() {
        SwingUtilities.invokeLater(this::init);
    }

    public static void main(String[] args) {
        user User = new user(null,null,"一夕云");
        MainScreen mainScreen =new MainScreen(User);
        mainScreen.runChat();
    }

}
