package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        /**
         * 在这里获取参数，来识别用户想调用的方法
         * **/
        String method_name = req.getParameter("method");

        //如果用户没有传递参数，则抛出异常
        if(method_name == null||method_name.trim().isEmpty()){
            throw new RuntimeException("你没有传递参数");
        }
        /**
         * 通过反射来调用方法
         * 我们需要的是本类的方法，所以我们应该先得到本类的class
         * **/
        Class c = this.getClass();
        Method method = null;

        //在找不到本类的方法的情况下做处理
        try{
            //参数必须为method_name，后两个参数与方法的参数必须保持一致
            method = c.getMethod(method_name,HttpServletRequest.class,HttpServletRequest.class);
        }
        catch (Exception e){
            throw new RuntimeException("方法不存在");
        }

        //调用(invoke)method方法
        try {
            String result = (String) method.invoke(this,req,resp);

            //如果用户返回的字符串为null或者""，那么我们什么也不做
            if(result==null||result.trim().isEmpty()){
                return;
            }
            /*
             * 获取返回的字符串
             * 帮它完成转发或重定向
             * 查看返回的字符串是否含有冒号，如果没有就默认表示转发，有就分割字符串得到前缀和后缀
             * 如果前缀是f表示转发，如果是r表示重定向，后缀就是路径
             * */
            if (result.contains(":")){
                //获取冒号的位置
                int index = result.indexOf(":");
                //从0开始截取
                String s = result.substring(0,index);
                //截取后缀
                String path = result.substring(index+1);

                if(s.equalsIgnoreCase("r")){
                    resp.sendRedirect(req.getContextPath()+path);
                }
                else if(s.equalsIgnoreCase("f")){
                    req.getRequestDispatcher(path).forward(req,resp);
                }else{
                    throw  new RuntimeException("你给定的字符"+s+"不符合规范");
                }
            }else {//如果没有冒号，默认转发
                req.getRequestDispatcher(result).forward(req,resp);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println("你调用的方法"+method_name+"出了异常");
            e.printStackTrace();
        }
    }
}
