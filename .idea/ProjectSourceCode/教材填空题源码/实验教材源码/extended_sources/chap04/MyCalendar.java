import java.util.*;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
public class MyCalendar {
   public static void main(String args[]){
      new CalendarWindow();
   }
}
class CalendarWindow extends JFrame implements ChangeListener {
   JSpinner jsp=null;
   CalPane calPane=null;
   Container con=null;
   CalendarWindow() {  
     SpinnerModel model =
       new SpinnerNumberModel(2009,1990,2090,1); 
       jsp=new JSpinner(model); 
       jsp.addChangeListener(this);
       jsp.setEditor(new JSpinner.NumberEditor(jsp, "#"));
       con=this.getContentPane();
       con.add(jsp,BorderLayout.NORTH);
       calPane=new CalPane(2009);
       con.add(calPane,BorderLayout.CENTER);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLocation(100,100);
       setSize(400,240); 
       setVisible(true);
       validate();
   }  
  public void stateChanged(ChangeEvent e) {
      con.remove(calPane);
      int year=(Integer)jsp.getValue();
      calPane=new CalPane(year);
      con.add(calPane,BorderLayout.CENTER);
      con.validate(); 
      validate();
   }
}
class CalPane extends JPanel implements ActionListener { 
     JTable table;
     String a[][]=new String[6][7];
     String colName[]={"日","一","二","三", "四","五","六"};
     JButton  nextMonth,previousMonth;
     int year=2009,month=1;
     CalendarBean calendar;
     JLabel showMessage=new JLabel("",JLabel.CENTER);
     public CalPane(int year){
        setLayout(new BorderLayout());
        calendar=new  CalendarBean();
        calendar.setYear(year);
        calendar.setMonth(month);
        String day[]=calendar.getCalendar();
        table=new JTable(a,colName);
        table.setRowSelectionAllowed(false); 
        setTable(day);
        nextMonth=new JButton("下月");
        previousMonth=new JButton("上月");
        nextMonth.addActionListener(this);
        previousMonth.addActionListener(this);
        JPanel pNorth=new JPanel(),
               pSouth=new JPanel();
        pNorth.add(previousMonth);
        pNorth.add(nextMonth);
        pSouth.add(showMessage);
        showMessage.setText("日历："+calendar.getYear()+"年"+calendar.getMonth()+"月" );
        add(new JScrollPane(table),BorderLayout.CENTER); 
        add(pNorth,BorderLayout.NORTH);
        add(pSouth,BorderLayout.SOUTH);
        validate();
     }
     public void actionPerformed(ActionEvent e){
        if(e.getSource()==nextMonth){
          month=month+1;
          if(month>12)
              month=1;
          calendar.setMonth(month);
          String day[]=calendar.getCalendar();
          setTable(day);
          table.repaint();
        }else if(e.getSource()==previousMonth){
          month=month-1;
          if(month<1)
              month=12;
          calendar.setMonth(month);
          String day[]=calendar.getCalendar();
          setTable(day);
          table.repaint();
        }
       showMessage.setText("日历："+calendar.getYear()+"年"+calendar.getMonth()+"月" );
     }
  public void setTable(String day[]){                      //设置表格单元格中的数据。
        int n=0;
        for(int i=0;i<6;i++) {
            for(int j=0;j<7;j++) {
                a[i][j]=day[n];
                n++; 
             }
         }  
     }
}
class CalendarBean {  
   String  day[];
   int year=2009,month=0;
   public void setYear(int year) {
        this.year=year;
   }
   public int getYear(){
        return year; 
   }
   public void setMonth(int month) {
       this.month=month;
   }
   public int getMonth(){
       return month; 
   }
   public String[] getCalendar() {
       String a[]=new String[42];                    
       Calendar rili=Calendar.getInstance();
       rili.set(year,month-1,1);             
       int week=rili.get(Calendar.DAY_OF_WEEK)-1;
       int day=0;
     if(month==1||month==3||month==5||month==7||month==8||month==10||month==12) {
        day=31;
      } 
     if(month==4||month==6||month==9||month==11) {
        day=30;
      }
     if(month==2) {
         if(((year%4==0)&&(year%100!=0))||(year%400==0)){
              day=29;
           } else {
              day=28;
           }
      }
      for(int i=week,n=1;i<week+day;i++) { 
               a[i]=String.valueOf(n) ;
               n++;
      }  
      return a;
   } 
}
