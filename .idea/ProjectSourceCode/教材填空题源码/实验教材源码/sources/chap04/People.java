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
    public void speakHello(){// ��дspeakHello( )������Ҫ��������ơ����룬�����й��ˣ��� 
         System.out.println("���룬�����й���");
    }
    public void averageHeight(){ // ��дaverageHeight( )������Ҫ��������� �������й��˵�ƽ����ߣ�168.78���ס� ��������Ϣ
         System.out.println("�����й��˵�ƽ����ߣ�168.78����");
    }
    public void averageWeight(){ // ��дaverageWeight( )������ Ҫ��������ơ������й��˵�ƽ�����أ�65�����������Ϣ
         System.out.println("�����й��˵�ƽ�����أ�65����");
    }
    public void chinaGongfu () {
        System.out.println("������,վ����,˯�繭");// �������"������,վ����,˯�繭"��
    }
}
class AmericanPeople  extends People {
    public void speakHello(){ //��дspeakHello()������Ҫ��������ơ�Hello��I am America!����������Ϣ
        System.out.println("Hello��I am America!");
    }
    public void averageHeight(){// ��дaverageHeight( )����
        System.out.println("We americans average height is 175 cm");
    }
    public void averageWeight(){// ��дaverageWeight( )����
        System.out.println("we americans average weight is 86.73 kg");
    }
    public void americanBoxing() {
        System.out.println("The straight, hook"); // ������� ��ֱȭ����ȭ����
    }
}
class BeijingPeople extends ChinaPeople { 
    public void speakHello(){// ��дspeakHello( )������Ҫ��������ơ����ã������й��ı����ˡ���������Ϣ
        System.out.println("��ã������й��ı�����");
    }
    public void beijingOpera() {
         System.out.println("������ɫ������");  // �����������ɫ�����硱����Ϣ
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
