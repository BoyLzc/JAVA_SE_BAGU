package org.example.Serializable.demo2;
import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private int age;
    private static final long serialVersionUID = 1L;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
