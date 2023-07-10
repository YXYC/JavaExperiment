import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.Calendar;
 
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
 
/**
 * 类：时钟~ 自动读取系统时间 并走秒
 * @author Abe
 *
 */
@SuppressWarnings("serial")
public class Clock extends JFrame implements ActionListener {
	private int[] timeNum = new int[3];
	private double[] radian = new double[3];
	private Timer timer;
	private String str = new String();
	private JLabel lbl;
	private Color[] color = { Color.red, Color.BLUE, Color.black };
	private Image offImage = new BufferedImage(400, 500,
			BufferedImage.TYPE_INT_RGB);// 双缓冲
	private JLabel[] lbls = new JLabel[4];
 
	public Clock() {
		this.setTitle("时钟");
		this.setSize(400, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
 
		timer = new Timer(200, this);
		lbl = new JLabel(str); // 显示数字时间
		//Color c = getColor();
		lbl.setBackground(Color.red);	
		lbl.setFont(new Font("Consolas", Font.PLAIN, 37));
		lbl.setBounds(120, 400, 400, 50);
		//lb1.setColor(c);
		this.add(lbl);
		for (int i = 0; i < lbls.length; i++) { // 显示刻度盘的数字
			lbls[i] = new JLabel(0 + 3 * i + "");
			lbls[i].setFont(new Font("Consolas", Font.PLAIN, 25));
			this.add(lbls[i]);
		}
		lbls[0].setBounds(190, 50, 50, 50);
		lbls[1].setBounds(310, 170, 50, 50);
		lbls[2].setBounds(190, 285, 50, 50);
		lbls[3].setBounds(70, 170, 50, 50);
	}
 
	@Override
	public void paint(Graphics g) {
		Graphics newG = offImage.getGraphics();
		super.paint(newG);
		this.draw(newG);
		g.drawImage(offImage, 0, 0, 400, 500, null);
		timer.start();
	}
 
	public void draw(Graphics newG) {
		newG.setColor(Color.black);
		newG.drawOval(50, 70, 300, 300);// 外圆
		Graphics2D g2d = (Graphics2D) newG;
 
		for (int i = 0; i < timeNum.length; i++) { // 指针
			g2d.setStroke(new BasicStroke(1 + i * 2));
			g2d.setColor(color[i]);
			g2d.drawLine(200, 220,
					200 + (int) (Math.sin(radian[i]) * (120 - 20 * i)),
					220 - (int) (Math.cos(radian[i]) * (120 - 20 * i)));
		}
		for (int i = 0; i < 60; i++) { // 刻度盘
			Double radMark = 2 * Math.PI * i / 60;
			int j;
			if (i % 5 != 0) {
				g2d.setStroke(new BasicStroke(1));
				j = 145;
			} else {
				g2d.setStroke(new BasicStroke(5));
				j = 135;
			}
			g2d.drawLine(200 + (int) (Math.sin(radMark) * j),
					220 - (int) (Math.cos(radMark) * j),
					200 + (int) (Math.sin(radMark) * 145),
					220 - (int) (Math.cos(radMark) * 145));
		}
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			Calendar cal = Calendar.getInstance(); // 读取系统时间
			this.timeNum[0] = cal.get(Calendar.SECOND);
			this.timeNum[1] = cal.get(Calendar.MINUTE);
			this.timeNum[2] = cal.get(Calendar.HOUR_OF_DAY);
			DecimalFormat df = new DecimalFormat("00");
			str = df.format(timeNum[2]) + ":" + df.format(timeNum[1]) + ":"
					+ df.format(timeNum[0]);
			lbl.setText(str);
 
			for (int i = 0; i < radian.length; i++) {	// 时间换算成弧度
				if (i < 2) {
					radian[i] = 2 * Math.PI * timeNum[i] / 60;
				} else {								//小时换算弧度时，加上分钟提供的弧度
					radian[i] = 2 * Math.PI
							* (timeNum[i] + timeNum[i - 1] / 60.0) / 12;
				}
			}
			repaint();
		}
	}
 
	public static void main(String[] args) {
		new Clock().setVisible(true);
	}
}
/*
————————————————
版权声明：本文为CSDN博主「Anubies」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/Anubies/article/details/40961533*/