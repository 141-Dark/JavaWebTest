package Servlet;

import Service.CustomerService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import domain.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/*
* web层
* */
@WebServlet("/CustomerServlet")
public class CustomerServlet extends BaseServlet {

    private CustomerService customerService = new CustomerService();
    /*
    * 添加客户
    * 1、添加表单数据到对象
    * 2、自动添加cid，使用uuid
    * 3、调用service完成添加
    * 4、向request域中保存成功信息
    * 5、转发到msg.jsp中
    * */

    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Customer c = CommonUtils.toBean(request.getParameterMap(),Customer.class);
        c.setCid(CommonUtils.uuid());
        try {
            customerService.add(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //保存request
        request.setAttribute("msg","添加客户成功");
        return "f:msg.jsp";
    }
}
