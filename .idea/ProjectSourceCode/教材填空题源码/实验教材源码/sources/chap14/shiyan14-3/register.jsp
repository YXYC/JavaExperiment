<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册界面</title>
    <style type="text/css">
        .center{
            text-align:center;
            margin-top: 50px;
        }
        .fon{
            font-size: 40px;
        }
        body{
            background-size: 100% 100%;
        }
        input {
            background-color: transparent;
            outline: none;
            color: black;
        }
        .clear{
            opacity:0.3;
        }
    </style>
</head>
<body>

<div class="center">
    <p class="fon">注册界面</p>
    <p>请输入您的用户名和密码进行注册</p>
    <form method="post" action="registerCheck.jsp">
        用户名：<input type="text" name="user1" style="width: 300px;height: 50px" placeholder="请输入用户名：" > <br>
        密码：&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="pass1"  style="width: 300px;height: 50px" placeholder="请输入密码：" > <br>
        <button type="submit" style="width:80px;height:40px; font-size: 20px" class="clear">注册</button>
        <button type="reset" style="width:80px;height:40px; font-size: 20px" class="clear">重置</button>
        <br>
    </form>
</div>
</body>
</html>

