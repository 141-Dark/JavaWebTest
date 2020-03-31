package domain;

public class Stu {
    private String sid;
    private int age;
    private String name;
    private String gender;

    public Stu() {
        super();
    }

    public Stu(String sid, String name,String gender, int age){
        super();
        this.age=age;
        this.gender = gender;
        this.name = name;
        this.sid = sid;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "stu{" +
                "sid=" + sid +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

}
