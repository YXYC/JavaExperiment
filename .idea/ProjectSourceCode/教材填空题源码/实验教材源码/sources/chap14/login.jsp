<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">       
  </head>  
  <%  
    String input_code = request.getParameter("req_code");   
    String hide_code =(String)session.getAttribute("sec_code");   
    String username=request.getParameter("username");
    String password=request.getParameter("password");
    if(username!=null&&password!=null){
         out.println("上次的信息:<br>");
         out.println("username:"+username);
         out.println("password:"+password);
	 out.println("hide_code:"+hide_code);
    }
    if(input_code!=null && hide_code!=null){  
        if(input_code.equals(hide_code)){  
		out.println("<br>上次验证码输入正确！");  
        }else{  
		out.println("<br>上次验证码输入不正确，请重新输入！");  
        }  
    }  
  %>  
    
  <body>  
    <form action="login.jsp" method="post">  
    用户名：  
    <input type="text" name="username"/><br/>  
    密码：  
    <input type="password" name="password"/><br/>  
    验证码：  
    <img src="numbers.jsp"/>  
    <input type="text" name="req_code"/>  
    <input type="submit" value="登录"/>  
    </form>  
  </body>  
</html>  
