package Demos;

import DAO.AccountDAO;
import SQL.JdbcUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/*
* 通过事务演示数据库转账
* */
public class Transaction_Test {
    /*
    *由谁转，转给谁，转多少钱
    * 对同一个事务的操作，必须是在同一个Connection中
    * 由于与数据库相关的操作只能在DAO层出现，因此，在本demo中必须将Connection操作隐藏
     */
    public void ONE(String from,String to,double money) throws SQLException, IOException, ClassNotFoundException {
        Connection con= null;
        try{
            con= JdbcUtils.getConnection();
            //开启事务
            con.setAutoCommit(false);//是否自动提交

            //...一系列相关操作
            AccountDAO dao = new AccountDAO();
            dao.UpdateBalance(con,from,-money);//给from减去相应的金额

            if(true){
                throw new RuntimeException("转账失败");
            }
            dao.UpdateBalance(con,to,money);//给to加上相应的金额
            //提交事务
            con.commit();
            con.close();
        } catch (Exception e) {
            //回滚事务·
            con.rollback();
            con.close();
        }
        finally {
            if(con !=null) con.close();
        }
    }

    @Test
    public void test() throws Exception{
        ONE("DYK","DYP",133);
    }
}
