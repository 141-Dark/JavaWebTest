package Demos;

import cn.itcast.jdbc.JdbcUtils;
import domain.Stu;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
/*
*  写这个类只是为了理解DBUtils的调用方法，实际操作时可以直接调用现有的工具包（就是DBUtils）
* */
public class QR_test {
    @Test
    public void test1() throws SQLException {
        //Stu stu = new Stu("124","刁玉宽2","male12",22);
        //add_stu(stu);
        Stu stu = select_stu("124");
        System.out.println(stu);
    }

    public void add_stu(Stu stu) throws SQLException {
        QR qr = new QR(JdbcUtils.getDataSource());
        String sql = "insert into stu values(?,?,?,?)";

        Object params[] = {stu.getSid(),stu.getName(),stu.getGender(),stu.getAge()};
        //调用，执行增删改
        qr.update(sql,params);
    }

    public Stu select_stu(String sid) throws SQLException {
        QR qr = new QR(JdbcUtils.getDataSource());
        String sql = "select * from stu where sid = ?";
        Object params[]= {sid};

        RsHander<Stu> rh = new RsHander<Stu>() {
            @Override
            public Stu handle(ResultSet rs) throws SQLException {
                if(!rs.next())return null;

                //需要将rs转换成Stu对象，即rs->javabean(手动)
                Stu stu = new Stu();
                //根据列名将内容取出放到stu对象中
                stu.setSid(rs.getString("sid"));
                stu.setAge(rs.getInt("age"));
                stu.setGender(rs.getString("gender"));
                stu.setName(rs.getString("name"));

                return stu;
            }
        };
         return (Stu) qr.query(sql,rh,params);
    }
}
