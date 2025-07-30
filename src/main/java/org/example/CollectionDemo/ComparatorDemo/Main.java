package org.example.CollectionDemo.ComparatorDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 创建学生列表
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 25));
        students.add(new Student("Bob", 20));
        students.add(new Student("Charlie", 22));

        // 使用Comparator按年龄排序
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                int flag = s1.getName().compareTo(s2.getName());
                if (flag != 0) {
//                    flag = s2.getAge() - s1.getAge(); // 从大到小
                    flag = s1.getAge() - s2.getAge(); // 从小到大
                }
                return flag;
            }
        });

        // 打印排序后的学生列表
        for (Student student : students) {
            System.out.println("Name: " + student.getName() + ", Age: " + student.getAge());
        }
    }
}
