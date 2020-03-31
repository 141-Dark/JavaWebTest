package Test_DAO;

import org.junit.Test;

import java.sql.*;

public class ConnectionTest {
    @Test
    public  void ConnectionTest() throws ClassNotFoundException, SQLException {
        /*
         * jdbc四大配置参数
         *(1)driverClassName:com.mysql.jdbc.Driver
         *(2)url:   jdbc:mysql://localhost:3306/数据库名
         *(3)username
         *(4)password
         * */
        //加载类
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/javaweb";

        String user = "root";
        String password = "root";

        //得到连接对象
        Connection con = DriverManager.getConnection(url,user,password);
        System.out.println(con+"，加载成功");

        /**
         * 通过con创建Statement
         * 通过Statement向数据库发送语句
         * 调用（返回int代表影响的行数）int ExecuteUpdate(string sql),他可以发送DML（增删改语句），DDL（创建表、数据库等）
         */
        //通过con得到Statement
        Statement statement = con.createStatement();
        //通过Statement发送sql语句
        String string = "insert into register values('124','刁玉宽','123','15188118783','1418273501')";//增加
        //String string = "update register set name ='刁玉宽二号',email='1418273501@qq.com' where id = '123'";//更改
        //String string = "delete from register";//删除，返回行数为表中所有的行
        //执行并返回影响的行数
        int number = statement.executeUpdate(string);
        System.out.println(number);
    }
    @Test
    public void SelectTest() throws ClassNotFoundException, SQLException {
        /*
        *一、连接数据库、四大参数
        *二、通过con得到statement
        *三、获取查询到的表，进行解析
        * */
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/javaweb";
        String name ="root";
        String password = "root";

        Connection con = DriverManager.getConnection(url,name,password);

        //通过con得到statement
        Statement statement = con.createStatement();
        /*
        * 调用Statement的ExcuteQuery对象返回一个结果集
        * ResultSet rs = statement.executequery(String querySql)
        * */
        String querySql ="select * from register";
        ResultSet resultSet = statement.executeQuery(querySql);

        //解析resultset
        /*
        * 把光标移动到第一行可以调用next()方法，返回一个boolean值，代表下一行是否存在（光标开始时在虚拟的第一行）
        * */
        //判断是否有下一行，若果有，则读取数据
        while (resultSet.next()){
            int id = resultSet.getInt(1);//通过列编号来获取值
            String username = resultSet.getString("name");//通过列名来获取
            int tel = resultSet.getInt(3);
            System.out.println(id+","+username+","+tel);
        }

        //关闭资源，倒着关，即先开的后关
        resultSet.close();
        statement.close();
        con.close();
    }

    @Test
    //jdbc代码标准化
    public void fun() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaweb","root","root");

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from register");

            //循环遍历，打印其中数据
            while (resultSet.next()){
                int id = resultSet.getInt(1);//通过列编号来获取值
                String username = resultSet.getString("name");//通过列名来获取
                int tel = resultSet.getInt(3);
                System.out.println(id+","+username+","+tel);
            }
        }
        catch (Exception e){

        }
        //抛出异常前要执行finally
        finally {
            //可能会出现空指针异常，
            if(resultSet != null) resultSet.close();
            if(statement!=null) statement.close();
            if(connection!=null)connection.close();
        }
    }
}
