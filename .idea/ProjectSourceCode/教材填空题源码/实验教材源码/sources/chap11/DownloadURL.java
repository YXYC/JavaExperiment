//DownloadURL.java
import java.io.*;
import java.net.*;
class DownloadURL {
   public static void main(String[] args) {
      try{
         URL myurl=new URL(args[0]);//从命令行输入的字符串创建URL对象
         InputStream in=myurl.openStream();//打开URL资源输入流
         FileOutputStream fout=new FileOutputStream("saveurl.dat");
         int ch=in.read();
         while(ch!=-1)  {
            System.out.print((char)ch);
            fout.write(ch);
            ch=in.read();
         }
         in.close();
         fout.close();
      }
      catch(ArrayIndexOutOfBoundsException e){System.out.println("使用格式：java DownloadURL  url");}
      catch(MalformedURLException e1){System.out.println("非法ＵＲＬ");}
      catch(IOException e2){}
   }
}