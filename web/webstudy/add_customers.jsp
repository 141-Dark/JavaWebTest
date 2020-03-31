<%--
  Created by IntelliJ IDEA.
  User: DYK
  Date: 2020/2/19
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>添加客户</title>
    <style type="text/css">
        .main{
            width: 900px;
        }
    </style>
    <script type="text/javascript">
        function checkNull(form) {
            //检查表单是否有空
            for(i = 0;i<form.length;i++){
                if(form.element[i].value==""){
                    alert("抱歉，"+form.element[i].title+"不能为空");
                    form.elements[i].focus();
                    return false;
                }
            }
            //判断号码的格式是否正确(方便测试，所以先注释)
            /*var phone = document.getElementById("phone").value;
            //手机号码正则表达式
            var reg = /^(86)?((13\d{9})|(15[0,1,2,3,4,5,6,7,8,9]\d{8})|(18[0,5,6,7,8,9]\d{8}))$/;
            //创建正则表达式
            var obj = new RegExp(reg);
            if(obj.test(phone)==false){
                alert("你输入的手机号不对，请重新输入");
                return false;
            }*/

            //判断邮箱是否正确(方便测试，所以先注释)
            /*var email = document.getElementById("email").value;
            //手机号码正则表达式
            var reg =/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
            //创建正则表达式
            var obj = new RegExp(reg);
            if(obj.test(email)==false){
                alert("你输入的邮箱号不对，请重新输入");
                return false;
            }*/
        }
    </script>
</head>
<body class="main">
<form action="${pageContext.request.contextPath}/CustomerServlet" method="post">
    <!--向servlet传递一个名为method（根据BaseServlet）的参数，指明要调用的方法-->
    <input type="hidden" name="method" value="add">
    <h3 align="center">添加客户</h3>
    <table class="table table-hover" border="0" align="center" width="40%" style="margin-left: 20%">
        <tr>
            <td>客户名称</td>
            <td><input type="text"ID="TextBox1" class="form-control control-label ID" Width="100px"  placeholder="输入姓名" name="cname" title="客户名"></td>
        </tr>
        <tr>
            <td>客户性别</td>
            <td>
                <input type="radio" name="gender" value="男" id="male">
                <label for="male">男</label>
                <input type="radio" name="gender" value="男" id="female">
                <label for="male">女</label>
            </td>
        </tr>
        <tr>
            <td>客户生日</td>
            <td><input type="date" ID="TextBox3" class="form-control control-label ID" Width="100px" name="birthday"  placeholder="年/月/日" title="生日"></td>
        </tr>
        <tr>
            <td>客户手机</td>
            <td><input type="tel" ID="TextBox4" class="form-control control-label ID" Width="100px"  placeholder="输入手机" name="cellphone" title="手机号码"></td>
        </tr>
        <tr>
            <td>客户邮箱</td>
            <td><input type="email"ID="TextBox5" class="form-control control-label ID" Width="100px"  placeholder="输入邮箱" name="email" title="邮箱"></td>
        </tr>
        <tr>
            <td>客户描述</td>
            <td><textarea name="description" cols="30" rows="3" class="form-control control-label" title="描述"></textarea></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" class="btn  btn-primary" value="提交">
                <input type="reset" class="btn  btn-primary" value="重置">
            </td>
        </tr>
    </table>

</form>
</body>
</html>
