package Demos;

import SQL.JdbcUtils;
import domain.Stu;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
* 简化jdbc代码
* */
public class dbutils_study {
    @Test
    public void add_stu(Stu stu) throws SQLException {
        //先标准化写法
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtils.getConnection();
            //插入数据
            String sql = "insert into stu values(?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,stu.getSid());
            pstmt.setString(2,stu.getName());
            pstmt.setString(3,stu.getGender());
            pstmt.setInt(4,stu.getAge());

            pstmt.executeUpdate();
        }catch (Exception e){

        }finally {
            if (con != null)con.close();
            if(pstmt!=null)pstmt.close();
        }
    }

    @Test
    public void update_stu(Stu stu) throws SQLException {
        //先标准化写法
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtils.getConnection();
            //插入数据
            String sql = "update stu set name = ?,age = ?,gender = ? where sid= ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(4,stu.getSid());
            pstmt.setString(1,stu.getName());
            pstmt.setString(3,stu.getGender());
            pstmt.setInt(2,stu.getAge());

            pstmt.executeUpdate();
        }catch (Exception e){

        }finally {
            if (con != null)con.close();
            if(pstmt!=null)pstmt.close();
        }
    }

    @Test
    public void delete_stu(String sid) throws SQLException {
        //先标准化写法
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JdbcUtils.getConnection();
            //插入数据
            String sql = "delete from stu where sid = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,sid);
            pstmt.executeUpdate();
        }catch (Exception e){

        }finally {
            if (con != null)con.close();
            if(pstmt!=null)pstmt.close();
        }
    }


    /*
    *按条件查询
     */
    @Test
    public Stu find_stu(String sid) throws SQLException {
        //先标准化写法
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = JdbcUtils.getConnection();
            //插入数据
            String sql = "select from stu where sid = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,sid);
            rs = pstmt.executeQuery();

            if(!rs.next())return null;

            //需要将rs转换成Stu对象，即rs->javabean(手动)
            Stu stu = new Stu();
            //根据列名将内容取出放到stu对象中
            stu.setSid(rs.getString("sid"));
            stu.setAge(rs.getInt("age"));
            stu.setGender(rs.getString("gender"));
            stu.setName(rs.getString("name"));

            return stu;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            if (con != null)con.close();
            if(pstmt!=null)pstmt.close();
            if(rs != null)rs.close();
        }
    }
}
