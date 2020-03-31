package DAO;

import SQL.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

/*
 * 通过事务演示数据库转账
 * */
public class AccountDAO {
    /*
     * 修改指用户的余额
     * 这里必须先指定连接，否则不能保证使用同一个连接，违背了事务使用的法则
     * */
    public void UpdateBalance(Connection con,String name, double balance) {
        try{
            String sql = "update account set balance = balance+? where name = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setDouble(1,balance);
            pstmt.setString(2,name);

            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
