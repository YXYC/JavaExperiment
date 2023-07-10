<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.sql.*"%>
<html>
<head>
    <title>检查界面</title>
</head>
<body>

<form method="post" action="">

    <%
	String user = request.getParameter("user");
	String pass = request.getParameter("pass");
	String code = request.getParameter("code");
	String hide_code =(String)session.getAttribute("sec_code");
	String name = null;
        String passward = null;

	Connection con=null;
    	Statement sql;
    	ResultSet rs;
    	try{  //加载JDBC-MySQL8.0连接器:
       	    Class.forName("com.mysql.cj.jdbc.Driver");
    	}
    	catch(Exception e){
       	    out.print("<h1>"+e);
    	}
    	String url = "jdbc:mysql://localhost:3306/Users?"+"useSSL=false&serverTimezone=UTC&characterEncoding=utf-8";
    	String root ="lzuuser1";
    	String password ="lzu320200932281@";
       try{
       con = DriverManager.getConnection(url,root,password);//连接数据库。
       sql=con.createStatement();
       String SQL = "SELECT * FROM users WHERE username='"+user+"'";//SQL语句。
       rs=sql.executeQuery(SQL);//查表。
	if(rs.next()){
	    name=rs.getString(1);
	    passward=rs.getString(2);
	}
	con.close();
        if (user.equals(name) && pass.equals(passward) &&code.equals(hide_code)) {
            response.sendRedirect("loginsuccess.jsp");
        }
        else {
            response.sendRedirect("loginfail.jsp");
        }
    } catch(SQLException e) {
       out.print("<h1>"+e);
    }


    %>
</form>
</body>
</html>

