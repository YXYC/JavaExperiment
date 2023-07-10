import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.event.*;
class minibrowser extends JFrame implements ActionListener,Runnable{
	JButton button;
	URL url;
	JTextField text;
	JEditorPane editPane;
	byte b[]=new byte[128];
	Thread thread;
	public minibrowser(){
		text=new JTextField(20);
		editPane=new JEditorPane();
		editPane.setEditable(false);
		button=new JButton("确定");
		button.addActionListener(this);
		thread=new Thread(this);
		JPanel p=new JPanel();
		p.add(new JLabel("输入网址："));
		p.add(text); p.add(button);
		Container con=getContentPane();
		con.add(new JScrollPane(editPane),BorderLayout.CENTER);
		con.add(p,BorderLayout.NORTH);
		setBounds(60,60,560,460);
		setVisible(true);
		validate();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		editPane.addHyperlinkListener(new HyperlinkListener(){
			public void hyperlinkUpdate(HyperlinkEvent e){
				if (e.getEventType()==HyperlinkEvent.EventType.ACTIVATED){
					try{
						editPane.setPage(e.getURL());
					}catch(IOException e1){
						editPane.setText(""+e1);
					}
				}
			}
		});
	}
	public void actionPerformed(ActionEvent e){
		if (!(thread.isAlive())) thread=new Thread(this);
		try{
			thread.start();
		}catch(Exception e1){
			text.setText("我正在读取"+url);
		}
	}
	public void run(){
		try{
			int n=-1;
			editPane.setText(null);
			editPane.setContentType("text/html");
			url=new URL(text.getText().trim());
			editPane.setPage(url);
		}catch(MalformedURLException e1){
			text.setText(""+e1);
			return;
		}catch(IOException e2){
			text.setText(""+e2);
			return;
		}
	}
	public static void main(String[] args){
		new minibrowser();
	}
}

/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.regex.*;
import javax.swing.event.*;
class MyBrowser extends JFrame implements ActionListener,Runnable{
	JButton button;
	URL url,newURL;
	JTextField text;
	JEditorPane editPane;
	byte b[]=new byte[2048];
	Thread thread;
	Container con=null;
	JPanel p;
	public MyBrowser(){
		text=new JTextField(20);
		editPane=new JEditorPane();
		editPane.setEnabled(false);
		button=new JButton("确定");
		button.addActionListener(this);
		thread=new Thread(this);
		p=new JPanel();
		p.add(new JLabel("输入网址："));
		p.add(text);
		p.add(button);
		con=getContentPane();
		con.add(new JScrollPane(editPane),BorderLayout.CENTER);
		con.add(p,BorderLayout.NORTH);
		setSize(800,600);
		setVisible(true);
		validate();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e){
		if (!(thread.isAlive()))
			thread=new Thread(this);
		try{
			thread.start();
		}catch(Exception ee){
			text.setText("我正在读取"+url);
		}
	}
	public void run(){
		try{
			int m=-1;
			editPane.setText(null);
			url=new URL(text.getText());//使用构造方法URL(String s)创建url，期中参数s由text中的文本指定
			InputStream in=url.openStream();
			File file=new File("temp.html");
			ByteArrayOutputStream write=new ByteArrayOutputStream();
			while((m=in.read(b))!=-1){
				write.write(b,0,m);
			}
			write.close();
			in.close();
			byte content[]=write.toByteArray();
			String str=new String(content);
			Pattern pattern;
			Matcher match;
			pattern=Pattern.compile("<a href=http://.*>.*</a>",Pattern.CASE_INSENSITIVE);
			match=pattern.matcher(str);
			FileOutputStream out=new FileOutputStream(file);
			while (match.find()){
				String strHREF=match.group();
				strHREF="<BR>"+strHREF;
				out.write(strHREF.getBytes());
			}
			out.close();
			//newURL=file.toURI().toURL();
			newURL=file.toURL();
			con.removeAll();
			editPane=new JEditorPane();
			editPane.setEnabled(false);
			editPane.setPage(newURL);
			con.add(p,BorderLayout.NORTH);
			con.add(new JScrollPane(editPane),BorderLayout.CENTER);
			con.validate();
			editPane.addHyperlinkListener(new HyperlinkListener(){
				public void hyperlinkUpdate(HyperlinkEvent e){
					if (e.getEventType()==HyperlinkEvent.EventType.ACTIVATED){
						try{
							URL linkURL=e.getURL();
							editPane.setPage(linkURL);
						}catch(IOException e1){
							editPane.setText(""+e1);
						}
					}
				}
			});
		}catch(MalformedURLException e1){
			text.setText(""+e1);
			return;
		}catch(IOException e1){
			text.setText(""+e1);
			return;
		}
	}
	public static void main(String[] args){
		new MyBrowser();
	}
}*/