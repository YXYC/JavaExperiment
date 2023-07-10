package red.star;
public class Triangle {
   double sideA=-1,sideB=-1,sideC=-1;
   String area;
   boolean isTriangle;
   public void setSideA(double a) {
      sideA=a;
   }
   public double getSideA() {
      return sideA;
   }
   public void setSideB(double b) {
      sideB=b;
   }
   public double getSideB() {
      return sideB;
   }
   public void setSideC(double c) {
      sideC=c;
   }
   public double getSideC() {
      return sideC;
   }
   public String getArea() {
      double p=(sideA+sideB+sideC)/2.0;
      if(isTriangle){
         double result= Math.sqrt(p*(p-sideA)*(p-sideB)*(p-sideC));
         area = String.format("%.2f",result);//保留2位小数。
      }
      return area;
   }
   public boolean getIsTriangle(){
      if(sideA<sideB+sideC&&sideB<sideA+sideC&&sideC<sideA+sideB)
         isTriangle=true;
      else   
         isTriangle=false;
      return isTriangle;
   }
}
