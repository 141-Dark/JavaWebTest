<%--
  Created by IntelliJ IDEA.
  User: DYK
  Date: 2019/12/20
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <style type="text/css">
        .table-striped {
            margin-left:40%;
            margin-top:10%;

        }
        hr{
            color: grey;
        }
        .center-block {
            margin-left:44%;
        }
        .qq {
            background-image:url("Image/BackgroundPhoto/qq.png");
        }

        ul {
            margin-top:1px;
            margin-left:38%;
            float:left;
            display:block;
        }
        li {
            margin-left:5px;
            font-size:20px;
            list-style-type:none;
            display:inline;
        }
        #Button1{
            width: 241px;
        }
    </style>
    <script type="text/javascript">
        function checkNull(form) {
            //检查表单内容是否为空
            for (i = 0; i < form.length; i++) {
                if (form.elements[i].value == "") {
                    alert("抱歉" + form.elements[i].title + "不能为空");
                    form.elements[i].focus();
                    return false;
                }
            }
        }
    </script>
</head>
<body>
<%
    //获取welcome.jsp的cookie用于保存
    String message = "你是第一次登录页面";
    String[] info = new String[]{"",""};
    Cookie[] cookies = request.getCookies();

    //cookies不为空时，遍历所有cookie找到welcom.jsp中创建的cookie
    if(cookies!=null){
        for (int i = 0;i < cookies.length;i++){
            if (cookies[i].getName().equals("cookie")){
                //保存用户信息
                info = cookies[i].getValue().split("#");
                message = ",欢迎回来";
            }
        }
    }
    else {
        message ="cookie为空";
    }
    //response.setHeader("refresh","URL=welcome.jsp");
%>
<div class="container">
    <div class="row">
        <%=info[1]+message%>
        <p>${msg}</p>
        <form id="form1" method="post" title="form1" name="form1" class="bs-example bs-example-form "  onsubmit="checkNull(form1)" action="${pageContext.request.contextPath}/LoginServlet">
            <table class="table-striped" >
                <caption  style="text-align:center"><h1>用户登录</h1></caption>
                <tr style="margin-bottom:10px;">
                    <td><input type="text" name="ID" id="ID"  title="账号" ID="TextBox1" class="form-control control-label ID" Width="241px"  placeholder="输入账号" OnTextChanged="TextBox1_TextChanged" ></td>
                </tr>

                <tr>
                    <td>
                        <input type="text" name="passWord" ID="TextBox2" title="密码" class="form-control"  Width="241px" placeholder="输入密码">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" ID="Button1" class="btn  btn-primary" data-loading-text="Loading..." value="登录"  OnClick="Button1_Click" />
                    </td>
                </tr>
            </table>

            <br />
            <span class="center-block">使用第三方账号登录</span>
            <hr style="font-size:2px"/>

            <ul>
                <li><img ID="ImageButton1"  Height="45px" Width="58px" ImageUrl="Image/qq.png" /></li>
                <li>
                    <img ID="ImageButton2"  Height="45px" Width="58px" ImageUrl="Image/weixin.png" />
                </li>
                <li>
                    <img ID="ImageButton3"  Height="45px" Width="58px" ImageUrl="Image/weibo.png"  />
                </li>
            </ul>


        </form>
    </div>
</div>
</body>
</html>
