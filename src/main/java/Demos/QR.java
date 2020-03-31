package Demos;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
* 定义一个泛型类
* 写这个类只是为了理解DBUtils的内部结构，实际操作时可以直接调用现有的工具包（就是DBUtils）
* */
public class QR <T> {
    private DataSource dataSource;

    public QR() {
        super();
    }

    public QR(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /*
     * z做delete，update，insert
     * */
    public int update(String sql, Object... params) throws SQLException {
        //先标准化写法
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = dataSource.getConnection();//通过连接池得到连接对象
            //插入数据
            pstmt = con.prepareStatement(sql);

            //初始化参数
            initParams(pstmt, params);
            return pstmt.executeUpdate();
        } catch (Exception e) {
                throw new RuntimeException(e);

        } finally {
            if (con != null) con.close();
            if (pstmt != null) pstmt.close();
        }
    }



    //Object... params代表一个数组
    public T query(String sql,RsHander<T> rh,Object... params) throws SQLException {
        //先标准化写法
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();//通过连接池得到连接对象
            //插入数据
            pstmt = con.prepareStatement(sql);

            //初始化参数
            initParams(pstmt, params);
            rs =  pstmt.executeQuery();
            return rh.handle(rs);//将rs传出去，即将rs变成一个T类型
        } catch (Exception e) {
            throw new RuntimeException(e);

        } finally {
            if (con != null) con.close();
            if (pstmt != null) pstmt.close();
        }

    }


    //初始化参数,专门用来赋参数，Object... params代表？号数组
    private void initParams(PreparedStatement pstmt,Object... params) throws SQLException {
        for(int i =0;i<params.length;i++){
            pstmt.setObject(i+1,params[i]);
        }
    }
}

/*
* 定义一个泛型接口
* 用来把结果集转换成需要的类型
* */
interface RsHander<T>{
    public T handle(ResultSet rs) throws SQLException;
}