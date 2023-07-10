<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.sql.*"%>
<html>
<head>
    <title>注册检查界面</title>
</head>
<body>

<form method="post" action="">

    <%
	String user1 = request.getParameter("user1");
	String pass1 = request.getParameter("pass1");
	boolean status=false;

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
       String SQL = "SELECT * FROM users WHERE username='"+user1+"'";//SQL语句。
       rs=sql.executeQuery(SQL);//查表。
	while(rs.next()){
		if(user1.equals(rs.getString(1))){
			status=true;
			break;
		}
	}

        if (status) {
	    con.close();
            response.sendRedirect("registerfail.jsp");
        }
        else {
	    SQL="insert into users values('"+user1+"','"+pass1+"');";
	    int num=sql.executeUpdate(SQL);
	    con.close();
	    if(num>0){
            	response.sendRedirect("registersuccess.jsp");
	    }else{
            	response.sendRedirect("registerfail.jsp");
	    }
        }
    } catch(SQLException e) {
       out.print("<h1>"+e);
    }


    %>
</form>
</body>
</html>

