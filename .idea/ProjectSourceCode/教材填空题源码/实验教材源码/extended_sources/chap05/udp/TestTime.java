package net.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class TestTime{
       public static void main(String[] args) throws ParseException, IOException {
           //ʹ�þ�̬������ȡʵ����ֻ�ܸ�ʽ������
           DateFormat df1=DateFormat.getDateInstance();
           //ֻ�ܸ�ʽ��ʱ��
           DateFormat df2=DateFormat.getTimeInstance();
           //��ʽ������ʱ��
           DateFormat df3=DateFormat.getDateTimeInstance();
           //Ҫ��ʽ����Date����
           Date date=new Date();
           //ʹ��format()��ʽ��Date����
           System.out.println(df1.format(date));
           System.out.println(df2.format(date));
           System.out.println(df3.format(date));
           String msg=executeCmd("time 12:00:00");
           System.out.println(msg);
       }
       public static String executeCmd(String command) throws IOException {  
    	    System.out.println("Execute command : " + command);  
    	    Runtime runtime = Runtime.getRuntime();  
    	    Process process = runtime.exec("cmd /c " + command);  
    	    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));  
    	    String line = null;  
    	    StringBuilder build = new StringBuilder();  
    	    while ((line = br.readLine()) != null) {  
    	        System.out.println(line);  
    	        build.append(line);  
    	    }  
    	    return build.toString();  
    	}  
}