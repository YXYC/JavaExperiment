package net.tcp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.Timer;

public class HostScan {
	boolean bStart=false;
	long totalHost;
	long numOfFinished;
	int MAX_THREADS=50;
	int numOfReachable;
	int timeout=100;
	int count=1;
	Timer timer;
	SimpleDateFormat format;
	long startIp;
	long endIp;
	int percent=0;
	public HostScan() {
		format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		bStart=true;
		initialize();
	}
	public long ipStrToLong(String ipStr) {
		String parts[]=ipStr.split("\\.");
		//根据IP地址的各个字节计算IP地址长整型值
		long value=(long)(Long.valueOf(parts[0])*Math.pow(2, 24)+Long.valueOf(parts[1])*Math.pow(2,16)+Long.valueOf(parts[2])*Math.pow(2, 8)+Long.valueOf(parts[3]));
		return value;
	}
	public String ipLongToStr(long ipLong) {
		int part1=(int)(ipLong/Math.pow(2, 24));
		ipLong-=part1*Math.pow(2, 24);
		int part2=(int)(ipLong/Math.pow(2,16));
		ipLong-=part2*Math.pow(2, 16);
		int part3=(int)(ipLong/Math.pow(2, 8));
		int part4=(int)(ipLong-part3*Math.pow(2, 8));
		return part1+"."+part2+"."+part3+"."+part4;
	}
	public void initialize() {
        Scanner keyin=new Scanner(System.in);
        System.out.print("请输入起始IP地址:");
        startIp=ipStrToLong(keyin.nextLine());
        System.out.print("请输入结束IP地址:");
        endIp=ipStrToLong(keyin.nextLine());
        if(startIp>endIp) {
        	System.out.println("起始IP地址不能比结束IP地址大");
        	System.exit(0);
        }
        totalHost=endIp-startIp;
        numOfFinished=0;
        numOfReachable=0;
        System.out.println("开始扫描！");
        //创建线程池
		ExecutorService pools=Executors.newFixedThreadPool(MAX_THREADS);
		//根据IP地址范围循环扫描主机
		for(long i=startIp;i<endIp;++i) {
			pools.submit(new PingHostThread(i));
		}
		pools.shutdown();
		//创建计时器对象
		timer=new Timer(1000,new ActionListener() {
			public void actionPerformed(ActionEvent args0) {
				if(numOfFinished==totalHost) {
					percent=100;
					timer.stop();
				}else {
					percent=(int)(numOfFinished*100/totalHost);
				}
			}
		});
        timer.start();
	}
	public static boolean isReachable(String ipStr,int count,int timeout) {
		Process pro;
		String pingCmd="ping "+ipStr+" -n "+count+" -w "+timeout;
		try {
			//执行ping命令进程
			pro=Runtime.getRuntime().exec(pingCmd);
			//获取进程输出流
			BufferedReader br=new BufferedReader(new InputStreamReader(pro.getInputStream()));
			StringBuilder sb=new StringBuilder();
			String s;
			//获取所有输出结果
			while((s=br.readLine())!=null) {
				sb.append(s);
			}
			//看输出结果中是否包含"100%",表示所有报文均丢失，主机不可达
			if(sb.toString().contains("100%")) return false;
			return true;
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	class PingHostThread implements Runnable {
		long ip;
		public PingHostThread(long ip) {
			this.ip=ip;
		}
		public void run() {
			String ipStr=ipLongToStr(ip);
			String res;
			if(isReachable(ipStr,count,timeout)) {
				numOfReachable++;
				res="======>主机"+ipStr+"可达("+percent+"%)\n";
			}else {
				res="主机"+ipStr+"不可达("+percent+"%)\n";
			}
			System.out.print(res);
			numOfFinished++;
		}
	}
	public static void main(String[] args) {
		HostScan myapp=new HostScan();
	}
}
