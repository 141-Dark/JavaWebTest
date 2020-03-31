package Servlet;

import Service.UserException;
import Service.UserService;
import cn.itcast.commons.CommonUtils;
import domain.User;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet( name = "LoginServlet" ,urlPatterns = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        /*
        * 获取表单数据，封装到form当中
        * 调用service中的login方法，得到返回的User对象
        * 如果有异常，将异常返回到request中，保存form，并将异常转发到Login.jsp中
        * 没有异常则跳转到另外一个网页（将登陆信息保存到session中，然后到另外一个网页显示）
        * */
        //依赖于service
        UserService userService = null;
        try {
            userService = new UserService();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);

        try{
            User user = userService.login(form);
            //没有异常时，保存到session中
            //request.getSession().setAttribute("sessionUser",user);获取User中的所有属性时User类中必须有toString方法

            request.getSession().setAttribute("UserName",user.getUserName());//获取用户名
            //跳转到网页
            response.sendRedirect(request.getContextPath()+"/webstudy/welcome.jsp");
        }
        catch (UserException | DocumentException | SQLException e){
            //将异常保存到request
            request.setAttribute("msg",e.getMessage());
            //保存form
            request.setAttribute("user",form);
            //异常转发
            request.getRequestDispatcher("/webstudy/Login.jsp").forward(request,response);
        }
    }

}
