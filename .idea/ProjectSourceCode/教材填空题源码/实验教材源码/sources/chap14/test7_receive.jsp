<%@ page contentType="text/html" %>
<%@ page pageEncoding = "utf-8" %> 
<HTML><body bgcolor = #DDEEFF> 
<%  request.setCharacterEncoding("utf-8"); 
    String name=request.getParameter("name");
    if(name==null||name.length()==0) {
       response.sendRedirect("test7.jsp"); 
       String str =(String)session.getAttribute("name");//这个仍然会被执行。
       session.setAttribute("name","李四"+str);//这个仍然会被执行。
    }
%> 
<b>欢迎<%= name %>访问网页。
</body></HTML>
