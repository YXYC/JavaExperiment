package chap09.poi;
public class Student {
	 
	  private String name;
	  private int age;
	  private String grade;
	  private int score;
	 
	  public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Student() {
	  }
	 
	  public Student(String name, int age, String grade,int score) {
	    super();
	    this.name = name;
	    this.age = age;
	    this.grade = grade;
	    this.score=score;
	  }
	 
	  public String getName() {
	    return name;
	  }
	 
	  public void setName(String name) {
	    this.name = name;
	  }
	 
	  public int getAge() {
	    return age;
	  }
	 
	  public void setAge(int age) {
	    this.age = age;
	  }
	 
	  public String getGrade() {
	    return grade;
	  }
	 
	  public void setGrade(String grade) {
	    this.grade = grade;
	  }
	 
	  @Override
	public String toString() {
		return "Student[" + name + "," + age + "," + grade + "," + score + "]";
	}
	}