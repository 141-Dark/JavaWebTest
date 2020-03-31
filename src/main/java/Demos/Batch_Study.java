package Demos;

import SQL.JdbcUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
* 批处理(只与增删改有关，与查询无关)
* */
public class Batch_Study {
    @Test
    public void test1() throws SQLException, IOException, ClassNotFoundException {
       /*
       *PreparedStatement对象内有几何（批）
       * 1、用循环疯狂向pstmt中添加sql参数，它有自己的参数，使用一组参数与模板就可以匹配出一条sql语句
       *2、调用执行批方法，完成数据库操作
       * */
        Connection con = JdbcUtils.getConnection();
        String sql = "insert into register values(?,?,?,?,?)";
        PreparedStatement pstmt =  con.prepareStatement(sql);

        //疯狂向pstmt中添加参数
        for(int i = 0;i < 1000;i++){
            pstmt.setString(1,"NO"+i);
            pstmt.setString(2,"步行者"+i+"号");
            pstmt.setString(3,"123");
            pstmt.setString(4,"201713453"+i);
            pstmt.setString(5,"1418273501@qq.com");

            pstmt.addBatch();//添加批，将这一组参数保存到集合中
        }
        long start = System.currentTimeMillis();//获取添加批所用的时间
        pstmt.executeBatch();//执行批
        long end = System.currentTimeMillis();//获取执行批所用的时间
        //时间太慢，需要在配置文件的url后面添加语句？rewriteBatchedStatements=true
        System.out.println("添加批所用的时间为："+start+"  执行批所用的时间为："+end);

    }
}
