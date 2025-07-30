package org.example.CollectionDemo.ComparableDemo;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("张三", 18);
        Student s2 = new Student("张三", 19);
        Student s3 = new Student("张三", 20);
        System.out.println(s1.compareTo(s2));
        System.out.println(s2.compareTo(s3));
    }
}
