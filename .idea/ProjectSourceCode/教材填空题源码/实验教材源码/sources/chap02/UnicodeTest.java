import java.io.*;
class UnicodeTest {
  public static void main(String[] args){
     int 中='中';
     for(int i=0;i<=20;i++){   //循环控制结构
       System.out.print(" "+中+":"+(char)中);
       中++;
     }
  }
}