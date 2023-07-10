<%@ page contentType="text/html;charset=UTF-8" import="java.util.*,java.awt.*" language="java" %>
<html>
  <head>
    <title>登入界面</title>
    <style type="text/css">
        .center{
            text-align:center;
            margin-top: 50px;
        }
        .fon{
            font-size: 40px;
        }
        .fon1{
            font-size: 20px;
        }
        body{
            background-size: 100% 100%;
        }
        input {
            background-color: transparent;
            outline: none;
            color: black;
        }

    </style>
  </head>
  <body>
  <div class="center">
  <p class="fon">登陆界面</p>
   <p>请输入您的用户名和密码进行登陆</p>

  <form method="post" action="loginCheck.jsp">
     用户名：<input type="text" name="user" style="width: 300px;height: 50px" placeholder="请输入用户名：" > <br>
     密码：&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="pass" style="width: 300px;height: 50px" placeholder="请输入密码：" > <br>
     验证码：&nbsp;&nbsp;<img src="numbers.jsp"/>&nbsp;&nbsp;<input type="text" name="code" style="width: 200px;height: 50px" placeholder="请输入验证码：" > <br>
     <button type="submit" style="width:80px;height:40px; font-size: 20px" value="login">登入</button>
        <a style="width:80px;height:40px; font-size: 20px" href="register.jsp">&nbsp;&nbsp;注册</a>
  </form>
  </body>
</html>

