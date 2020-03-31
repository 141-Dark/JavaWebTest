package DAO;

import domain.User;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.sql.SQLException;

public interface UserDAO {
    public void add(User user) throws DocumentException, IOException, SQLException;
    public User findByUserID(String UserID) throws DocumentException, SQLException;
}
