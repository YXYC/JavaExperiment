<%@ page contentType="text/html" %>
<%@ page pageEncoding = "utf-8" %> 
<style>#textStyle
   { font-family:宋体;font-size:36;color:blue 
   }
</style>
<HTML><body bgcolor=#ffccff> 
<p id="textStyle">
填写姓名（<%= (String)session.getAttribute("name")%>）：<br>
<form action="test7_receive.jsp" method="post" name=form>
   <input type="text" id="textStyle"  name="name">
   <input type="submit" id="textStyle" value="确定"/>
</form>
</body></HTML>
