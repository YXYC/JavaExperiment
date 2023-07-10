<%@ page contentType="text/html" %>  
<%@ page pageEncoding = "utf-8" %>  
<HTML><body>
<h1> 产生一个1-10之间的随机数
<%  double i=(int)(Math.random()*10)+1;
    if(i<=5) {
%>      <jsp:forward page="test4_1.jsp" >
            <jsp:param name="number" value="<%= i %>" />
        </jsp:forward> 
<%  }
    else {
%>     <jsp:forward page="test4_2.jsp" >
           <jsp:param name="number" value="<%= i %>" />
       </jsp:forward> 
<%  }
%>
</body></HTML>
