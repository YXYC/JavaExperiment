package net.tcp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
public class DNSLookup {
    public static void main(String[] args) {
    	Scanner keyin=new Scanner(System.in);
    	System.out.println("��������������");
    	String hostName=keyin.nextLine();
    	try {
    	InetAddress[]  addresses=InetAddress.getAllByName(hostName);
    	System.out.println("��ѯ�����");
    	System.out.println("������"+hostName);
    	System.out.println("IP��ַ��");
    	for(int i=0;i<addresses.length;i++) {
    		System.out.println(addresses[i].getHostAddress());
    	}
    	}catch(UnknownHostException e1) {
    		System.out.println("DNS ���������ô��󣬻���������ϣ���ѯʧ�ܣ�");
    		return;
    	}
    }
}
