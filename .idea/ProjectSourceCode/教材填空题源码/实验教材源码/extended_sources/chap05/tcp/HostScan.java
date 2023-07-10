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
		//����IP��ַ�ĸ����ֽڼ���IP��ַ������ֵ
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
        System.out.print("��������ʼIP��ַ:");
        startIp=ipStrToLong(keyin.nextLine());
        System.out.print("���������IP��ַ:");
        endIp=ipStrToLong(keyin.nextLine());
        if(startIp>endIp) {
        	System.out.println("��ʼIP��ַ���ܱȽ���IP��ַ��");
        	System.exit(0);
        }
        totalHost=endIp-startIp;
        numOfFinished=0;
        numOfReachable=0;
        System.out.println("��ʼɨ�裡");
        //�����̳߳�
		ExecutorService pools=Executors.newFixedThreadPool(MAX_THREADS);
		//����IP��ַ��Χѭ��ɨ������
		for(long i=startIp;i<endIp;++i) {
			pools.submit(new PingHostThread(i));
		}
		pools.shutdown();
		//������ʱ������
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
			//ִ��ping�������
			pro=Runtime.getRuntime().exec(pingCmd);
			//��ȡ���������
			BufferedReader br=new BufferedReader(new InputStreamReader(pro.getInputStream()));
			StringBuilder sb=new StringBuilder();
			String s;
			//��ȡ����������
			while((s=br.readLine())!=null) {
				sb.append(s);
			}
			//�����������Ƿ����"100%",��ʾ���б��ľ���ʧ���������ɴ�
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
				res="======>����"+ipStr+"�ɴ�("+percent+"%)\n";
			}else {
				res="����"+ipStr+"���ɴ�("+percent+"%)\n";
			}
			System.out.print(res);
			numOfFinished++;
		}
	}
	public static void main(String[] args) {
		HostScan myapp=new HostScan();
	}
}
