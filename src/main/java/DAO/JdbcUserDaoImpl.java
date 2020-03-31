package DAO;

import SQL.JdbcUtils;
import domain.User;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
* 针对数据库得实现
* */
public class JdbcUserDaoImpl implements UserDAO {
    @Override
    public void add(User user) throws DocumentException, IOException, SQLException {
        /*
         * 可以试着标准化
         * */
        Connection con = null;
        PreparedStatement pstmt = null;
        //通过配置文件得到连接
        try {
            con  = JdbcUtils.getConnection();
            //准备sql模板
            String sql="insert into register values(?,?,?,?,?)";
            //预编译语句
            pstmt = con.prepareStatement(sql);
            //为问号赋值
            pstmt.setString(1,user.getID());
            pstmt.setString(2,user.getUserName());
            pstmt.setString(3,user.getPassWord());
            pstmt.setString(4,user.getEmail());
            pstmt.setString(5,user.getPhone());
            //执行语句
            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(pstmt != null)pstmt.close();
            if(con != null)con.close();
        }
    }

    @Override
    public User findByUserID(String UserID) throws DocumentException, SQLException {
        /*
         * 可以试着标准化
         * */
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        //通过配置文件得到连接
        try {
            con = JdbcUtils.getConnection();
            //准备sql模板
            String sql = "select * from register where id = ?";
            //预编译语句
            pstmt = con.prepareStatement(sql);
            //为问号赋值
            pstmt.setString(1, UserID);
            //执行语句
            rs = pstmt.executeQuery();

            if (rs == null) {
                return null;
            }
            //如果有下一行
            if (rs.next()) {
                //转换成User（因为rs是jdbc的东西，如果直接返回，可能会暴露使用的数据库类型）
                //这叫ORM映射（对象关系映射）
                User user = new User();
                user.setID(rs.getString("id"));
                user.setUserName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassWord(rs.getString("pwd"));
                user.setPhone(rs.getString("tel"));

                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (pstmt != null) pstmt.close();
            if (con != null) con.close();
        }
    }
}
