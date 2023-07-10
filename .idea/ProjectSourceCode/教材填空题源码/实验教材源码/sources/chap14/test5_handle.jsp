<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.DayOfWeek" %>
<%
    request.setCharacterEncoding("utf-8");  
    String year=request.getParameter("year"); 
    String month=request.getParameter("month");
    int y = Integer.parseInt(year);
    int m = Integer.parseInt(month);
    LocalDate date = LocalDate.of(y,m,1);
    int days = date.lengthOfMonth(); //得到该月有多少天。
    int space = 0;                   //存放空白字符的个数
    DayOfWeek dayOfWeek = date.getDayOfWeek(); //得到1号是星期几
    switch(dayOfWeek) {
          case SUNDAY:    space = 0; break;
          case MONDAY:    space = 1; break;
          case TUESDAY:   space = 2; break;
          case WEDNESDAY: space = 3; break;
          case THURSDAY:  space = 4; break;
          case FRIDAY:    space = 5; break;
          case SATURDAY:  space = 6; break;
   }
   String [] calendar = new String[space+days]; //用于存放日期和1号前面的空白
   for(int i=0;i<space;i++)
       calendar[i]="--";
   for(int i = space,n=1;i<calendar.length;i++){
       calendar[i] = String.valueOf(n) ;
       n++;
   }  
%>
<HTML><body bgcolor = #ffccff>
<h3> <%=year %>年<%=month %>月的日历:</h3>
<table  border=1>
  <tr><th>星期日</th><th>星期一</th><th>星期二</th><th>星期三</th>
      <th>星期四</th><th>星期五</th><th>星期六</th>
  </tr>
<% 
   int n = 0;
   while(n<calendar.length){
       out.print("<tr>");
       int increment = Math.min(7,calendar.length-n);
       for(int i=n;i<n+increment;i++) {
         out.print("<td align= center>"+calendar[i]+"</td>");
       }
       out.print("</tr>");
       n = n+increment;
   }
%> 
</table></body></HTML>
