<%@ page contentType="text/html" %>
<%@ page pageEncoding = "utf-8" %> 
<%@ page import="java.time.LocalTime" %>
<HTML><body bgcolor = #ffccff>
<p style="font-family:宋体;font-size:36;color:blue">
现在的时间是：<br>
<%  out.println(""+LocalTime.now());
    response.setHeader("Refresh","5");
 %>
</p></body></HTML>
