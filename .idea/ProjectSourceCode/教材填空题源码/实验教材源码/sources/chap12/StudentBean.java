import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentBean
implements
  java.io.Serializable
{
  private java.sql.Statement stmt;
  private java.sql.ResultSet rs;

  public StudentBean() {
    stmt = null;
    rs = null;
  }

  public boolean insertOrUpdate(Student s1) throws SQLException {
    boolean i2;
    Connection c3;
    int i4;
    i2 = true;
    c3 = ConnectDB.getConnection();
    stmt = c3.createStatement();
    rs = stmt.executeQuery(new StringBuilder().append("select * from S where Sid='").append(s1.getId()).append("'").toString());
    if (rs.next()) {
      i4 = stmt.executeUpdate(new StringBuilder().append("update S set name='").append(s1.getName()).append("',sex='").append(s1.getSex()).append("',age=").append(s1.getAge()).append(",dep='").append(s1.getDep()).append("' where Sid='").append(s1.getId()).append("'").toString());
      if (i4 == 0) {
        i2 = false;
      }
    }
    else {
      i4 = stmt.executeUpdate(new StringBuilder().append("insert into S values('").append(s1.getId()).append("','").append(s1.getName()).append("','").append(s1.getSex()).append("',").append(s1.getAge()).append(",'").append(s1.getDep()).append("')").toString());
      if (i4 == 0) {
        i2 = false;
      }
    }
    return i2;
  }

  public boolean delStudent(Student s1) throws java.sql.SQLException {
    boolean i2;
    Connection c3;
    int i4;
    i2 = true;
    c3 = ConnectDB.getConnection();
    stmt = c3.createStatement();
    i4 = stmt.executeUpdate(new StringBuilder().append("delete from S where Sid='").append(s1.getId()).append("'").toString());
    if (i4 == 0) {
      i2 = false;
    }
    return i2;
  }

  public Student getStudent(String s1) throws java.sql.SQLException {
    Connection c2;
    String s3;
    Student o4;
    c2 = ConnectDB.getConnection();
    stmt = c2.createStatement();
    s3 = new StringBuilder().append("select * from S where Sid='").append(s1).append("'").toString();
    rs = stmt.executeQuery(s3);
    o4 = null;
    if (rs.next()) {
      o4 = new Student();
      o4.setId(rs.getString(1));
      o4.setName(rs.getString(2));
      o4.setSex(rs.getString(3));
      o4.setAge(rs.getInt(4));
      o4.setDep(rs.getString(5));
    }
    else {
      o4 = null;
    }
    rs.close();
    stmt.close();
    return o4;
  }

  public java.util.ArrayList getAll() throws SQLException {
    ArrayList a1;
    Connection c2;
    Student s3;
    a1 = new java.util.ArrayList();
    c2 = ConnectDB.getConnection();
    stmt = c2.createStatement();
    rs = stmt.executeQuery("select * from S");
    while (rs.next()) {
      s3 = new Student();
      s3.setId(rs.getString(1));
      s3.setName(rs.getString(2));
      s3.setSex(rs.getString(3));
      s3.setAge(rs.getInt(4));
      s3.setDep(rs.getString(5));
      a1.add(s3);
    }
    rs.close();
    stmt.close();
    return a1;
  }
}
