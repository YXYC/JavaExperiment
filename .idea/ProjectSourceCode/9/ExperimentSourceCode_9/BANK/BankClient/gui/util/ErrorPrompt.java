package ExperimentSourceCode_9.BANK.BankClient.gui.util;

import javax.swing.*;
import java.awt.*;

public class ErrorPrompt extends JFrame {
    Container container = getContentPane();

    public ErrorPrompt(String reason) {
        container.setLayout(null);
        setWindow();
        setContent(reason);
    }

    private void setWindow() {
        this.setVisible(true);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //关闭当前窗口
        this.setTitle("提示");
    }

    private void setContent(String reason) {
        JLabel jLabel = new JLabel(reason);
        jLabel.setBounds(70, 30, 340, 80);
        jLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        jLabel.setForeground(Color.red);    //设置字体为红色
        container.add(jLabel);
    }

}
