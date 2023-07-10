import java.io.*;
import java.util.*;
class CardClass {
    private String name;   //姓名
    private String appellation;    //称呼
    private String department;   //工作单位
    private String phone;   //电话
    private String mobile;  //手机
    private String email;   //邮箱
    public CardClass(){}
    public CardClass(String n,String a,String d,String p,String m,String e){
        name=n;
        appellation=a;
        department=d;
        phone=p;
        mobile=m;
        email=e;
    }
    public String getName(){return name;}
    public String getAppellation(){return appellation;}
    public String getDepartment(){return department;}
    public String getPhone(){return phone;}
    public String getMobile(){return mobile;}
    public String getEmail(){return email;}
    public String toString(){
        StringBuilder str=new StringBuilder("*************************************************\n");
        str.append("   "+department+"\n");
        str.append("            "+name+"    "+appellation+"\n");
        str.append("            电话:"+phone+"\n");
        str.append("            手机:"+mobile+"\n");
        str.append("            邮箱:"+email+"\n");
        str.append("*************************************************\n");
        return str.toString();
    }
    public void writeToFile(){
        FileWriter fout=null;
        try {
            fout=new FileWriter(""+name+".txt");
            String mystr=toString();
            fout.write(mystr,0,mystr.length());
            fout.flush();
            fout.close();
        }catch(IOException e1){e1.printStackTrace();}
    }
    public void inputData(){
        System.out.println("请输入名片信息：");
        Scanner keyin=new Scanner(System.in);
        System.out.print("工作单位:");
        department=keyin.nextLine();
        System.out.print("姓名:");
        name=keyin.nextLine();
        System.out.print("称呼:");
        appellation=keyin.nextLine();
        System.out.print("电话:");
        phone=keyin.nextLine();
        System.out.print("手机:");
        mobile=keyin.nextLine();
        System.out.print("邮箱:");
        email=keyin.nextLine();
    }
    public void display(){
        System.out.println(toString());
    }
}
class CardClassTest{
    public static void main(String[] args){
        CardClass card=new CardClass();
        card.inputData();
        card.display();
        card.writeToFile();
    }
}