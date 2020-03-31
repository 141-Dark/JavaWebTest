package Demos;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class dbcp_Study {
    /*
    * 数据库连接池的学习使用（需要添加两个依赖，dbcp.jar,以及pool.jar）
    * 使用两个不同的连接池分别是dbpc和c3p0（需要导入c3p0.jar和mchange.jar两个包），步骤一样
    * c3p0用得更多
    * */
    @Test
    public void test() throws SQLException {
        /*
        * 1、创建连接池对象
        * 2、配置数据库连接四大参数
        * 3、配置池参数（有默认值，可以不配）
        * 4、得到连接对象
        * 注意：连接池返回的Connection对象，它的close()方法与众不同，不是关闭，而是将池返回给池
        * */

        //创建连接对象（即必须实现的接口）
        BasicDataSource dataSource = new BasicDataSource();
        //配置四大参数
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/javaweb");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        //设置池参数
        dataSource.setMinIdle(3);//设置最小空闲连接
        dataSource.setMaxWaitMillis(1000);//设置最大的等待时间（单位：毫秒）

        //得到连接对象
        Connection con = dataSource.getConnection();
    }

    /*
    *c3p0的使用（通过配置文件）
    * 但是这里可以不指定配置文件，因为配置文件名称比必须是c3p0-config.xml,必须放在src文件下
    * */
    @Test
    public void test2() throws SQLException {
        //开启连接池
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        //直接调用
        Connection con = dataSource.getConnection();

        //打印看看
        System.out.println(con);
        //将连接返回到连接池
        con.close();
    }
}
