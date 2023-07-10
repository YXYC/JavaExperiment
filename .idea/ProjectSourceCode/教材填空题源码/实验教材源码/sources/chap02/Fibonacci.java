public class Fibonacci {
    public static void main(String[] args) {
        int a=0,b=1,c=0;
        for (int i=1;i<=20;i++){
            System.out.print(" "+b);
            c=a+b;//计算斐波那契数列的第i+1一项
            a=b;//将a赋值为斐波那契数列的第i项
            b=c;// 将b赋值为斐波那契数列的第i+1项
        }
    }
}
