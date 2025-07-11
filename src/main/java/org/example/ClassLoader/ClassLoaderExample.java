package org.example.ClassLoader;

public class ClassLoaderExample {
    // 双亲委派
    public static void main(String[] args) {
        // 当前类加载器
        ClassLoader classLoader = ClassLoaderExample.class.getClassLoader();
        System.out.println(classLoader);
        // 父类加载器
        ClassLoader parentClassLoader = classLoader.getParent();
        System.out.println(parentClassLoader);
        // 父类的父类（根加载器、启动类加载器）
        ClassLoader rootClassLoader = parentClassLoader.getParent();
        System.out.println(rootClassLoader);

    }
}
