package Service;

import DAO.c3p0_JdbcUtils_DAO;
import SQL.c3p0_JdbcUtils;
import org.junit.Test;

import java.sql.SQLException;

public class c3p0_JdbcUtils_Service {
    private c3p0_JdbcUtils_DAO dao = new c3p0_JdbcUtils_DAO();
    @Test
    public void test() throws SQLException {
        try{
            c3p0_JdbcUtils.beginTransation();//开启事务
            dao.update("DYP",  -10000);
            dao.update("DYK",10000);
            c3p0_JdbcUtils.comitTransation();//提交事务


        }catch (Exception e){
            c3p0_JdbcUtils.rollbackTransation();
        }

    }
}
