package SQL;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//写之前先写配置文件
public class  JdbcUtils {
    //如果有多个连接需要加载，为了节省时间，配置文件只要加载一次就可以，此时需要写一个静态方法
    private static Properties properties = null;
    static {
        try{
            /*
             * （1）加载配置文件(配置文件必须直接写在src目录下)
             * （2）加载驱动
             * （3）加载DriverManager.getConnection
             *  cn.itcast.jdbc.
             * */
            //加载配置文件
            InputStream in = cn.itcast.jdbc.JdbcUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
            properties = new Properties();
            properties.load(in);
        }
        catch (Exception e){
            throw new RuntimeException();
        }
        //加载驱动
        try{
            Class.forName(properties.getProperty("driver"));
        }
        catch (Exception e){
            throw new RuntimeException();
        }
    }

    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        //加载DriverManager.getConnection
        return DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"),properties.getProperty("pwd"));
    }
}
