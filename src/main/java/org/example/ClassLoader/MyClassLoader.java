package org.example.ClassLoader;

import java.io.*;

// 所有用户自定义的类加载器需要继承 ClassLoader 类
public class MyClassLoader extends ClassLoader{
    private String path;
    private String packageName;

    public MyClassLoader(String path, String packageName) {
        this.path = path;
        this.packageName = packageName;
    }

    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            // 首先尝试 使用父类加载器（ApplicationClassLoader 应用类加载器） 加载类
            return super.loadClass(name);
        } catch (ClassNotFoundException e) {
            // 如果 父类加载器无法加载类 则 自定义加载类
            return findClass(name);
        }
    }
    public Class<?> findClass(String name) {
        // 实现自己的类加载逻辑，例如从特定位置加载类文件
        byte[] classData = null;
        try {
            classData = loadClassData(name);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(classData == null) {
            throw new ClassCastException();
        }
        return defineClass(packageName + name, classData, 0, classData.length);
    }

    private byte[] loadClassData(String name) throws FileNotFoundException {
        // 实现加载类的文件逻辑
        try {
            FileInputStream fis = new FileInputStream(path + name + ".class");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            fis.close();
            bos.close();
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        // 创建自定义类加载器
        MyClassLoader myClassLoader = new MyClassLoader("/Users/lzc/IdeaProjects/bagu/bagu/target/classes/org/example/", "org.example.ClassLoader");
        try {
            // 使用自定义类加载器
            Class<?> clazz = myClassLoader.loadClass("org.example.ClassLoader.MyClassLoader");
            System.out.println("加载此类的类加载器为：" + clazz.getClassLoader());
            System.out.println("加载此类的类加载器的父类加载器为：" + clazz.getClassLoader().getParent());
        } catch (ClassNotFoundException e) {
            System.out.println("未找到此类");
        }
    }
}
