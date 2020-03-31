package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FirstServlet")
public class FirstServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("test/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print("<h1>上写文路径："+request.getServletPath()+"</h1>");
        out.print("<h2>HTTP请求方式："+request.getMethod()+"</h2>");
        out.print("<h2>请求参数："+request.getQueryString()+"</h2>");
        out.print("<h2>uri："+request.getRequestURI()+"</h2>");
        out.print("<h2>HTTP请求地址："+request.getRequestURL()+"</h2>");
        out.print("<h2>servlet地址："+request.getServletPath()+"</h2>");
        //清空缓冲区数据
        out.flush();
        //关闭输入流
        out.close();
        //记得要到web.xml文档中配置
         /*<servlet>
        <servlet-name>FirstServlet</servlet-name>
        <servlet-class>Servlet.FirstServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>FirstServlet</servlet-name>
        <url-pattern>/Servlet/FirstServlet</url-pattern>
    </servlet-mapping>*/
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
