package ExperimentSourceCode_9.BANK.BankClient.gui;

import ExperimentSourceCode_9.BANK.BankClient.Client.C_Connect;
import ExperimentSourceCode_9.BANK.BankClient.File.ExportExcel ;
import ExperimentSourceCode_9.BANK.BankClient.Client.File.ExportPdf;
import ExperimentSourceCode_9.BANK.BankClient.File.Input_xls;
import ExperimentSourceCode_9.BANK.BankClient.gui.util.ErrorPrompt ;
import ExperimentSourceCode_9.BANK.bean.Request;
import ExperimentSourceCode_9.BANK.bean.User;
import ExperimentSourceCode_9.BANK.bean.bank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;


@SuppressWarnings("serial")
public class Main_ManageFrame extends JFrame {
    private User user;

    public Main_ManageFrame(User user) {
        // TODO Auto-generated constructor stub
        this.user =user;
        Container container = getContentPane();

        final JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBorder(BorderFactory.createTitledBorder("<html><b><i>" +
                "<u><font face='SansSerif' size='5' color='purple'>Welcome!"+user.getUsername() +
                "</font></u></i></b></html>"));
        Dimension dimension = new Dimension(800, 800);
        layeredPane.setPreferredSize(dimension);

        final JPanel panelBg = new JPanel();
        final Image imageBg = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("Main_Manage.png"));
        ImageIcon imageIcon = new ImageIcon(imageBg.getScaledInstance
                (dimension.width, dimension.height, Image.SCALE_FAST));
        final JLabel bg = new JLabel(imageIcon);
        final Point origin = new Point(10, 30);
        final Rectangle rectangle = new Rectangle(origin, dimension);
        panelBg.setBounds(rectangle);
        panelBg.add(bg);

        final JPanel panelContent = new JPanel();
        JButton button = new JButton("批量开户");
        JButton button2 = new JButton("导出xls文件");
        JButton button3 = new JButton("修改用户信息");
        JButton button1 =new JButton("导出pdf");

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
         * 批量开户
         */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ErrorPrompt("正在读取："+"d://temp//readme.xls");
                new Input_xls("d://temp//readme.xls");
                new ErrorPrompt("批量开户完成！");
            }
        });

        /**
         * 修改用户基本信息
         */
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame MainFrame = null;
                try {
                    MainFrame = new MainFrame();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                MainFrame.setVisible(true);
            }
        });

        /**
         * 导出xls文件
         */
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                C_Connect connect = new C_Connect();
                Request request =new Request();
                request.setA("find_bank");
                try {
                    request= connect.in_out(request);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                List<bank> list = request.getBanks();
                String path = "D:\\temp\\customer.xls";
                System.out.println("开始导出...");
                long s1 = new Date().getTime();
                // 开始导出
                new ExportExcel(list, path);
                long s2 = new Date().getTime();
                long time = s2 - s1;
                new ErrorPrompt("导出完成！消耗时间：" + time + "毫秒");
                new ErrorPrompt("导出到："+path);
                System.out.println("导出完成！消耗时间：" + time + "毫秒");
            }
        });

        /**
         * 导出pdf表单
         */
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String RESULT = "d://temp//year.pdf";
                    try {
                        new ExportPdf().createPdf(RESULT);
                        new ErrorPrompt("已导出到："+RESULT);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
        });
    }



    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        User user = new User();
        user.setUsername("测试君");
        Main_ManageFrame frame = new Main_ManageFrame(user);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
