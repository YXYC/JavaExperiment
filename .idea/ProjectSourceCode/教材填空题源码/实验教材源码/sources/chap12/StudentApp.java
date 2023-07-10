import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;
class StudentFrame extends JFrame {
   JLabel id_label,name_label,sex_label,age_label,dep_label;
   JTextField id_txt,name_txt,sex_txt,age_txt,dep_txt;
   JButton first,last,next,previous,load;
   StudentBean mybean;
   SeleCourseBean scbean;
   CourseBean cb;
   ArrayList<Student> stulist;
   int row=0;
   Object a[][];
   Object title[]={"�γ̺�","�γ���","�ɼ�"};
   JTable table;
   JScrollPane scrollpane;
   public StudentFrame()   {
      super("ѧ���ɼ�����ϵͳ(ver 1.0)---���ѧ����Ϣ");
      mybean=new StudentBean();
      scbean=new SeleCourseBean();
      cb=new CourseBean();
      JPanel p1=new JPanel();
      id_label=new JLabel("ѧ��:");
      name_label=new JLabel("����:");
      sex_label=new JLabel("�Ա�:");
      age_label=new JLabel("����:");
      dep_label=new JLabel("רҵ:");
      
      id_txt=new JTextField(8);
      name_txt=new JTextField(8);
      sex_txt=new JTextField(8);
      age_txt=new JTextField(8);
      dep_txt=new JTextField(8);
      
      p1.setLayout(new GridLayout(5,2));
      p1.add(id_label);p1.add(id_txt);
      p1.add(name_label);p1.add(name_txt);
      p1.add(sex_label);p1.add(sex_txt);
      p1.add(age_label);p1.add(age_txt);
      p1.add(dep_label);p1.add(dep_txt);

      first=new JButton("��һ��");
      first.setEnabled(false);
      first.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            row=0;
            refreshdata();   
         }
      });
      next=new JButton("��һ��");
      next.setEnabled(false);
      next.addActionListener(new ActionListener()      {
         public void actionPerformed(ActionEvent e)         {
            if(row<stulist.size()-1)            {
               row++;
            }
            else            {  
               row=stulist.size()-1;
            }
            refreshdata();   
         }
      });
      
      last=new JButton("���һ��");
      last.setEnabled(false);
      last.addActionListener(new ActionListener()   {
         public void actionPerformed(ActionEvent e)      {
            row=stulist.size()-1;
            refreshdata();   
         }
      });
      previous=new JButton("��һ��");
      previous.setEnabled(false);
      previous.addActionListener(new ActionListener()    {
         public void actionPerformed(ActionEvent e)      {
            if(row>0)         {
               row--;
            }
            else      {  
               row=0;
            }
            refreshdata(); 
         }
      });

      load=new JButton("װ������");
      load.addActionListener(new ActionListener()   {
         public void actionPerformed(ActionEvent e)   {
             try   {
                stulist=mybean.getAll();
             } catch(Exception e1){ e1.printStackTrace()}
             first.setEnabled(true);
             next.setEnabled(true);
             last.setEnabled(true);
             previous.setEnabled(true);
             refreshdata();   
         }
      });
      JPanel p2=new JPanel(new GridLayout(5,1));
      p2.add(load);p2.add(first);p2.add(previous);p2.add(next);p2.add(last);
      JPanel p=new JPanel(new BorderLayout());
      p.add(p1,BorderLayout.CENTER);p.add(p2,BorderLayout.EAST);
      JButton printscore=new JButton("��ʾ�ɼ���");
      printscore.addActionListener(new ActionListener()  {
        public void actionPerformed(ActionEvent event)  {
          try{
              String id=stulist.get(row).getId();
              ArrayList<SeleCourse> sclist=scbean.getSeleCourse(id);
              if(sclist.size()==0) System.out.println("�޳ɼ���Ϣ��");
              else  { 
                System.out.println("�γ���\t\t��ʦ\t�ɼ�");
                System.out.println("==============================");                
                for(int i=0;i<sclist.size();i++){
                  String cno=sclist.get(i).getCno();
                  String cn=cb.getCourse(cno).getCname();
                  String tn=cb.getCourse(cno).getTeacher();
                  System.out.println(cn+"  \t"+tn+"\t"+sclist.get(i).getGrade());
                } 
              }
          }catch(SQLException ee){}
       }
     });
      JButton sortscore=new JButton("�ܳɼ�����");
      sortscore.addActionListener(new ActionListener()    {
        public void actionPerformed(ActionEvent event)   {
         if(stulist.size()==0) System.out.println("��ѧ��");
         else   {
           System.out.println("\n  \t�ܳɼ�����");
           System.out.println("-------------------------------");
           System.out.printf("%-10s %6s\n","����","�ܳɼ�");
           System.out.println("==============================");
           try{
              String id=null;
              float  score=0;
              int stucounts=stulist.size();
              class Tempobj implements Comparable{
                String stuname;
                float  stuscore;
                Tempobj(String s,float sc){stuname=s;stuscore=sc;}
                public int compareTo(Object x){
                   Tempobj b=(Tempobj)x;
                   if(stuscore-b.stuscore>0) return 1;
                   else if(stuscore-b.stuscore<0) return -1;
                   else return 0;
                }
              }
              ArrayList<Tempobj> scorelist=new ArrayList<Tempobj>();
              for(int i=0;i<stucounts;i++){
                score=0;
                id=stulist.get(i).getId();
                ArrayList<SeleCourse> sclist=scbean.getSeleCourse(id); 
                for(int j=0;j<sclist.size();j++){
                  score+=sclist.get(j).getGrade();
                }
                scorelist.add(new Tempobj(stulist.get(i).getName(),score));
//                System.out.printf("%-10s\t%6s\n",stulist.get(i).getName(),""+score); 
              }
              Collections.sort(scorelist);
              Iterator<Tempobj> iter=scorelist.iterator();
              while(iter.hasNext()){
                Tempobj obj=iter.next();
                System.out.printf("%-10s\t%6s\n",obj.stuname,obj.stuscore);                 
              }
           }catch(SQLException ee){}
           System.out.println("==============================");
         }
       }
     });
      JPanel p3=new JPanel(new GridLayout(1,2));
      p3.add(printscore);p3.add(sortscore);
      Container con=getContentPane();
      con.add(p,BorderLayout.NORTH);
      con.add(p3,BorderLayout.SOUTH);
   }
   public void refreshdata()  {
      String id=stulist.get(row).getId();
      id_txt.setText(id);
      name_txt.setText(stulist.get(row).getName());
      sex_txt.setText(""+stulist.get(row).getSex());
      age_txt.setText(""+stulist.get(row).getAge());
      dep_txt.setText(""+stulist.get(row).getDep());
      /*ѡ����Ϣ*/
      try{
        ArrayList<SeleCourse> sclist=scbean.getSeleCourse(id); 
        a=new Object[sclist.size()][title.length];
        table=new JTable(a,title); 
        scrollpane=new JScrollPane(table);
        getContentPane().add(scrollpane,BorderLayout.CENTER);
        for(int i=0;i<sclist.size();i++){
           String cno=sclist.get(i).getCno();
           table.setValueAt(cno, i, 0);
           String cn=cb.getCourse(cno).getCname();
           table.setValueAt(cn, i, 1);
           table.setValueAt(sclist.get(i).getGrade(),i,2);
        } 
      }catch(SQLException e){System.out.println(e);} 
      validate();
   }
   public static void main(String[] args)   {
      StudentFrame myapp=new StudentFrame();
      myapp.setSize(400,300);
      myapp.setVisible(true);
      myapp.addWindowListener(new WindowDestroyer());
   }
}