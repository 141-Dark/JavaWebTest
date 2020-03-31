package domain;
/*
* domain类的作用：把数据库中的查询结果放到这个对象中
* */
public class User {
    String userName;
    String passWord;
    String ID;
    String email;
    String phone;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", ID='" + ID + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    //写一个构造方法
    public User(String UserName,String PassWord){
        this.userName = UserName;
        this.passWord = PassWord;
    }
    //无参构造方法（必须有）
    public User(){
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
