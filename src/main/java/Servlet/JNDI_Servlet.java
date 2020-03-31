package Servlet;

import com.mchange.v2.c3p0.DataSources;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/*
* 获取JNDI的资源
* */
@WebServlet(name = "JNDI_Servlet")
public class JNDI_Servlet extends HttpServlet {
    public JNDI_Servlet() throws NamingException, SQLException {
        //创建一个JNDI的上下文对象
        Context ctx = new InitialContext();

        //将查找出来的入口赋值给另外的一个上下文
        Context envContext = (Context) ctx.lookup("java:comp/env");//java:comp/env是固定写法

        //根据资源（就是配置的JNDI）名称进行二次查询，找到资源
        DataSource dataSource = (DataSource) envContext.lookup("jdbc/mysql");
        //也可以直接DataSource dataSource = (DataSource) envContext.lookup("java:comp/env/jdbc/mysql");

        Connection con = dataSource.getConnection();
        System.out.println(con);
        con.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
