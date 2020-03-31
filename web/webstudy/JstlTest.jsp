<%--
  Created by IntelliJ IDEA.
  User: DYK
  Date: 2019/12/1
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:set var="code" value="<script>alert('jstl标签库测试')</script>" scope="request"/>
<c:out value="${code}" escapeXml="true"/>
</body>
</html>
