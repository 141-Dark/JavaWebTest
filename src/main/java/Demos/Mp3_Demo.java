package Demos;

import SQL.JdbcUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.io.OutputFormat;
import org.junit.Test;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.*;

public class Mp3_Demo {
    @Test
    //把MP3文件保存到数据库中
    public void save() throws SQLException, IOException, ClassNotFoundException {
        //得到connection
        Connection con = JdbcUtils.getConnection();
        //给出模板
        String sql = "insert into mp3 values(?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        //设置参数
        pstmt.setInt(1,1);
        pstmt.setString(2,"escape this moment.mp3");

        /*需要得到blob（二进制大数据，即MP3文件的大小）
        * 1、需要将文件变成byte[]数组
        * 2、使用byte[]创建Blob
        * */
        byte[] bytes = IOUtils.toByteArray(new FileInputStream("src/MUSIC/Escape This Moment.mp3"));
        Blob blob = new SerialBlob(bytes);

        pstmt.setBlob(3,blob);

        //调用执行语句
        pstmt.executeUpdate();
    }
    @Test
    //从数据库中读取MP3
    public void read_sql() throws SQLException, IOException, ClassNotFoundException {
        //建立连接
        Connection con = JdbcUtils.getConnection();
        //给出Select语句模板，然后执行
        String sql = "select * from mp3 where id = ?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,1);
        //执行
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            //获取rs中名为DATA的数据
            Blob blob = rs.getBlob("DATA");
            System.out.println(blob);

            /*把取出来的值变成文件
            * 1、通过blob得到输入流
            * 2、自己创建输出流
            * 3、将输入流的数据写到输出流中
            * */
            InputStream inputStream = blob.getBinaryStream();
            OutputStream outputStream = new FileOutputStream("src/MUSIC/数据库歌曲测试.mp3");
            //使用copy方法将输入流数据写到输出流
            IOUtils.copy(inputStream,outputStream);
        }
        con.close();
    }
}
