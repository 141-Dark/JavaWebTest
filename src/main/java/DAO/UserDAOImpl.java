package DAO;

import domain.User;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;

/*
*这是三层架构中最低层
* 作用：（1）写一个查询方法，来连接数据库，数据库可以是xml文件（首先用xml文档来练手）
* 在这里没有连接数据库，所以查询时只写到这一步
* 把数据从xml中取出来，然后将数据封装到domain中的User（封装时面向对象的精髓之一）
* （2）根据用户的ID来实现查找用户是否存在
* （3）如果用户不存在，则添加用户信息
* */
public class UserDAOImpl implements UserDAO {
    //（1）第一个功能（没有引用，已作废）
    public static User find()
    {
        return new User("刁玉宽","123");
    }

    String path1 = "F:/java文件/JavaWebTest/users.xml";//依赖数据文件（这里的“数据库”文件所在位置）
    File path = new File(path1);
    /*
    *（2）第二个功能
    * 取得document
    * 根据用户的ID来查找用户是否存在
    * 返回null(代表用户不存在)
    * 如果不为Null则将Element封装到User中hao
    */
    public User findByUserID(String UserID) throws DocumentException {
        //(取得document)创建解析器
        SAXReader reader = new SAXReader();
        Document doc = reader.read(path);

        Element ele = (Element) doc.selectSingleNode("//user[@userID='"+UserID+"']");//意为选择单一节点
        //校验ele是否为null
        if(ele == null){
            return null;
        }
        //将ele中的数据封装到User对象中
        User user = new User();
        String attrUserName = ele.attributeValue("username");
        String attrUserPwd = ele.attributeValue("password");
        String attrUserID = ele.attributeValue("userID");
        String attrUserPhone = ele.attributeValue("phone");
        String attrEmail = ele.attributeValue("email");

        user.setUserName(attrUserName);
        user.setID(attrUserID);
        user.setEmail(attrEmail);
        user.setPassWord(attrUserPwd);
        user.setPhone(attrUserPhone);
        return user;
    }

    /*
    *（3）第三个功能
    * 如果用户不存在，则添加用户
    * 得到document元素
    * 通过document得到root元素<Users>
    * 把element保存到root元素中
    * */
    public void add(User user) throws DocumentException, IOException {
        SAXReader reader = new SAXReader();
        Document doc =  reader.read(path);
        //得到根元素
        Element root = doc.getRootElement();
        //通过根元素添加新元素
        Element userEle = root.addElement("user");
        //为userEle添加属性
        userEle.addAttribute("username",user.getUserName());
        userEle.addAttribute("userID",user.getID());
        userEle.addAttribute("password",user.getPassWord());
        userEle.addAttribute("phone",user.getPhone());
        userEle.addAttribute("email",user.getEmail());

        /*
        * 保存文档
        *先创建输出格式化器
        * */
        OutputFormat outputFormat = new OutputFormat(" ",true);//缩进使用/t，是否换行，是
        outputFormat.setTrimText(true);//清空原有的换行和缩进

        //创建XMLWriter,以原先地址进行保存（注意编码），套用上面的格式
        XMLWriter xmlWriter = new XMLWriter(new OutputStreamWriter(new FileOutputStream(path),"UTF-8"),outputFormat);
        xmlWriter.write(doc);//保存document对象
        xmlWriter.close();
    }

}
