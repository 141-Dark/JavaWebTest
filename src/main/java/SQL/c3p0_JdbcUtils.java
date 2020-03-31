package SQL;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
/*
* 以后主要使用这个，少用JdbcUtils了，这个更简单
* 可以调用的方法有：1、连接。2、返回连接池对象。3、开启事务。4、提交事务。5、回滚事务
* */
public class c3p0_JdbcUtils {
    //配置文件的默认配置，要求必须给出c3p0-config.xml文件
    static private ComboPooledDataSource dataSource = new ComboPooledDataSource();

    //创建一个事务专用连接(防止多线程使用)
    private static  ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    //装饰类增强getConnection方法
    public static Connection getConnection() throws SQLException {
        Connection con = tl.get();//获取本线程的con
        //如果con不为null，说明已经调用过beginTrasation()方法，即已经开起了事务
        if(con!=null) return con;

        System.out.println("开启了c3p0的连接模式");

        //没有开启事务
        return dataSource.getConnection();
    }

    //返回连接池对象
    public  static ComboPooledDataSource getDataSource(){
        return dataSource;
    }

    /*
    *开启事务
    * 获取一个connection,设置它的setAutoCommit(false)
    * 还要保证dao中使用的连接是我们刚刚创建的
    * */


    public  static void beginTransation() throws SQLException {
        Connection con = tl.get();//获取本线程
        //防止已经开启过事务
        if (con != null) throw  new RuntimeException("重复开启事务");
        //给con赋值,直接调用本类中的方法
        con = getConnection();
        //con设置为手动提交
        con.setAutoCommit(false);

        tl.set(con);//把当前线程的连接保存起来
    }

    /*
    * 提交事务
    * 获取beginTransation()提供的Connection（因为connection都必须是一个），然后调用commit方法
    * */
    public  static void comitTransation() throws SQLException {
        Connection con = tl.get();//获取本线程的con
        //防止没有开启事务直接调用
        if (con == null) throw  new RuntimeException("事务没有开启");
        //直接使用con.commit()
        con.commit();
        con.close();
        //关闭连接不代表将连接字符清空，如果不清空下次创建连接时还是本次的连接，原因请看getConnection方法
        //con = null;
        tl.remove();//移除连接

    }

    /*
     * 回滚事务
     * 获取beginTransation()提供的Connection（因为connection都必须是一个），然后调用commit方法
     * */
    public  static void rollbackTransation() throws SQLException {
        Connection con = tl.get();//获取本线程的con
        //防止没有开启事务直接调用
        if (con == null) throw  new RuntimeException("事务没有开启");
        //直接使用con.rollback()
        con.rollback();
        con.close();
        //关闭连接不代表将连接字符清空，如果不清空下次创建连接时还是本次的连接，原因请看getConnection方法
        //con = null;
        tl.remove();//移除连接
    }
    /*
    * 关闭连接，防止只打开了事务却没有进行相关的操作，不能提交
    * */
    public static  void realseConnection(Connection connection) throws SQLException {
        /**
         * 判断连接是否是事务专用，如果是事务专用就关闭，不是事务专用就不关闭
         * **/
        Connection con = tl.get();//获取当前线程
        //如果con==null，那么说明现在没有事务，则connection一定不是事务专用
        if(con == null)connection.close();

        //如果con!=null,那么需要判断参数连接是否与事务专用连接相等，如果不等说明参数连接不是事务专用连接
        if(con != connection)connection.close();

    }
}
