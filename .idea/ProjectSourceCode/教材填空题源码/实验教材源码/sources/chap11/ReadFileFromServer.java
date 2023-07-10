//ReadFileFromServer.java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import javax.swing.*;
public class ReadFileFromServer extends JFrame implements ActionListener {
	JTextArea text;
	JButton button;
    String msg;
	public ReadFileFromServer(){
		setTitle("从服务器读取文件内容");
		text = new JTextArea(12, 20);
		button=new JButton("连接服务器并读取内容");
		button.addActionListener(this);
		add(button,"North");
		add(text,"Center");
		setSize(400,300);
		setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e){
		Socket soc = null;
		  BufferedReader br=null;
		try{
			soc = new Socket("124.70.66.251",4001);//将target mechine替换为目标机器的ip地址或主机名
			br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
			  msg="从服务器读取内容如下：";
			  String temp;
			do{
				     temp=br.readLine();
				     if(temp==null) break;
				     msg=msg+temp+"\n";
			  }while(true);
			  text.setText(msg);
			  br.close();
			  soc.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
	public static void main(String[] args){
		new ReadFileFromServer();
	}
}