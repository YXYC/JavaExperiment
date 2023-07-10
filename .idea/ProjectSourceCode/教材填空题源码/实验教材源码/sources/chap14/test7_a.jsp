<%@ page contentType="text/html" %>
<%@ page pageEncoding = "utf-8" %>
<style>#textStyle
   { font-family:宋体;font-size:36;color:blue 
   }
</style> 
<HTML><body bgcolor=#ffccff> 
<p id="textStyle"> 
这是test7_a.jsp页面<br>单击提交键连接到test7_b.jsp
<% String id=session.getId();
   out.println("<br>session对象的ID是<br>"+id);
%>
<form action="test7_b.jsp" method=post name=form>
  <input type="submit" id="textStyle" value="访问test7_b.jsp" />
</form>  
</body></HTML>

