package net.tcp;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import javax.xml.bind.DatatypeConverter;

public class NetworkInterfaceInfo {
    public static void main(String[] args) {
    	StringBuilder sb=new StringBuilder();
    	try {
    		String hostname=InetAddress.getLocalHost().getHostName();
    		sb.append("本地主机名:"+hostname+"\n");
    		sb.append("========================================================\n");
    		Enumeration<NetworkInterface> interfaces=NetworkInterface.getNetworkInterfaces();
			//获取网卡列表
    		while(interfaces.hasMoreElements()) {
    			NetworkInterface nic=interfaces.nextElement();
                //获取每个网卡的信息
    			sb.append("网络接口:"+nic.getIndex()+"的配置信息---------------------------\n");
    		    sb.append(" 接口名称:"+nic.getName()+"\n");
    		    sb.append(" 显示名称:"+nic.getDisplayName()+"\n");
    		    //接口状态
    		    sb.append(" 接口状态:");
    		    if(nic.isUp()) sb.append("已经激活\n");
    		    else sb.append("已关闭\n");
    		    //是否回环接口
    		    sb.append(" 是否回环接口:");
    		    if(nic.isLoopback()) sb.append("回环接口\n");
    		    else sb.append("非回环接口\n");
    		    //是否虚拟接口
    		    if(nic.isVirtual()) sb.append("虚拟接口\n");
    		    else sb.append("物理接口\n");
    		    //接口MTU
    		    sb.append(" 接口MTU:"+nic.getMTU()+"\n");
    		    byte[] macAddr=nic.getHardwareAddress();
    		    if(macAddr!=null) sb.append(" 接口硬件地址(MAC地址):"+DatatypeConverter.printHexBinary(macAddr)+"\n");
    		    else sb.append(" !!无法获取该网卡硬件地址\n");
    		    //接口IP地址列表
    		    sb.append(" IP地址列表:");
    		    Enumeration<InetAddress> ipAddresses=nic.getInetAddresses();
    		    while(ipAddresses.hasMoreElements()) {
    		    	sb.append("\t"+ipAddresses.nextElement().getHostAddress()+"\n");
    		    }
    		    sb.append("\n");
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    		System.out.print("程序运行异常 ，无法获取网络信息，请检查安全软件配置。");
    	}
    	System.out.print(sb.toString());
    }
}
