package net.heart;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

public class ClientHeart extends Thread {
	private Socket socket;

	public ClientHeart() {
		try {
			socket = new Socket(InetAddress.getLocalHost(), 9090);
			String ip = InetAddress.getLocalHost().toString();
			this.start();
			while (true) {
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				String heartbeat = "�ҵ�ip��ַ=" + ip + ",my time=" + new Date().getTime();
				out.writeObject(heartbeat); // ģ������
				out.flush();
				//System.out.println("������..." + heartbeat);
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	@Override
	public void run() {
		try {
			while(true) {
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				Object recheart=in.readObject();
				System.out.println("�յ�����������������"+recheart);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ClientHeart client = new ClientHeart();
	}
}