<%@ page contentType="text/html" %>
<%@ page pageEncoding = "utf-8" %>
<style>
   #textStyle{
      font-family:宋体;font-size:36;color:blue 
   }
</style>
<% request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="play" class="red.star.Play" scope="session" />
<%
    String webDir = request.getContextPath();//获取当前Web服务目录的名称
    webDir = webDir.substring(1); //去掉名称前面的目录符号：/
%>
<jsp:setProperty  name="play" property="webDir" value="<%= webDir %>"/> 
<jsp:setProperty  name="play" property="index"  param = "index" /> 
<HTML><body bgcolor = cyan><p id = textStyle>
<image src = 
 image/<jsp:getProperty name="play" property="showImage"/> width =300 height =200>
</image><br> 
<a href ="?index=<%=play.getIndex()+1 %>">下一张</a>
<a href ="?index=<%=play.getIndex()-1 %>">上一张</a>
</p></body></HTML>
