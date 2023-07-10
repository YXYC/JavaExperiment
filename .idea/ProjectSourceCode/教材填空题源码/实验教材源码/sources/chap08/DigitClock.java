import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class DigitClock extends JFrame implements Runnable{
	private static final long serialVersionUID = 1L;
	private JLabel date;
	private JLabel time;
	public DigitClock() {
		//初始化图形界面
		this.setVisible(true);
		this.setTitle("数字时钟");
		this.setSize(282, 176);
		this.setLocation(200, 200);
		this.setResizable(true);
		this.setBackground(new Color(0,0,128));
		JPanel panel = new JPanel();
		panel.setBackground(Color.blue);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		//时间
		time = new JLabel();
		time.setForeground(Color.red);
		time.setBackground(Color.lightGray);
		time.setBounds(31, 54, 196, 59);
		time.setFont(new Font("Arial", Font.PLAIN, 50));
		panel.add(time);
		//日期
		date = new JLabel();
		date.setForeground(Color.red);
		date.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		date.setBounds(47, 10, 180, 22);
		panel.add(date);
	}
	    //用一个线程来更新时间
		 public void run() { 
		 while(true){ 
		 try{ 
			 date.setText(new SimpleDateFormat("yyyy 年 MM 月 dd 日   EEEE").format(new Date()));
			 time.setText(new SimpleDateFormat("HH:mm:ss").format(new Date()));
		 }catch(Throwable t){ 
		  t.printStackTrace(); 
		  } 
		 }
	}
		
	public static void main(String[] args) {
		new Thread(new DigitClock()).start();
	}
}
//https://blog.csdn.net/c_jian/article/details/50505130