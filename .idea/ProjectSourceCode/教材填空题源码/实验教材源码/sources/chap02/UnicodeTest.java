import java.io.*;
class UnicodeTest {
  public static void main(String[] args){
     int ��='��';
     for(int i=0;i<=20;i++){   //ѭ�����ƽṹ
       System.out.print(" "+��+":"+(char)��);
       ��++;
     }
  }
}