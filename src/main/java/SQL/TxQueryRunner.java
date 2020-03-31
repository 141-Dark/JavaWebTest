package SQL;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.SQLException;
/*
* 这个类中的方法自己来处理连接的问题，
* 无需外界传递
* 处理方法：
*     通过c3p0_JdbcUtils.getConnection();得到连接，可能是事务连接，也可能是普通连接
*     通过c3p0_JdbcUtils.realseConnection(con);完成连接的释放，如果是普通连接则关闭
* */
public class TxQueryRunner extends QueryRunner {
    @Override
    public int[] batch(String sql, Object[][] params) throws SQLException {
        //得到连接
        Connection con = c3p0_JdbcUtils.getConnection();
        //执行父类方法,传递连接对象
        int[] result = super.batch(con,sql, params);
        //释放连接
        c3p0_JdbcUtils.realseConnection(con);
        return result;
    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
        //得到连接
        Connection con = c3p0_JdbcUtils.getConnection();
        //执行父类方法,传递连接对象
        T result = super.query(con,sql, params,rsh);
        //释放连接
        c3p0_JdbcUtils.realseConnection(con);
        return result;
    }

    @Override
    public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
        return super.query(conn, sql, rsh, params);
    }

    @Override
    public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
        //得到连接
        Connection con = c3p0_JdbcUtils.getConnection();
        //执行父类方法,传递连接对象
        T result = super.query(con,sql, rsh);
        //释放连接
        c3p0_JdbcUtils.realseConnection(con);
        return result;
    }

    @Override
    public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh) throws SQLException {
        //得到连接
        Connection con = c3p0_JdbcUtils.getConnection();
        //执行父类方法,传递连接对象
        T result = super.query(con,sql,rsh);
        //释放连接
        c3p0_JdbcUtils.realseConnection(con);
        return result;
    }

    @Override
    public <T> T query(String sql, Object param, ResultSetHandler<T> rsh) throws SQLException {
        //得到连接
        Connection con = c3p0_JdbcUtils.getConnection();
        //执行父类方法,传递连接对象
        T result = super.query(con,sql, param,rsh);
        //释放连接
        c3p0_JdbcUtils.realseConnection(con);
        return result;
    }

    @Override
    public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh) throws SQLException {
        //得到连接
        Connection con = c3p0_JdbcUtils.getConnection();
        //执行父类方法,传递连接对象
        T result = super.query(con,sql, params,rsh);
        //释放连接
        c3p0_JdbcUtils.realseConnection(con);
        return result;
    }

    @Override
    public int update(String sql) throws SQLException {
        //得到连接
        Connection con = c3p0_JdbcUtils.getConnection();
        //执行父类方法,传递连接对象
        int result = super.update(con,sql);
        //释放连接
        c3p0_JdbcUtils.realseConnection(con);
        return result;
    }

    @Override
    public int update(String sql, Object param) throws SQLException {
        //得到连接
        Connection con = c3p0_JdbcUtils.getConnection();
        //执行父类方法,传递连接对象
        int result = super.update(con,sql,param);
        //释放连接
        c3p0_JdbcUtils.realseConnection(con);
        return result;
    }

    @Override
    public int update(String sql, Object... params) throws SQLException {
        //得到连接
        Connection con = c3p0_JdbcUtils.getConnection();
        //执行父类方法,传递连接对象
        int result = super.update(con,sql,params);
        //释放连接
        c3p0_JdbcUtils.realseConnection(con);
        return result;
    }

    @Override
    public int update(Connection conn, String sql, Object... params) throws SQLException {
        //得到连接
        Connection con = c3p0_JdbcUtils.getConnection();
        //执行父类方法,传递连接对象
        int result = super.update(con,sql,params);
        //释放连接
        c3p0_JdbcUtils.realseConnection(con);
        return result;
    }
}
