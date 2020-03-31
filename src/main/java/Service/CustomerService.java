package Service;

import DAO.CustomerDAO;
import domain.Customer;

import java.sql.SQLException;

/*
* 业务层，依赖DAO
* */
public class CustomerService {
    private CustomerDAO customerDAO= new CustomerDAO();
    /**
     * 添加客户
     * **/
    public void add(Customer c) throws SQLException {
        customerDAO.add(c);
    }
}
