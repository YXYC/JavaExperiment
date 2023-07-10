package ExperimentSourceCode_7.fill;/*client.java*/
import java.net.*;
import java.io.*;
public class Client {
  public static void main(String args[]) throws Exception {
  Socket soc=new Socket("127.0.0.1",4001); //将target mechine替换为目标机器的ip地址或主机名
  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
  System.out.println("输入消息至服务器");
  String message="";
  String temp;
  PrintStream ps=new PrintStream(soc.getOutputStream());
  while(!((temp=br.readLine()).equals("quit"))){
      ps.println(temp);
  }
  ps.close();
  soc.close();
  }
}