package net.tcp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
public class DNSLookup {
    public static void main(String[] args) {
    	Scanner keyin=new Scanner(System.in);
    	System.out.println("请输入主机名：");
    	String hostName=keyin.nextLine();
    	try {
    	InetAddress[]  addresses=InetAddress.getAllByName(hostName);
    	System.out.println("查询结果：");
    	System.out.println("主机："+hostName);
    	System.out.println("IP地址：");
    	for(int i=0;i<addresses.length;i++) {
    		System.out.println(addresses[i].getHostAddress());
    	}
    	}catch(UnknownHostException e1) {
    		System.out.println("DNS 服务器设置错误，或者网络故障，查询失败！");
    		return;
    	}
    }
}
