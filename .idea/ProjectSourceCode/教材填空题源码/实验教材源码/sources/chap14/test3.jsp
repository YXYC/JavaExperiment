<%@ page contentType="text/html" %>
<%@ page pageEncoding = "utf-8" %>
<HTML><body  bgcolor=cyan >
<!--产生三随机数-->
<% 
   double a=1+Math.random()*10;
   double b=2+Math.random()*10;
   double c=3+Math.random()*10;
%>
<p style="font-family:宋体;font-size:36">
<br>加载triangle.jsp计算三边为<%=a%>,<%=b%>,<%=c%>的三角形面积.
   <jsp:include page="myfile/triangle.jsp">
     <jsp:param name="sideA" value="<%=a%>"/>
     <jsp:param name="sideB" value="<%=b%>"/>
     <jsp:param name="sideC" value="<%=c%>"/>
   </jsp:include>
</p></body></HTML>
