<%@ page contentType="text/html" %>
<%@ page pageEncoding = "utf-8" %>
<style>
   #textStyle{
      font-family:宋体;font-size:28;color:blue 
   }
</style> 
<HTML><body id = textStyle bgcolor = #ffccff>  
<form action="test5_handle.jsp" method=post >
输入日期的年份选择月份查看日历.<br>
年份：<input type="text" name="year" id = textStyle value=2022 size=12 />
月份 <select name="month" id = textStyle size =1>
  <option value="1">1月</option>
  <option value="2">2月</option>
  <option value="3">3月</option>
  <option value="4">4月</option>
  <option value="5">5月</option>
  <option value="6">6月</option>
  <option value="7">7月</option>
  <option value="8">8月</option>
  <option value="9">9月</option>
  <option value="10">10月</option>
  <option value="11">11月</option>
  <option value="12">12月</option>
</select><br>  
<input type="submit" id = textStyle value="提交"/>
</form> 
</body></HTML>
