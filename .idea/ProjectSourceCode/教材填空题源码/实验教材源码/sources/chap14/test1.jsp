<%@ page contentType="text/html" %>   <!-- jsp指令标记 -->
<%@ page pageEncoding = "utf-8" %>    <!-- jsp指令标记 -->
<%@ page import="java.time.LocalTime" %> <!-- jsp指令标记 -->
<%!   public int continueSum(int start,int end){  // 定义方法
         int sum =0; 
         for(int i=start;i<=end;i++)  
            sum=sum+i; 
         return sum;
      }
%>
<HTML><body bgcolor = pink>
<script>   <!--JavaScript(JS)标记 -->                    
  <!--以下是JavaScript代码片段 -->
  var userTime = new Date();
  var hour = userTime.getHours();
  var minute =userTime.getMinutes();
  var second = userTime.getSeconds();
  var millisecond = userTime.getMilliseconds();
  document.write("<h2>浏览器时间:"+
                  hour+":"+minute+":"+second+":"+millisecond+"<br></h2>");
</script> 
<p style="font-family:黑体;font-size:36;color:red"> <!--html标记 --> 
<%  //Java程序片：
    LocalTime timeServer = LocalTime.now();  
    int hour = timeServer.getHour();
    int minute =timeServer.getMinute();
    int second = timeServer.getSecond();
    int nano   = timeServer.getNano() ;//纳秒。
    int millisecond = nano/1000000;
    out.print("服务器时间:<br>"+
             hour+":"+minute+":"+second+":"+millisecond);
    int start=1;
    int end=100; 
    int sum=continueSum(start,end);
%>
</p>
<p style="font-family:宋体;font-size:33;color:blue"> <!--Html标记，属性为css样式 -->
   从
   <%= start %>  <!-- Java表达式 --> 
   至
   <%= end %>    <!-- Java表达式 --> 
   的<br>连续和是:
   <%= sum %>    <!-- Java表达式 --> 
   <br>
   <%= new java.util.Date().toString() %>
   <%
   for(int i=1;i<=10;i++){
        out.print("sqrt("+i+")="+Math.sqrt(i)+"<br>");
   }
   %>
</p>
<script>   <!--JavaScript(JS)标记 -->                    
   document.write("<h2>服务器时间:"+
<%=hour%>+":"+<%=minute%>+":"+<%=second%>+":"+<%=millisecond%>+"</h2>");
</script> 
</body></HTML>
