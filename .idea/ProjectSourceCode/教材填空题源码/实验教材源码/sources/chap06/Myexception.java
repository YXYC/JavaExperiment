class Myexception extends Exception  {
     String mymsg="我的异常信息！";
Myexception()
{super("我自己定义的异常");}
     Myexception(String msg){super(msg);}
     public void displayme(){System.out.println(mymsg);}
}
class ExceptionTest  {
      public static void main(String[] args) {
         try {
           if(args[0].charAt(0)=='A')  {
                Myexception e=new Myexception();
                throw e;
           }else{
               System.out.println(args[0]);
           }
         }
          catch(Myexception aaaa)   {
                System.out.println(aaaa.getMessage());
                aaaa.displayme();
          }
      }
}