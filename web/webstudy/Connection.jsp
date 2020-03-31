<%--
  Created by IntelliJ IDEA.
  User: DYK
  Date: 2019/11/27
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Bean.RegisterBean" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:useBean id="user" beanName="Bean.RegisterBean" scope="page"></jsp:useBean>
<jsp:setProperty name="user" property="*"/>
<%
    user.ad
%>
</body>
</html>
