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
    		sb.append("����������:"+hostname+"\n");
    		sb.append("========================================================\n");
    		Enumeration<NetworkInterface> interfaces=NetworkInterface.getNetworkInterfaces();
			//��ȡ�����б�
    		while(interfaces.hasMoreElements()) {
    			NetworkInterface nic=interfaces.nextElement();
                //��ȡÿ����������Ϣ
    			sb.append("����ӿ�:"+nic.getIndex()+"��������Ϣ---------------------------\n");
    		    sb.append(" �ӿ�����:"+nic.getName()+"\n");
    		    sb.append(" ��ʾ����:"+nic.getDisplayName()+"\n");
    		    //�ӿ�״̬
    		    sb.append(" �ӿ�״̬:");
    		    if(nic.isUp()) sb.append("�Ѿ�����\n");
    		    else sb.append("�ѹر�\n");
    		    //�Ƿ�ػ��ӿ�
    		    sb.append(" �Ƿ�ػ��ӿ�:");
    		    if(nic.isLoopback()) sb.append("�ػ��ӿ�\n");
    		    else sb.append("�ǻػ��ӿ�\n");
    		    //�Ƿ�����ӿ�
    		    if(nic.isVirtual()) sb.append("����ӿ�\n");
    		    else sb.append("����ӿ�\n");
    		    //�ӿ�MTU
    		    sb.append(" �ӿ�MTU:"+nic.getMTU()+"\n");
    		    byte[] macAddr=nic.getHardwareAddress();
    		    if(macAddr!=null) sb.append(" �ӿ�Ӳ����ַ(MAC��ַ):"+DatatypeConverter.printHexBinary(macAddr)+"\n");
    		    else sb.append(" !!�޷���ȡ������Ӳ����ַ\n");
    		    //�ӿ�IP��ַ�б�
    		    sb.append(" IP��ַ�б�:");
    		    Enumeration<InetAddress> ipAddresses=nic.getInetAddresses();
    		    while(ipAddresses.hasMoreElements()) {
    		    	sb.append("\t"+ipAddresses.nextElement().getHostAddress()+"\n");
    		    }
    		    sb.append("\n");
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    		System.out.print("���������쳣 ���޷���ȡ������Ϣ�����鰲ȫ������á�");
    	}
    	System.out.print(sb.toString());
    }
}
