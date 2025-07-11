package org.example.Copy;

import java.io.Serializable;

public class User implements Cloneable{
    private String name;
    private int age;
    private Address address;
    public User() {
    }
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
    public void setAddress(Address address) {
        this.address = address;
    }
    public Address getAddress() {
        return address;
    }

    public Object clone() throws CloneNotSupportedException {
//        return super.clone(); // 浅拷贝
        // 深拷贝
        User user = (User) super.clone();
        // clone会创建一个新的 address 对象 所以这里的 address拷贝肯定是深拷贝
        user.setAddress((Address) address.clone());
        return user;
    }
}

