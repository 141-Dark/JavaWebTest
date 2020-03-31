package DAO;

import cn.itcast.jdbc.TxQueryRunner;
import domain.Customer;
import org.apache.commons.dbutils.QueryRunner;


import java.sql.SQLException;

/*
* 持久层，操作数据库
* */
public class CustomerDAO {
    private QueryRunner qr = new TxQueryRunner();

    /*
    * 添加客户
    * */
    public void add(Customer c) {
        try{
            String sql = "insert into customer values(?,?,?,?,?,?,?)";
            Object[] params = {c.getCid(),c.getCname(),c.getGender(),c.getBirthday(),c.getCellphone(),c.getEmail(),c.getDescription()};
            qr.update(sql,params);
        }catch (Exception e){
            throw new RuntimeException("报错");
        }

    }
}
