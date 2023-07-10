class Shiyan2_3_1{
   public static void main(String[] args){
      long f1=1,f2=1;
      int i;
      for(i=0;i<=20;i=i+2){
         System.out.print("  "+f1);
         System.out.print("  "+f2);
         f1=f1+f2;
         f2=f1+f2;
      }
   }
}