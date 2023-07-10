<%@ page contentType="text/html" %> 
<%@ page pageEncoding = "utf-8" %>
<%! public String getArea(double a,double b,double c) {
       if(a+b>c&&a+c>b&&c+b>a) {
           double p=(a+b+c)/2.0;
           double area=Math.sqrt(p*(p-a)*(p-b)*(p-c)) ;
           String result = String.format("%.2f",area);//保留两位小数
           return result;
       }
       else { 
          return(""+a+","+b+","+c+"不能构成一个三角形,无法计算面积");
       }
    }
%>
<%   String sideA=request.getParameter("sideA");
     String sideB=request.getParameter("sideB");
     String sideC=request.getParameter("sideC");
     double a=Double.parseDouble(sideA);
     double b=Double.parseDouble(sideB);
     double c=Double.parseDouble(sideC);
%>
<p style="font-family:黑体;font-size:36;color:blue">
<br><b>我是被加载的文件,负责计算三角形的面积<br>
    给我传递的三边是:<%=sideA%>,<%=sideB%>,<%=sideC%></b>
    <% if(a+b>c&&a+c>b&&c+b>a) { %>
<br><b><i>三角形的面积(保留2位小数):<%= getArea(a,b,c)%></i></b>
    <% } else { %>
     <br><b><i><%= getArea(a,b,c)%></i></b>
     <% } %>
</p>
