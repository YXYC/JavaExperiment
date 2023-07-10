package ExperimentSourceCode_9.BANK.BankClient.gui;

import ExperimentSourceCode_9.BANK.BankClient.Client.C_Connect;
import ExperimentSourceCode_9.BANK.BankClient.gui.util.ErrorPrompt;
import ExperimentSourceCode_9.BANK.bean.Request;
import ExperimentSourceCode_9.BANK.bean.bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class Main_UserFrame extends JFrame {
    private bank user;

    public Main_UserFrame(bank user) {
        // TODO Auto-generated constructor stub
        this.user =user;
        Container container = getContentPane();

        final JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBorder(BorderFactory.createTitledBorder("<html><b><i>" +
                "<u><font face='SansSerif' size='5' color='purple'>Welcome!"+user.getName() +
                "</font></u></i></b></html>"));
        Dimension dimension = new Dimension(800, 800);
        layeredPane.setPreferredSize(dimension);

        final JPanel panelBg = new JPanel();
        final Image imageBg = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Main_User.png"));
        ImageIcon imageIcon = new ImageIcon(imageBg.getScaledInstance
                (dimension.width, dimension.height, Image.SCALE_FAST));
        final JLabel bg = new JLabel(imageIcon);
        final Point origin = new Point(10, 30);
        final Rectangle rectangle = new Rectangle(origin, dimension);
        panelBg.setBounds(rectangle);
        panelBg.add(bg);

        final JPanel panelContent = new JPanel();
        JButton button = new JButton("转账");
        JButton button2 = new JButton("存钱/取钱/查询余额");
        JButton button3 = new JButton("修改个人信息");
        JButton button1 =new JButton("销户");

        panelContent.setBounds(rectangle);
        panelContent.setOpaque(false); // 设置为透明
        panelContent.add(button);
        panelContent.add(button2);
        panelContent.add(button3);
        panelContent.add(button1);
        layeredPane.add(panelBg, Integer.valueOf(0));
        layeredPane.add(panelContent, Integer.valueOf(1));

        container.add(layeredPane, BorderLayout.CENTER);




        /**
         * 转账按钮的监听
         */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CountFrame(user);
            }
        });

        /**
         * 存~取~查按钮的实现
         */
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new get_outFrame(user);
            }
        });

        /**
         * 修改账户信息的实现
         */
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new EditFrame(user);
            }
        });

        /**
         * 销户的实现
         */
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Request request = new Request();
                request.setA("delect");
                request.setBank(user);
                C_Connect connect =new C_Connect();
                try {
                    connect.in_out(request);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                new ErrorPrompt("已成功销户！");
                dispose();
            }
        });

    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        bank user = new bank();
        user.setName("测试君");
        Main_UserFrame frame = new Main_UserFrame(user);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
