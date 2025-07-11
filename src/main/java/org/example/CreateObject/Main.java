package org.example.CreateObject;

import java.io.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;

public class Main implements Cloneable{
//    public static class Person {
//        public String name;
//        public int age;
//        public Person() {
//        }
//        public Person(String name, int age) {
//            this.name = name;
//            this.age = age;
//        }
//        public void setName(String name) {
//            this.name = name;
//        }
//        public String getName() {
//            return name;
//        }
//        public void setAge(int age) {
//            this.age = age;
//        }
//        public int getAge() {
//            return age;
//        }
//        @Override
//        public String toString() {
//            return "Person{name='" + name + "', age=" + age + "}";
//        }
//    }
    public static void main(String[] args) throws Throwable {
        // 创建对象的方式
        // 1、new
        Person person = new Person();
        // 2、反射
        Person person1 = (Person)Class.
                forName("org.example.CreateObject.Person").
                getDeclaredConstructor(String.class, int.class).
                newInstance("lzc", 24);
        Person person2 = Person.class.
                getDeclaredConstructor(String.class, int.class).
                newInstance("lzc2", 25);
        Person person3 = Person.class.
                getConstructor().
                newInstance();
        System.out.println(person1);
        System.out.println(person2);
        // 3、clone 方法 需要实现 Cloneable接口
        Person person4 = new Person("lzc4", 25);
        // 4、反序列化
        Person person5 = new Person();
        person5.setName("lzc5");
        person5.setAge(25);
        System.out.println(person5);
        // 写入
        ObjectOutputStream oos = new ObjectOutputStream
                (new FileOutputStream("tempFile"));
        oos.writeObject(person5);
        // 读取
        File file = new File("tempFile");
        ObjectInputStream ois = new ObjectInputStream
                (new FileInputStream(file));
        Person person6 = (Person) ois.readObject();
        System.out.println(person6);

        // 利用 MethodHandlers 通过构造方法获取对象 类似于反射 但是性能比反射好一些
        // 获取构造函数的方法句柄
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        // 定义构造函数的方法句柄类型 首先是返回参数 构造函数一般都是 void 剩下的就是入参
        MethodType constructorType = MethodType.methodType(void.class, String.class, int.class);
        // 创建对象 找到 person 类中的构造函数
        MethodHandle constructorHandle = lookup.findConstructor(Person.class,
                constructorType);
        // 执行构造函数 创建 person对象
        Person obj = (Person) constructorHandle.invoke("lzc6", 24);
        System.out.println(obj);
        // 调用普通成员方法
        MethodType methodType = MethodType.methodType(void.class, String.class);
        MethodHandle printString = lookup.findVirtual(Person.class, "printString", methodType);
        String name = "lzc";
        printString.invoke(obj, name);
    }
}
