package org.example.Serializable;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String name;
    private transient int age;
    private Date birthday;
    // transient 关键字的作用就是控制变量的序列化 阻止该变量被序列化到文件中 transient变量的值被设为初始值
    private transient String gender;
    private static final long serialVersionUID = 1L;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                '}';
    }
}
