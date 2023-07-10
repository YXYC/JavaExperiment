import java.util.*;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
public class Shiyan4_3_1 {
   public static void main(String args[]){
      new CalendarWindow();
   }
}
class CalendarWindow extends JFrame implements ChangeListener {
   JSpinner jsp=null;
   CalPane calPane=null;
   Container con=null;
   CalendarBean cb=null;
   CalendarWindow() {  
       cb=new CalendarBean();
       int year=cb.getYear();
       SpinnerModel model =
       new SpinnerNumberModel(year,1990,2090,1); 
       jsp=new JSpinner(model); 
       jsp.addChangeListener(this);
       jsp.setEditor(new JSpinner.NumberEditor(jsp, "#"));
       con=this.getContentPane();
       con.add(jsp,BorderLayout.NORTH);
       calPane=new CalPane(cb.getYear(),cb);
       con.add(calPane,BorderLayout.CENTER);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLocation(100,100);
       setSize(450,240); 
       setVisible(true);
       validate();
   }  
  public void stateChanged(ChangeEvent e) {
      con.remove(calPane);
      int year=(Integer)jsp.getValue();
      System.out.println("year:"+year);
      calPane=new CalPane(year,cb);
      con.add(calPane,BorderLayout.CENTER);
      con.validate(); 
      validate();
   }
}
class MyRenderer implements TableCellRenderer {
     CalendarBean cb;
     public MyRenderer(CalendarBean cb){this.cb=cb;}
     public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();
     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
         Component renderer =DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
         Color foreground=Color.BLACK, background=Color.WHITE;
         Calendar curday=Calendar.getInstance();
         int today=curday.get(Calendar.DATE);
         int startofweek=curday.get(Calendar.DAY_OF_WEEK);
         int startofmonth=cb.getWeek();
       
         if (row == (today+startofmonth-1-startofweek)/7 && column==startofweek-1) {
             if(cb.getYear()==curday.get(Calendar.YEAR)&&cb.getMonth()==curday.get(Calendar.MONTH)+1){
                 foreground = Color.red;
                 background = Color.BLUE;
             }
         }  else {
             foreground = Color.BLACK;
             background = Color.WHITE;
         }
         renderer.setForeground(foreground);
         renderer.setBackground(background);
         return renderer;
    }
}
class CalPane extends JPanel implements ActionListener { 
     JTable table;
     String a[][]=new String[6][7];
     String colName[]={"日","一","二","三", "四","五","六"};
     JButton  nextMonth,previousMonth;
     int year,month;
     CalendarBean calendar;
     JLabel showMessage=new JLabel("",JLabel.CENTER);
 
     public CalPane(int y,CalendarBean cb){
        setLayout(new BorderLayout());
        calendar=cb;
        year=y;
        cb.setYear(y);
        month=cb.getMonth();
        String day[]=calendar.getCalendar();
        table=new JTable(a,colName);
        table.setRowSelectionAllowed(false); 
        setTable(day);
        table.setDefaultRenderer(Object.class,new MyRenderer(calendar));
         
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
   private String  day[];
   private Calendar rili;
   public CalendarBean(){
       rili=Calendar.getInstance();
       rili.set(getYear(),getMonth()-1,1);
   }
   public void setYear(int year) {  rili.set(Calendar.YEAR,year);  }
   public int getYear(){ return rili.get(Calendar.YEAR); }
   public int getDate(){ return rili.get(Calendar.DATE);}
   public int getMonth(){ return rili.get(Calendar.MONTH)+1;}
   public void setMonth(int month) {rili.set(Calendar.MONTH,month-1);}
   public void setDate(int date){rili.set(Calendar.DATE,date);}
   public int  getWeek(){return rili.get(Calendar.DAY_OF_WEEK);}
   public String[] getCalendar() {
       String a[]=new String[42];  
       int year=getYear();
       int month=getMonth();                             
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
