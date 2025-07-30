package org.example.CollectionDemo.ComparableDemo;

public class Student implements Comparable<Student> {
    private String name;
    private int age;
    @Override // 自定义比较规则
    public int compareTo(Student o) {
        int flag = this.name.compareTo(o.name);
        if (flag != 0) {
            flag = this.age - o.age;
        }
        return flag;
/*        if (this.age > o.age) {
            return 1;
        } else {
            return -1;
        }*/
    }
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
