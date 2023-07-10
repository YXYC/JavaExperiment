import java.io.*;
public class RandomTest {   
   public static void main(String args[])
   {  
     File f=new File("RandomTest.java");
     try{  
        RandomAccessFile random=new RandomAccessFile(f,"r"); // 创建指向文件f的random对象
        long l=random.length();
        char ch;
        for(long i=l;i>=0;i--){
    
           random.seek(i);
           ch=(char)random.read();
           System.out.print(ch);
        }
        random.close();
     }catch(Exception e){System.out.println("IOError!");}
   }
}

