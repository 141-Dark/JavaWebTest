<%--
  Created by IntelliJ IDEA.
  User: DYK
  Date: 2019/11/25
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>登录页面</title>
    <style type="text/css">
       #table1{
           border: aliceblue 2px ;
       }
        body{
            background-color: grey;
        }
        tr{
            background-color: aliceblue;
            border: aliceblue 1px dotted;
        }
    </style>
    <script language="JavaScript">
        function checkNull(form) {
            //检测表单内容有没有空
            for(i= 0;i<form.length;i++){
                if(form.elements[i].value == ""){
                    alert("抱歉，"+form.elements[i].title+"不能为空");
                    form.elements[i].focus();
                    return false;
                }
            }
            //判断两次密码是否相等
            var pwd1 = document.getElementById("PassWord").value;
            var pwd2 = document.getElementById("PassWord2").value;
            if (pwd1 != pwd2){
                alert("两次密码不一样，请重新输入");
                return  false;
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
<body >
<--判断用户是否是第一次登陆-->
<%
    String welcome = "你第一次访问本站";
    String[] info = new String[]{"","","","","",""};
    Cookie[] cook = request.getCookies();
    //判断·cookie内的内容是否为空,不为空时显示：XXX，欢迎回来
    if (cook!=null){
        for(int i = 0;i<cook.length;i++){
            if(cook[i].getName().equals("firstCookie")){//firstCookie要在show.jsp中创建
                info = cook[i].getValue().split("#");//#号隔开
                welcome = ",欢迎回来";
            }
        }
    }
    //response.setHeader("refresh","URL=show.jsp");
%>
<%=info[1]+welcome%>

<!--session测试发送到show.jsp-->
<%
//String sessionMesssage = "刁玉宽，你好";
//session.setAttribute("message",sessionMesssage);
    //${pageContext.request.contextPath}/
%>
<p>${msg}</p>
<form method="post" name="form1" action="${pageContext.request.contextPath}/registerServlet" onsubmit="checkNull(form1)" >
    <table id="table1">
        <tr>
            <td colspan="2">用户注册</td>
        </tr>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="userName" id="user" title="用户名" value="<%=info[0]%>"></td>
        </tr>
        <tr>
            <td>用户工号</td>
            <td><input type="text" name="ID" id="number" title="用户工号" value="<%=info[1]%>"></td>
        </tr>

        <tr>
            <td>密&nbsp码</td>
            <td><input type="password" name="passWord" id="pwd1" title="密码" value="<%=info[2]%>"></td>
        </tr>

        <tr>
            <td>确认密码</td>
            <td><input type="password" name="PassWord2" id="pwd2" title="确认密码" value="<%=info[3]%>"></td>
        </tr>

        <tr>
            <td>电话号码</td>
            <td><input type="text" name="phone" id="phone" title="电话号码" value="<%=info[4]%>"></td>
        </tr>

        <tr>
            <td>邮箱</td>
            <td><input type="text" name="email" id="email" title="邮箱" value="<%=info[5]%>"></td>
        </tr>

        <tr>
            <td></td>
            <td>
                <input type="submit" class="btn_grey" value="提交">
                <input type="reset" class="btn_grey" value="重置">
            </td>
        </tr>
    </table>

</form>
</body>
</html>