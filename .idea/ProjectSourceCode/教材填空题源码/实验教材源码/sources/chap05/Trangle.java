package  www.horsefly;
public class Trangle  {
  double sideA,sideB,sideC;
   boolean flag;
   public Trangle(double a,double b,double c)  {
     sideA=a;sideB=b;sideC=c;
     if(a+b>c&&a+c>b&&c+b>a)  {
        System.out.println("����һ��������");
        flag=true;
     }  else  { 
        System.out.println("�Ҳ���һ��������");
        flag=false;
     }
   }
 public void jsmj () {    
       if(flag)  {
          double p=(sideA+sideB+sideC)/2.0;
          double area=Math.sqrt(p*(p-sideA)*(p-sideB)*(p-sideC)) ;
          System.out.println("��һ��������,�ܼ������");
          System.out.println("�����:"+area);
        }  else { 
           System.out.println("����һ��������,���ܼ������");
        }
   } 
 public void set(double a, double b, double c) { 
     sideA=a;sideB=b;sideC=c;
     if(a+b>c&&a+c>b&&c+b>a) { 
        flag=true;
     }  else  { 
        flag=false;
     }
   }
}