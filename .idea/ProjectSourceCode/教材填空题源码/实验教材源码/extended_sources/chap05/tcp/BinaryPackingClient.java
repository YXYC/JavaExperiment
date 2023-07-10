package net.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BinaryPackingClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        final short TYPE_REQUEST=1;
        int pktLen;
        int seq;
        long timeStamp;
        //��Ϣ����
        String msg="��ã�����TCPͨ��";
        String servIp="127.0.0.1";
        int servPort=8999;
        Socket tcpClient=null;
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("TCP���������ݴ���ͻ���\n");
        //�����׽���
        try {
        	tcpClient=new Socket("127.0.0.1",servPort);
        	System.out.printf(" %s �������� %s:%d �ӳɹ�������\n",
        			format.format(new Date()),servIp,servPort);
        }catch(UnknownHostException e) {
        	e.printStackTrace();
        	System.exit(0);
        }catch(IOException e) {
        	e.printStackTrace();
        	System.exit(1);
        }
        seq=(int)Math.random()%Integer.MAX_VALUE;
        timeStamp=new Date().getTime();
        pktLen=4+4+8+msg.getBytes().length; //���㱨���ֽڳ���
        System.out.println("\n��Ҫ���͵��������£�\n�������ͣ�short����"+TYPE_REQUEST
        		+"\n���ݳ���(int):"+pktLen
        		+"\n��ţ�int����"+seq
        		+"\n������ʱ�����long����"+timeStamp
        		+"\n��Ϣ���ݣ�byte[]����"+msg+"\n");
        OutputStream os;
        try {
        	//��ȡ�����
        	os=tcpClient.getOutputStream();
        	DataOutputStream dos=new DataOutputStream(new BufferedOutputStream(os));
        	dos.writeShort(TYPE_REQUEST);
        	dos.writeInt(pktLen);
        	dos.writeInt(seq);
        	dos.writeLong(timeStamp);
        	dos.write(msg.getBytes());
        	dos.flush();
        	System.out.printf("%s ���ݷ��ͳɹ�����%d�ֽڡ�", format.format(new Date()),dos.size());
        	//��ʼ���շ�����
        	InputStream is=tcpClient.getInputStream();
        	DataInputStream dis=new DataInputStream(new BufferedInputStream(is));
        	//��ȡ����
        	int response=dis.readInt();
        	System.out.printf("\n%s ������%s:%d��Ӧ����Ϊ��%s\n", format.format(new Date()),servIp,servPort,response);
        	if(response==400) {
        		System.out.println("������ʱ��ȷ�����Ҫ�졣����");
        	}else if(response==200) {
        		System.out.println("������ʱ��ȷ�����Ҫ��������");
        	}
        	tcpClient.close();
        	System.out.println(format.format(new Date())+"�ͻ��˹ر�TCP����...");
        }catch(IOException e) {
        	e.printStackTrace();
        }
	}

}
