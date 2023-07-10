package ExperimentSourceCode_3.cal;

import java.util.Scanner;

public class CAL_ML {
    int a, b;
    char t;


    public CAL_ML(String a, String b, String t) {
        Scanner sc = new Scanner(System.in);
        try {
            this.a = Integer.valueOf(a);
            this.b = Integer.valueOf(b);
            this.t = t.charAt(0);
        } catch (NumberFormatException e) {
            System.out.println("出现输入参数无法转化为数字异常，请重新输入除数与被除数：");
            this.a = sc.nextInt();
            this.b = sc.nextInt();
            this.t = t.charAt(0);
        }//可能出现无法转化为数字的异常,出现即重新转化
    }//输入字符串转换过程

    public int cal() {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        try {
            while (t != '+' && t != '-' && t != '*' && t != '/') {
                System.out.println("输入不是运算符号(+、-、*、/)!" + "\n" + "重新输入运算符号：");
                t = sc.next().charAt(0);
            }
            switch (t) {
                case '+':
                    result = a + b;
                    break;
                case '-':
                    result = a - b;
                    break;
                case '*':
                    result = a * b;
                    break;
                case '/':
                    result = a / b;
                    break;
            }
        } catch (ArithmeticException e) {
            while (b == 0) {
                System.out.println("出现除数为0的异常" + "\n" + "请重新输入除数：");
                b = sc.nextInt();
                result = a / b;
            }
            return result;
        }
        return result;
    }//计算方法
}
class CalTest{
    public static void main(String[] args) {
        String a, b, t;
        Scanner sc = new Scanner(System.in);
        try {
            CAL_ML cal_ml = new CAL_ML(args[0], args[1], args[2]);
            System.out.println("result=" + cal_ml.cal());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("命令行传来参数个数异常" + "\n" + "请重新输入两个操作数和操作符：");
            a = sc.next();
            b = sc.next();
            t = sc.next();
            CAL_ML cal_ml = new CAL_ML(a, b, t);
            System.out.println("result=" + cal_ml.cal());
        } finally {
            System.out.print("不管是否异常，总是要执行");
        }
    }
}

