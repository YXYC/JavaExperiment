package net.heart;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerHeart extends Thread {
	private ServerSocket server = null;
	private static Integer ordinal = 0;
	private static Map<Integer, Socket> map = new HashMap<>();
	Object obj = new Object();

	public ServerHeart() {
		try {
			server = new ServerSocket(9090);
			while (true) {
				Socket aclient = server.accept();
				aclient.setSoTimeout(10000);
				ordinal++;
				map.put(ordinal, aclient);
				if (ordinal == 1)
					this.start();
				synchronized (obj) {
					new Thread(new ServerBilocationHeart(ordinal, aclient)).start();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				int clients = map.size();
				for (int i = 1; i <= clients; i++) {
					Socket soc = map.get(i);
					if (soc != null) {
						System.out.println("第" + i + "个客户还活着！客户的ip地址=" + soc.getInetAddress());
						ObjectOutputStream out = new ObjectOutputStream(soc.getOutputStream());
						out.writeObject("Hello,你还活着！");// 可以给活着的客户发送心跳包，此处略
					}
				}
				Thread.sleep(4000);
			} catch (Exception e) {
				e.printStackTrace();
				// break;
			}
		}
	}

	class ServerBilocationHeart implements Runnable {
		Integer no;
		Socket client;

		public ServerBilocationHeart(Integer no, Socket client) {
			this.no = no;
			this.client = client;
		}

		@Override
		public void run() {
			try {
				while (true) {
					ObjectInput in = new ObjectInputStream(client.getInputStream());
					Object heartbeat = in.readObject();// 模拟心跳
					// System.out.println("收到第" + no + "客户发来的心跳消息:" + heartbeat);
				}
			} catch (Exception e) {
				// e.printStackTrace();
				synchronized (map) {
					map.put(no, null);
				}
				System.out.println("第" + no + "个客户失去联系！");
				try {
					client.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("开始检测客户端是否在线...");
		ServerHeart server = new ServerHeart();
	}
}