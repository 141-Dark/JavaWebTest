<%--
  Created by IntelliJ IDEA.
  User: DYK
  Date: 2019/12/26
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Title</title>
    <style type="text/css">
        .li5 {
            color:aliceblue;
            margin-left:80px;
            font-size:20px;
            list-style-type:none;
            display:inline;
        }
        .ul2 {
            margin-top:40px;
            margin-left:22%;
            float:left;
            display:block;
            padding:8px;
        }
        .d1{
            margin-left: 40%;
            margin-top: 20px;
        }
        .d2{
            margin-top: 100px;
            height: 400px;
        }
        .i1{
            height: 300px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12 d1"><h1>商务管理系统</h1></div>
        <div class="col-md-12">
            <ul class="ul2">
                <li class="li5"><a href="webstudy/add_customers.jsp" target="1"><span class="glyphicon glyphicon-home"></span>添加客户</a></li>
                <li class="li5"><a href="#" target="1"><span class="glyphicon glyphicon-shopping-cart"></span>查询客户</a></li>
                <li class="li5"><a href="#" target="1"><span class="glyphicon glyphicon-flag"></span>高级搜索</a></li>
            </ul>
        </div>

        <div class="col-md-12 d2">
            <iframe class="col-md-12 i1" name="1"></iframe>
        </div>
    </div>
</div>

</body>
</html>
