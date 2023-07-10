class People {
   protected double weight,height;
   public void speakHello( ) {
       System.out.println("Who am I?");
   }  
  public void averageHeight() { 
      height=173;
      System.out.println("average height:"+height);
   }
  public void averageWeight() {
      weight=70;
      System.out.println("average weight:"+weight);
   }

}
class ChinaPeople extends People{  
    public void speakHello(){// 重写speakHello( )方法，要求输出类似“哈唠，我是中国人！” 
         System.out.println("哈唠，我是中国人");
    }
    public void averageHeight(){ // 重写averageHeight( )方法，要求输出类似 “我们中国人的平均身高：168.78厘米” 这样的信息
         System.out.println("我们中国人的平均身高：168.78厘米");
    }
    public void averageWeight(){ // 重写averageWeight( )方法， 要求输出类似“我们中国人的平均体重：65公斤”这样的信息
         System.out.println("我们中国人的平均体重：65公斤");
    }
    public void chinaGongfu () {
        System.out.println("坐如钟,站如松,睡如弓");// 输出类似"坐如钟,站如松,睡如弓"。
    }
}
class AmericanPeople  extends People {
    public void speakHello(){ //重写speakHello()方法，要求输出类似“Hello，I am America!”这样的信息
        System.out.println("Hello，I am America!");
    }
    public void averageHeight(){// 重写averageHeight( )方法
        System.out.println("We americans average height is 175 cm");
    }
    public void averageWeight(){// 重写averageWeight( )方法
        System.out.println("we americans average weight is 86.73 kg");
    }
    public void americanBoxing() {
        System.out.println("The straight, hook"); // 输出类似 “直拳，钩拳”。
    }
}
class BeijingPeople extends ChinaPeople { 
    public void speakHello(){// 重写speakHello( )方法，要求输出类似“您好，俺是中国的北京人”这样的信息
        System.out.println("你好，我是中国的北京人");
    }
    public void beijingOpera() {
         System.out.println("北京特色：京剧");  // 输出”北京特色：京剧”的信息
    }
}
class PeopleTest  {
  public static void main(String args[])  {
      ChinaPeople chinaPeople=new ChinaPeople( );
      AmericanPeople americanPeople=new AmericanPeople( );
      BeijingPeople beijingPeople=new BeijingPeople( );
      chinaPeople.speakHello( );
      americanPeople.speakHello( );
      beijingPeople.speakHello( );
      chinaPeople.averageHeight( );
      americanPeople.averageHeight( );
      beijingPeople.averageHeight( );
      chinaPeople.averageWeight( );
      americanPeople.averageWeight( );
      beijingPeople.averageWeight( );
      chinaPeople.chinaGongfu( );
      americanPeople.americanBoxing( );
      beijingPeople.beijingOpera( ) ;
      beijingPeople.chinaGongfu( );
  }  
}
