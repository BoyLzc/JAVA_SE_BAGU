package org.example.Map;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static class Person {
        private String name;
        private int age;
        public Person(String name, int age){
            this.name = name;
            this.age = age;
        }
        public String getName(){
            return name;
        }
        public int getAge(){
            return age;
        }
    }

    // Set 去重代码示例
    public static <T> Set<T> removeDuplicateBySet(List<T> data) {
        if (data == null || data.isEmpty()) {
            return new HashSet<>();
        }
        return new HashSet<>(data);
    }

    // List 去重代码示例
    public static <T> List<T> removeDuplicateByList(List<T> data) {
        if (data == null || data.isEmpty()) {
            return new ArrayList<>();
        }
        List<T> result = new ArrayList<>(data.size());
        for (T current : data) {
            if (!result.contains(current)) {
                result.add(current);
            }
        }
        return result;
    }

    // 手动实现工具类
    public static <T> List<T> arrayToList(final T[] array) {
        final List<T> l = new ArrayList<T>(array.length);

        for (final T s: array) {
            l.add(s);
        }
        return l;
    }

    public static void main(String[] args) {
        String [] s= new String[]{
                "dog", "lazy", "a", "over", "jumps", "fox", "brown", "quick", "A"
        };
        // 将数组 转化为集合
        List<String> list = Arrays.asList(s);
        System.out.println(list);
        Collections.reverse(list);
        System.out.println(list);
        // 将集合 转化为 数组
        //没有指定类型的话会报错
        s = list.toArray(new String[0]);
        // 打印数组
        System.out.println(Arrays.toString(s));

        // 集合转 map
        List<Person> bookList = new ArrayList<>();
        bookList.add(new Person("张三", 30));
        bookList.add(new Person("李四", 20));
        System.out.println(bookList);

        Map<String, Integer> bookMap = bookList.stream().collect(Collectors.toMap(Person::getName, Person::getAge));
        System.out.println(bookMap);

        // 集合去重
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("a");
        list1.add("b");
        list1.add("c");
        System.out.println(list1);
        // 方法一 使用Set去重
        Set<String> set = removeDuplicateBySet(list1);
        System.out.println(set);
        // 方法二 使用List去重
        List<String> list2 = removeDuplicateByList(list1);
        System.out.println(list2);

        // Arrays.asList() 是泛型方法 传递的数组必须是对象数组，而不是基本类型
        int[] intArray = new int[]{1, 2, 3, 4, 5};
        List<int[]> list3 = Arrays.asList(intArray);
        // 如果给的是 基本类型数组，则返回的是一个长度为1的List 其代表数组对象本身
        System.out.println("list3:" + list3.getClass());
        System.out.println(list3); // [数组地址值]
        System.out.println(list3.size()); // 1
        System.out.println(list3.get(0)); // 数组地址值
        // 集合转数组
        int[] intArray1 = list3.get(0);
        System.out.println(Arrays.toString(intArray1));
        // 如果是 包装类型 则正常使用
        System.out.println("Integer");
        Integer[] intArray2 = new Integer[]{1, 2, 3, 4, 5};
        List<Integer> list4 = Arrays.asList(intArray2);
        System.out.println("list4:" + list3.getClass());
        System.out.println(list4);
        System.out.println(list4.size());
        System.out.println(list4.get(0));
        Integer[] intArray3 = list4.toArray(new Integer[0]);
        System.out.println(Arrays.toString(intArray3));

        // 数组 -> 集合
        // 方法1 手动实现工具类
        Integer[] myArray = {1, 2, 3};
        System.out.println(arrayToList(myArray));

        // 方法2 asList
        List<Integer> integers = new ArrayList<>(Arrays.asList(myArray));
        System.out.println(integers);

        // 方法3 Stream
        integers = Arrays.stream(myArray).collect(Collectors.toList());
        System.out.println(integers);

        // 基本类型也可以实现转换
        int[] intArray4 = {1, 2, 3};
        integers = Arrays.stream(intArray4).boxed().collect(Collectors.toList());
        System.out.println(integers);

        System.out.println(Arrays.toString(intArray4));
    }
}
