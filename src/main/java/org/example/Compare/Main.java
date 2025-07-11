package org.example.Compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);
        System.out.println("原始数组:");
        System.out.println(arrayList);
        // void reverse(List list)：反转
        Collections.reverse(arrayList);
        System.out.println("Collections.reverse(arrayList):");
        System.out.println(arrayList);
        // void sort(List list),按自然排序的升序排序
        Collections.sort(arrayList);
        System.out.println("Collections.sort(arrayList):");
        System.out.println(arrayList);
        // 定制排序的用法
        Collections.sort(arrayList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 定义降序规则：o2.compareTo(o1)表示反向比较
                return o2.compareTo(o1);
            }
        });
        System.out.println("定制排序后：");
        System.out.println(arrayList);

        Person[] personArray = new Person[4];
        personArray[0] = new Person("Tom", 18);
        personArray[1] = new Person("Jerry", 19);
        personArray[2] = new Person("Jim", 20);
        personArray[3] = new Person("Mike", 17);
        System.out.println("原始数组:");
        for (Person person : personArray) {
            System.out.println(person.getAge() + "-" + person.getName());
        }
        Arrays.sort(personArray, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(o1.getAge() > o2.getAge()) {
                    return -1;
                }
                if (o1.getAge() < o2.getAge()) {
                    return 1;
                }
                return 0;
//                return o2.compareTo(o1);
            }
        });
        System.out.println("排序后:");
        for (Person person : personArray) {
            System.out.println(person.getAge() + "-" + person.getName());
        }
    }
}
