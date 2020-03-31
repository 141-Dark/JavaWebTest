package DAO;

import SQL.JdbcUtils;
import SQL.TxQueryRunner;
import SQL.c3p0_JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

public class c3p0_JdbcUtils_DAO {
    public static void update(String name,double money) throws SQLException {
        QueryRunner qr = new TxQueryRunner();
        String sql = "update account set balance = balance+? where name = ?";
        Object[] params ={money,name};
        //我们需要自己提供连接，保证多次使用的都是同一个连接
        Connection con = c3p0_JdbcUtils.getConnection();
        qr.update(sql,params);
        //释放连接
        c3p0_JdbcUtils.realseConnection(con);
    }
}
