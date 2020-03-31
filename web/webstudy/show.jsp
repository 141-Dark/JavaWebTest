<%--
  Created by IntelliJ IDEA.
  User: DYK
  Date: 2019/11/25
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据传输测试</title>
</head>
<body>
亲爱的<%=new String(request.getParameter("user1").getBytes("iso-8859-1"),"UTF-8")%>你好
<%
    //获取register中的所有元素，这里传入的默认是name属性
    String user = request.getParameter("user1");
    String number = request.getParameter("number");
    String pwd1 = request.getParameter("pwd1");
    String pwd2 = request.getParameter("pwd2");
    String phone = request.getParameter("phone");
    String email = request.getParameter("email");

    //创建cookie对象以及存活时间为二十分钟
    Cookie mycook = new Cookie("firstCookie",user+"#"+number+"#"+pwd1+"#"+pwd2+"#"+phone+"#"+email);
    mycook.setMaxAge(60*60*24);
    response.addCookie(mycook);

    //获取register发送来的session对象
    String getmessage = (String)session.getAttribute("message");
    System.out.println("保存到session的值:"+getmessage);

%>
</body>
</html>
