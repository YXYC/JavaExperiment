<%@ page contentType="text/html" %>
<%@ page pageEncoding = "utf-8" %> 
<style>#textStyle
   { font-family:黑体;font-size:36;color:red 
   }
</style> 
<HTML><body bgcolor=cyan> 
<p id="textStyle">  
这是test7_b.jsp页面
<%  String id=session.getId();
    out.println("<br>session对象的ID是<br>"+id);
%>
<br>连接到test7_a.jsp的页面。<br>
<a href="test7_a.jsp">test7_a.jsp</a>   
</body></HTML>

