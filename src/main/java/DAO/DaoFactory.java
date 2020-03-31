package DAO;

import java.io.InputStream;
import java.util.Properties;

public class DaoFactory {
    /*
    *返回一个具体的UserDao实现对象
    * */
    private static Properties properties;
    //只需要执行一次，
    static {
        /*
         * 给出一个配置文件，文件中给出UserDAO接口的实现类名称
         * 获取实现类的名称，通过反射完成创建对象
         * 通过接口方法名得到类名，完成创建
         * */
        try {
            InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
            properties = new Properties();
            properties.load(in);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public static UserDAO getUserDao() throws ClassNotFoundException {
        //得到DAO实现类（UserDAOImpl）的名称
        String getClassName = properties.getProperty("DAO.UserDAO");
        //通过反射来实现类的对象
        try{
            Class clazz = Class.forName(getClassName);
            return (UserDAO) clazz.newInstance();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
