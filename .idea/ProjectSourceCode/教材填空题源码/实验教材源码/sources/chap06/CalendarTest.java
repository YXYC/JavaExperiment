import java.text.*;
import java.util.*;
class CalendarTest {
  public static void main(String[] args) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    String  year= String.valueOf(calendar.get(Calendar.YEAR)),
            month= String.valueOf(calendar.get(Calendar.MONTH)+1),
            day= String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),
            weekday= String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)-1);
    int hour = calendar.get(Calendar.HOUR_OF_DAY),
        minute = calendar.get(Calendar.MINUTE),
        second = calendar.get(Calendar.SECOND);
    System.out.println("���ڵ�ʱ���ǣ�");
    System.out.println(year+"��"+month+"��"+day+"��"+"����"+weekday);
    System.out.println(hour+"ʱ"+minute+"��"+second+"��");
    calendar.set(2008,2,8);  //����������2008��3��8��
    long time2008 = calendar.getTimeInMillis();
    calendar.set(2009,2,8);  //����������2009��3��8��
    long time2009 = calendar.getTimeInMillis();
    long days = (time2009-time2008)/(1000*60*60*24);
    System.out.println("2009��3��8�պ�2008��3��8�����"+days+"��");

    Date date = new Date();
    SimpleDateFormat dateFm1 = new SimpleDateFormat("EEEE-MMMM-dd-yyyy");
    System.out.println(dateFm1.format(date));

    DateFormat dateFm2 = DateFormat.getDateTimeInstance(DateFormat.LONG,
    DateFormat.SHORT);
    System.out.println(dateFm2.format(date));
   }
}