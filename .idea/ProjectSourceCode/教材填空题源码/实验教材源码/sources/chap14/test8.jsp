<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>application对象演示</title>
  </head>
  <body>
  <%! Integer num;
  %>
  <%
  if(session.isNew()){
    num = (Integer)application.getAttribute("count");
    if(num == null) {//如果是第一个访问者
       num = new Integer(1);
       application.setAttribute("count",num);
    }else{
       num = new Integer(num.intValue() + 1);
       application.setAttribute("count",num);
    }
  }
  %>
  <p>
  <font size="2" color="blue">简单的用户访问计数器</font>
  </p>
  <p>
  <font size="2" color="#000000">
  欢迎访问此页面，您是<%=num%>个访问用户
  </font>
  </p>
  </body>
</html>
