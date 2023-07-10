package threadexec;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class ShuffleBySwingTimer {
    ArrayList<Integer> list=new ArrayList<Integer>();
    Timer timer;
    private JFrame frmswing;
    private JLabel lblArray;
    private JLabel lblRandomArray;
    private JButton btnStart;
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
    		public void run() {
    			try {
    				ShuffleBySwingTimer window=new ShuffleBySwingTimer();
    				window.frmswing.setSize(400,300);
    				window.frmswing.setVisible(true);
    			}catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    	});
    }
    public ShuffleBySwingTimer() {
    	initialize();
    }
    private void initialize() {
    	frmswing=new JFrame();
    	btnStart=new JButton("开始");
    	lblArray=new JLabel();
    	lblRandomArray=new JLabel();
    	frmswing.add(lblArray,"North");
    	frmswing.add(lblRandomArray);
    	frmswing.add(btnStart,"South");
    	btnStart.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			timer.start();
    		}
    	});
    	for(int i=0;i<10;i++) {
    		int v=100+i*10;
    		list.add(v);
    	}
    	lblArray.setText("原始数据："+listToStr(list));
    	//创建计时器
    	timer=new Timer(200,new ActionListener() {
    		//定时器固定时间执行的任务
    		public void actionPerformed(ActionEvent arg0) {
    			Collections.shuffle(list);
    			lblRandomArray.setText("随机乱序："+listToStr(list));
    		}
    	});
    }
    public String listToStr(List list) {
    	StringBuilder sb=new StringBuilder();
    	for(int i=0;i<list.size();i++) {
    		sb.append(list.get(i)+" ");
    	}
    	return sb.toString();
    }
}
