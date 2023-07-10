import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Scanner;
public class TestCharset {
    public static void main(String[] args) {
    	System.out.print("请输入要编码的字符串:");
    	Scanner keyin=new Scanner(System.in);
    	String inputstr=keyin.nextLine();
    	Charset cs=Charset.forName("GBK");
    	ByteBuffer buffer=cs.encode(inputstr);
    	String hexStr="";
    	while(buffer.remaining()>0){
    		hexStr+=Integer.toHexString(buffer.get()&0xFF).toUpperCase()+" ";
    	}
    	System.out.println("GBK编码结果:"+hexStr);
 /******************************************************/   	
    	hexStr=new String();
    	try{
    		byte[] bytes=inputstr.getBytes("UTF-8");
    		for(int i=0;i<bytes.length;i++){
    			hexStr+=Integer.toHexString(bytes[i]&0xFF).toUpperCase()+" ";
    		}
    	}catch(UnsupportedEncodingException e) {
    		e.printStackTrace();
    	}finally {
    		System.out.println("UTF8编码结果:"+hexStr);
    	}  	
/******************************************************/    	
    	System.out.print("请输入要解码的十六进制码串:");
    	String inputCode=keyin.nextLine();
    	String[] strs=inputCode.split(" ");
    	byte[] array=new byte[strs.length];
    	for(int i=0;i<strs.length;i++){
    		array[i]=(byte)(Integer.valueOf(strs[i],16).intValue());
    	}
    	cs=Charset.forName("GBK");
    	CharBuffer cbuffer=cs.decode(ByteBuffer.wrap(array));
    	System.out.println("GBK解码结果:"+cbuffer.toString());
/******************************************************/
    	String str="";
    	try{
    		str=new String(array,"UTF-8");
    	}catch(UnsupportedEncodingException e1) {
    		e1.printStackTrace();
    	}finally {
    		System.out.println("UTF8解码结果："+str);
    	}
    }
}
