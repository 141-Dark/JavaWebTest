package Service;

import DAO.DaoFactory;
import DAO.UserDAO;
import domain.User;
import org.dom4j.DocumentException;

import java.io.IOException;
import java.sql.SQLException;

public class UserService {
    //service依赖于DAO层
    //把具体的实现类的创建隐藏到工厂当中，因为不可以直接把接口当做对象创建（UserDAO）
    private UserDAO userDAO = DaoFactory.getUserDao();

    public UserService() throws ClassNotFoundException {
    }
    //service查询必须使用DAO来实现
    /*public User find(){
        return UserDAO.find();
    }*/

    //写一个register方法
    public void regist(User user) throws UserException, DocumentException, IOException, SQLException {
        //如果找不到用户的ID则说明用户没有被注册过
        User _user = userDAO.findByUserID(user.getID());
        if (_user != null){
            throw new UserException("用户账号"+user.getID()+",已经被注册");
        }
        else{
            userDAO.add(user);
        }
    }

    public User login(User form) throws DocumentException, UserException, SQLException {
        /*
        * 使用user中的ID来查询用户
        * */
        User user = userDAO.findByUserID(form.getID());
        /*
        * 如果返回null则说明用户吧不存在，异常信息为“用户不存在”href='"+request.getContextPath()+"/webstudy/Login.jsp"+"'
        * */
        if(user == null){
            throw new UserException("用户不存在,请前往注册");
        }
        //比较输入的密码和数据库中的密码，如果密码不一致，抛出异常，返回异常信息为“密码错误，重新输入”
        if(!form.getPassWord().equals(user.getPassWord())){
            throw new UserException("密码错误，重新输入");
        }

        //返回数据库中的user（不返回form是因为form中的用户信息并不完全）
        return user;
    }
}
