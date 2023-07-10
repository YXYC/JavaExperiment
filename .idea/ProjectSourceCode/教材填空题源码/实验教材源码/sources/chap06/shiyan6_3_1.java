import java.util.Scanner;
public class shiyan6_3_1 {
  public static void main(String []args) {
      int x=0,y=0,result=0;
      char op;
      if(args.length>3) {
          throw new IllegalArgumentException();
      }
      try {
          x=Integer.parseInt(args[0]);
          y=Integer.parseInt(args[2]);
          op=args[1].charAt(0);
          switch(op){
            case '+': result=x+y;break;
            case '-': result=x-y;break;
            case '*': result=x*y;break;
            case '/': result=x/y;break;
            default:System.out.println("Error op!");
          }
          System.out.println("result="+result);  
      }catch(ArithmeticException e1) {
          System.out.println("捕获到了数学类异常,除数不能为0");
          Scanner in=new Scanner(System.in);
          System.out.println("请重新输入除数：");
          y=in.nextInt();
          result=x/y;
          System.out.println("result="+result);
      }catch(ArrayIndexOutOfBoundsException e2) {
          System.out.println("您忘掉了命令行参数");
      }catch(NumberFormatException e3) {
          System.out.println("数字格式异常");
      }catch(IllegalArgumentException e4) {
          System.out.println("参数数目太多");
      }
      finally  {
          System.out.println("不管是否有异常,总是要执行");
      }
   }
}