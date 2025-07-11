package org.example.Generic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HollisTest {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<String> listString = new ArrayList<>();
        test(listString);
        System.out.println(listString);
        // 编译出错
//        test1(listString);
//        test2(listString);
//        System.out.println(listString);
        test3(listString);
        System.out.println(listString);
        // 可以将具体参数类型的 List 赋值给 List<?>
        test5(listString);
        // 无法将具体参数类型的 List 赋值给 List<Object>
//        test6(listString);
        // 在泛型为 Integer 的 ArrayList 中添加 String 类型的数据
        ArrayList<Integer> integerArrayList = new ArrayList<Integer>();
        Method add = integerArrayList.getClass().getMethod("add", Object.class);
        add.invoke(integerArrayList, "java反射机制");
        add.invoke(integerArrayList, 666);
        System.out.println(integerArrayList);
    }


    // test
    public static void test(List list) {
        list.add("Hollis");
//        list.add(666);
    }

    public static void test5(List<?> list) {
        System.out.println(list);
    }

    public static void test6(List<Object> list) {
        System.out.println(list);
    }

    //
    public static void test1(List<Object> list) {
        list.add(new HollisTest());
    }

    // 由于不确定列表中元素的具体类型，只能从该列表中读取数据，而不能往里面添加除了null之外的任何元素
    public static void test2(List<?> list) {
        // 编译出错
//        list.add("Hollis");
        // 编译正常
        list.get(0);
    }

    public static void test3(List<String> list) {
        list.add("Hollis");
    }
}
