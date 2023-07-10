class SinTable {
  public static void main(String[] args){
     int i;
     for(i=0;i<=90;i++)
       System.out.println("sin("+i+")="+Math.sin(i*Math.PI/180.0));
  }
}