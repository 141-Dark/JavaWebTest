package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
* 通过一个Servlet来处理多个请求
* 客户端发送请求时，必须多给出一个参数来说明请求调用的方法
* 请求处理的值必须与service相同，即返回值和参数，以及声明的异常都要相同，唯一不同的是方法名
* 客户端必须传递名为method的参数（参数名可以任意）
* 在这里假设传递来的参数名为method
* */
@WebServlet(name = "Base_Servlet",urlPatterns = "/Base_Servlet")
public class Base_Servlet extends HttpServlet {

    protected String add_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("调用了add_user方法");
        return "f:/webstudy/register.jsp";
    }

    protected String delete_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("调用了delete_user方法");
        return "r:/webstudy/register.jsp";
    }

    protected void edit_user(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("调用了edit_user方法");
    }
}
