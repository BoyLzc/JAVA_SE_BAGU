package org.example.Map;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author shuang.kou
 * @createTime 2020年06月15日 17:02:00
 */
public class Person {
    private Integer age;

    public Person(Integer age) {
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }


    public static void main(String[] args) {
        TreeMap<Person, String> treeMap = new TreeMap<>(new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                // 升序 person1 > person2 则 num > 0
//                int num = person1.getAge() - person2.getAge();
//                return Integer.compare(num, 0);
                // 降序 person1 > person2 则 num < 0
                int num = person2.getAge() - person1.getAge();
                return Integer.compare(num, 0);
            }
        });
//        TreeMap<Person, String> treeMap = new TreeMap<>();
        treeMap.put(new Person(3), "person1");
        treeMap.put(new Person(18), "person2");
        treeMap.put(new Person(35), "person3");
        treeMap.put(new Person(16), "person4");
        treeMap.entrySet().stream().forEach(personStringEntry -> {
            System.out.println(personStringEntry.getValue());
        });
    }
}