import java.util.*;
public class GuessNumber {     
     public static void main (String args[ ]) { 
           System.out.println("����һ��1��100֮�������,��²������");
           int realNumber=(int)(Math.random()*100)+1;
           int myGuess=0;
           int guessCount=1;
           Scanner reader=new Scanner(System.in);     
           System.out.println("�������Ĳ²�:");
           myGuess=reader.nextInt(); //�Ӽ�������һ������ 
      while (myGuess!=realNumber){  // ѭ������
         if (myGuess>realNumber){  // ��������
               System.out.println("�´���,���ٲ�:");
              myGuess=reader.nextInt(); //�ٴӼ�������һ������
           }
         else if(myGuess<realNumber){  // ��������
               System.out.println("��С��,���ٲ�:");
               myGuess=reader.nextInt();   //�ٴӼ�������һ������
           }
           guessCount++;
       }
      if(guessCount<4)
      System.out.println("��̫�����ˣ���Ȼ��ô��Ͳ¶���!");
  else if(guessCount>8)
      System.out.println("ҪŬ��ѧϰŶ���´�ϣ���¶ԵĴ�����һ��");
  else
      System.out.println("��������");
   }
}
