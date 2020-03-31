package Test_DAO;


import SQL.JdbcUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

public class PrepareStarementTest {

    public boolean Test(String userid,String pwd) throws ClassNotFoundException, SQLException {
        //连接数据库
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaweb","root","root");
        //创建连接
        String sql = "select * from register where id = ? and pwd = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);//必须先绑定sql模板

        //为参数赋值
        preparedStatement.setString(1,userid);
        preparedStatement.setString(2,pwd);
        //执行查询
        ResultSet resultSet = preparedStatement.executeQuery();//executeQuery()中不用再添加sql语句，因为模板已经绑定
        return resultSet.next();
    }

    @Test
    public void fun1() throws SQLException, ClassNotFoundException {
        //可以防止sql攻击
        String id ="125";
        String pwd = "123";
        boolean bool = Test(id,pwd);
        System.out.println(bool);
    }

    @Test
    /*
    * 测试JdbcUtils是否能够连接数据库
    * */
    public void fun2() throws SQLException, IOException, ClassNotFoundException {
        Connection con = JdbcUtils.getConnection();
        System.out.println(con);
    }
}
