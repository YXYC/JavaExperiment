import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
public class GUIClock {
    Timer timer;
    private JFrame frmswing;
    private JLabel lbTime;
    private JButton btnStart;
    public GUIClock() {
       	frmswing=new JFrame();
    	btnStart=new JButton("开始");
    	lbTime=new JLabel();
    	frmswing.add(lbTime);
    	frmswing.add(btnStart,"South");
    	btnStart.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e){
    			timer.start();
    		}
    	});
    	//创建计时器
    	timer=new Timer(200,new ActionListener() {
    		//定时器固定时间执行的任务
    		public void actionPerformed(ActionEvent arg0){
    	    	lbTime.setText("当前时间："+new Date());
    		}
    	});
    }
	public static void main(String[] args){
	  	EventQueue.invokeLater(new Runnable() {
    		public void run(){
    			try{
    				GUIClock window=new GUIClock();
    				window.frmswing.setSize(300,100);
    				window.frmswing.setVisible(true);
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    	});
    }
}