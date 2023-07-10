import java.util.*;
public class InputDouble {  
    public static void main(String args[]) {
        double a,b,c;
        Scanner reader=new Scanner(System.in);     
        System.out.println("从键盘输入一个浮点数");
        a=reader.nextDouble();   //reader调用方法读取用户从键盘输入的数据，并赋值给a
        b=a*a;
        c=a*a*a;
        System.out.println("b="+b); // 输出b
        System.out.println("c="+c);// 输出c 
    }
}