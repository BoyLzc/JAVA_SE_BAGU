package org.example.Compare;

import java.util.*;

// person对象没有实现Comparable接口，所以必须实现，这样才不会出错，才可以使treeMap中的数据按顺序排列
// 前面一个例子的String类已经默认实现了Comparable接口，详细可以查看String类的API文档，另外其他
// 像Integer类等都已经实现了Comparable接口，所以不需要另外实现了
public class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * T重写compareTo方法实现按年龄来排序
     * 1、TreeMap/TreeSet 排序时
     * 当 Person 对象作为 TreeMap 的 key 或存入 TreeSet 时（如示例中的 TreeMap<Person, String>），会自动调用 compareTo 进行排序。
     * 2、Collections.sort() 对 List  排序时
     * 如果直接对 List<Person> 调用 Collections.sort(list)（无自定义 Comparator），会使用 Person 的 compareTo 方法。
     * 3、Arrays.sort() 对 Person[] 排序时
     * 对 Person 数组调用 Arrays.sort(personArray) 时触发。
     */
    @Override
    public int compareTo(Person o) {
        // 升序
        if (this.age > o.getAge()) {
            return 1;
        }
        if (this.age < o.getAge()) {
            return -1;
        }
        // 降序
//        if (this.age > o.getAge()) {
//            return -1;
//        }
//        if (this.age < o.getAge()) {
//            return 1;
//        }
        return 0;
    }

    public static void main(String[] args) {
//        TreeMap<Person, String> pdata = new TreeMap<Person, String>();
//        pdata.put(new Person("张三", 30), "zhangsan");
//        pdata.put(new Person("李四", 20), "lisi");
//        pdata.put(new Person("王五", 10), "wangwu");
//        pdata.put(new Person("小红", 5), "xiaohong");
//        // 得到key的值的同时得到key所对应的值
//        Set<Person> keys = pdata.keySet();
//        System.out.println(keys);
//        for (Person key : keys) {
//            System.out.println(key.getAge() + "-" + key.getName());
//        }

//        Collections.sort()
//        List<Person> list = new ArrayList<Person>();
//        list.add(new Person("张三", 30));
//        list.add(new Person("李四", 20));
//        list.add(new Person("王五", 10));
//        list.add(new Person("小红", 5));
//        Collections.sort(list);
//        for (Person person : list) {
//            System.out.println(person.getAge() + "-" + person.getName());
//        }

//        Arrays.sort()
        Person[] personArray = new Person[4];
        personArray[0] = new Person("张三", 30);
        personArray[1] = new Person("李四", 20);
        personArray[2] = new Person("王五", 10);
        personArray[3] = new Person("小红", 5);
        System.out.println("原始数组:");
        for (Person person : personArray) {
            System.out.println(person.getAge() + "-" + person.getName());
        }
        Arrays.sort(personArray);
        System.out.println("升序排序后:");
        for (Person person : personArray) {
            System.out.println(person.getAge() + "-" + person.getName());
        }
        System.out.println("降序排序后:");
        Arrays.sort(personArray, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        for (Person person : personArray) {
            System.out.println(person.getAge() + "-" + person.getName());
        }


    }
}