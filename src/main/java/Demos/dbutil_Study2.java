package Demos;

import cn.itcast.jdbc.JdbcUtils;
import com.sun.faces.renderkit.html_basic.HtmlBasicRenderer;
import domain.Stu;
import org.apache.commons.configuration2.tree.QueryResult;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.StatementConfiguration;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 通过dbutils工具直接来写测试
 * **/
public class dbutil_Study2 {
    @Test
    public void test() throws SQLException, IOException, ClassNotFoundException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "insert into stu values(?,?,?,?)";
        Object params[] = {"125","刁玉宽3","male",22};
        //调用，执行增删改
        qr.update(sql,params);
    }

    /*
    * 打印单行记录
    * */
    @Test
    public void test2() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from stu where sid = ?";
        Object params[]= {125};
       /* ResultSetHandler<Stu> rsh = new ResultSetHandler<Stu>() {
            @Override
            public Stu handle(ResultSet resultSet) throws SQLException {
                if(!rs.next())return null;

                //需要将rs转换成Stu对象，即rs->javabean(手动)
                Stu stu = new Stu();
                //根据列名将内容取出放到stu对象中
                stu.setSid(rs.getString("sid"));
                stu.setAge(rs.getInt("age"));
                stu.setGender(rs.getString("gender"));
                stu.setName(rs.getString("name"));

                return stu;
            }
        };

        Stu stu = qr.query(sql,rsh,params);*/
        //也可以这样写
        //BeanHandler将指定的结果集转换成对象
        Stu stu = qr.query(sql,new BeanHandler<Stu>(Stu.class),params);
        System.out.println(stu);
    }

    /*
     * 打印多行记录
     * 每行对应一个map对象
     * */
    @Test
    public void test3() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from stu";

        List<Stu> stu = (List<Stu>) qr.query(sql, new BeanListHandler<Stu>(Stu.class));
        System.out.println(stu);
    }

    /**
     * map有大括号[]
     * **/

    /*
    * 打印单行，对应一个map对象
    * */
    @Test
    public void test4() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from stu where sid = ?";
        Object[] params = {123};

        Map map =  qr.query(sql, new MapHandler(),params);
        System.out.println(map);
    }

    /*
     * 打印多行，每行对应一个map对象
     * */
    @Test
    public void test5() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select * from stu ";

        List<Map<String,Object>> map =  qr.query(sql, new MapListHandler());
        System.out.println(map);
    }


    /*
     * 打印单行单列
     * */
    @Test
    public void test6() throws SQLException {
        QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
        String sql = "select count(*) from stu ";

        long cnt =  qr.query(sql, new ScalarHandler<>());
        System.out.println(cnt);
    }

}
