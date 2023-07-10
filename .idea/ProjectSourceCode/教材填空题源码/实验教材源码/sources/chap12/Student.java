import java.io.Serializable;
public class Student implements  java.io.Serializable 
{
  private String id;
  private String name;
  private String sex;
  private int age;
  private String dep;

  public Student() {
  }

  public Student(String s1, String s2, String s3, int i4, String s5) {
    id = s1;
    name = s2;
    sex = s3;
    age = i4;
    dep = s5;
  }

  public String getId() {
    return id;
  }

  public void setId(String s1) {
    id = s1;
  }

  public String getName() {
    return name;
  }

  public void setName(String s1) {
    name = s1;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String s1) {
    sex = s1;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int i1) {
    age = i1;
  }

  public String getDep() {
    return dep;
  }

  public void setDep(String s1) {
    dep = s1;
  }
}
