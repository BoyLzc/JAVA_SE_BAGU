package org.example.CollectionDemo.Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 适用Stream流比较
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 18));
        students.add(new Student("Bob", 20));
        students.add(new Student("Charlie", 19));
        // 如果Student实现了Comparable接口，那么可以使用stream()方法进行排序
        List<Student> sortedStudents = students.stream().sorted().
                collect(Collectors.toList());
        sortedStudents.forEach(student -> System.out.println("Name: " + student.getName() + ", Age: " + student.getAge()));
        // 如果没有实现Comparable接口
/*        List<Student> sortedStudents2 = students.stream().
                sorted((s1, s2) -> {
                    return s1.getAge() - s2.getAge();
                }).
                collect(Collectors.toList());*/
    }
}
