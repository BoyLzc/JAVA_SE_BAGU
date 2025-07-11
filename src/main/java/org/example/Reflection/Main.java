package org.example.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 反射机制是指程序在运行时能够获取自身的信息
// 在java中，只要给定类的名字，那么就可以通过反射机制获取类的所有属性和方法
public class Main {
    public static class Person{
        public String name;
        public int age;
        public Person() {
            System.out.println("我是默认构造函数");
        }
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getName(){
            return name;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public int getAge(){
            return age;
        }
    }
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        // 通过反射获取某个类的方法
        Object person = new Person("LZC", 18);
        Class<?> clazz = person.getClass();
        System.out.println(clazz);
        // 获取成员变量
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("成员变量" + field.getName());
        }
        // 获取方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("方法" + method.getName());
        }
        // 获取不到通过构造函数注入的值
//        Method method = clazz.getDeclaredMethod("getName");
//        method.invoke(person);
        // 调用方法
        Method method = clazz.getDeclaredMethod("setName", String.class);
//        method.setAccessible(true); // 如果方法是私有的
        method.invoke(person, "lzc");
        method = clazz.getDeclaredMethod("getName");
        System.out.println(method.invoke(person));

        // 默认构造函数
        Constructor<?> constructor2 = clazz.getConstructor();
        Object object = constructor2.newInstance();
        System.out.println(object);
        // 此类写法亦可
        System.out.println(clazz.getDeclaredConstructor().newInstance());

        // 使用特定的构造函数
        Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
        Object object1 = constructor.newInstance("lzc", 123);
        System.out.println(object1);
        System.out.println(clazz.getDeclaredMethod("getName").invoke(object1));


    }
}
