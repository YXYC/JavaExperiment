import java.net.*;
class EncodeTest {
  public static void main(String args[]) throws Exception{
    String str = "Jackson's bike-bell cost $5 中国";
    String str2 = URLEncoder.encode(str,"UTF-8");
    System.out.println(str);
    System.out.println(str2);
    String str3=URLDecoder.decode(str2,"UTF-8");
    System.out.println(str3);
  }
}