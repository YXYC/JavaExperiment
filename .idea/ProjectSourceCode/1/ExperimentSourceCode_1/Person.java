package ExperimentSourceCode_1;


public class Person{
    //属性
    private int age=0;
    private String name="noname";//名字
    private char sex='M';//性别
    public Person(){}
    public Person(String n,int a,char s){
        name = n;
        if(a>=0&&a<140) age=a; //数据过滤
        else age=0;
        if(s=='M') sex=s; //数据过滤
        else sex='F';
    }

    //行为或方法
    public void introduceme(){
        System.out.println("my name is:"+name+"\tmt age is:"+age);
        if(sex=='M') System.out.println("I am woman!");
        else System.out.println("I am woman!");
    }
    //查询姓名name
    public String getName(){
        return name;
    }
    //设置姓名name
    public void setName(String n){
        name = n;
    }
    //查询年龄age
    public int getAge(){
        return age;
    }
    //设置年龄age
    public void setAge(int a){
        if(a>=0&&a<140) age =a;
        else age =0;
    }
    //查询性别sex
    public char getSex(){return sex;}
    //设置性别sex
    public void setSex(char s){
        if(s=='M') sex='M';
        else sex='F';
    }
    public boolean equals(Person a){
        if(this.name.equals(a.name)&&this.age==a.age&&this.sex==a.sex)
            return true;
        else
            return false;
    }
    public String toString(){
        return name+"."+sex+","+age;
    }
}

class PersonTest{
    public static void main(String args[]){
        Person p1,p2;
        p1 = new Person("张三",28,'M');
        p2 = new Person();
        p2.setName("陈红");p2.setAge(38);p2.setSex('F');
        p1.introduceme();
        p2.introduceme();
    }
}
