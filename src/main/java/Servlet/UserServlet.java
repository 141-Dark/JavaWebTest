package Servlet;

import Service.UserService;
import domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     *Servlet依赖于service，然后通过service完成功能，然后把结果保存到request中
     * 转发到jsp进行显示
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Service依赖于UserService（记不得的话查一下java三层架构）必须是一层依赖一层
        UserService userService = null;
        try {
            userService = new UserService();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //User user= userService.find();
        //最后保存到User对象中
        request.setAttribute("user","user");
        //转发到jsp页面中
         request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
