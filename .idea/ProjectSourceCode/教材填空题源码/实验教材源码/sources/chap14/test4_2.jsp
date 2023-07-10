<%@ page contentType="text/html" %>  
<%@ page pageEncoding = "utf-8" %>  
<HTML><body bgcolor=yellow>
<p style="font-family:宋体;font-size:36">
<% String s=request.getParameter("number");
    out.println("传递过来的值是"+s);
%>
<br><img src=image/pic_b.jpg width=300 height=280 />
</p></body></HTML>
