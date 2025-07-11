package org.example.CreateObject;

import java.io.Serializable;

public class Person implements Cloneable, Serializable {
    public String name;
    public int age;
    public Person() {
        super();
        System.out.println("我是默认构造函数");
    }
    public Person(String name, int age) {
//        super();
        this.name = name;
        this.age = age;
        System.out.println("我是有参构造函数");
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
    public void printString(String words) {
        System.out.println(words);
    }
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Person person = new Person("张三", 18);
        Person person2 = (Person) person.clone();
        System.out.println(person.getName());
        System.out.println(person2.getName());
        System.out.println(person.getClass());
        System.out.println(person2.getClass());
        System.out.println(person == person2);
        System.out.println(person.equals(person2));
    }
}
