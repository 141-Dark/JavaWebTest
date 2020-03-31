package Servlet;

import Service.UserException;
import Service.UserService;
import cn.itcast.commons.CommonUtils;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;
import org.dom4j.DocumentException;

/*
* User相关的功能只需要定义一个类即可
* 其余的要对应多个servlet
* */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        UserService userService = null;
        try {
            userService = new UserService();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /*
         *封装表单数据（封装到user中）
         * 封装时jsp中的name属性名称要与User中的名称一样
         * */
        //User form = CommonUtils.toBean(request.getParameterMap(),User.class);
        User form = CommonUtils.toBean(request.getParameterMap(),User.class);

        /*
        * 调用UserService中的regist方法传递form
        * 得到异常：获取异常信息，保存到request域中，然后转发到register.jsp中显示
        * 没有异常：直接输出注册成功
        * */
        try {
            userService.regist(form);
            response.getWriter().print("<h1>注册成功</h1>,<a href='"+request.getContextPath()+"/webstudy/Login.jsp"+"'>点击跳转到登录页面</a>");
        } catch (UserException e) {
            //获取异常信息，保存到request域中
            request.setAttribute("msg",e.getMessage());
            //转发到register.jsp中显示
            request.getRequestDispatcher("/webstudy/register.jsp").forward(request,response);
        } catch (DocumentException e) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
