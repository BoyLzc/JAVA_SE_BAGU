package org.example.Generic;

import java.util.ArrayList;
import java.util.List;

public class Main{
    // 示例1：使用T作为泛型类型参数，表示任何类型
//    public static class MyGenericClass<T> {
//        private T myField;
//        public void setMyField(T myField)
//        {
//            this.myField = myField;
//        }
//        public T getMyField()
//        {
//            return myField;
//        }
//    }

    // 示例2：使用K和V作为泛型类型参数，表示键和值的类型
    public static class MyMap<K, V> {
        private List<LZC<K, V>> entries;
        public MyMap() {
            entries = new ArrayList<>();
        }
        public void put(K key, V value) {
            LZC<K, V> entry = new LZC<>(key, value);
            entries.add(entry);
        }
        public V get(K key) {
            for (LZC<K, V> entry : entries) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
            return null;
        }
        private class LZC<K, V> {
            private K key;
            private V value;
            public LZC(K key, V value) {
                this.key = key;
                this.value = value;
            }
            public K getKey() {
                return key;
            }
            public V getValue() {
                return value;
            }
        }
    }

    // 示例3：使用E作为泛型类型参数，表示元素类型
    public static class MyList<E> {
        private List<E> elements;
        public MyList() {
            elements = new ArrayList<>();
        }
        public void add(E element) {
            elements.add(element);
        }
        public E get(int index) {
            return elements.get(index);
        }
    }

    // 示例4：使用Object作为泛型类型参数，表示可以接收任何类型
    public static class MyGenericClass {
        private Object myField;
//        public MyGenericClass(Object myField) {
//            this.myField = myField;
//        }
        public void setMyField(Object myField) {
            this.myField = myField;
        }
        public Object getMyField() {
            return myField;
        }
    }

    public static void main(String[] args)
    {
        MyList<Object> list = new MyList<>();
        list.add("Hello");
        list.add(123);
        System.out.println(list.elements);

//        MyGenericClass<Object> objectMyGenericClass = new MyGenericClass<>();
//        objectMyGenericClass.setMyField("Hello");
//        System.out.println(objectMyGenericClass.getMyField());
//        objectMyGenericClass.setMyField(123);
//        System.out.println(objectMyGenericClass.getMyField());

//        MyGenericClass objectMyGenericClass = new MyGenericClass();
//        objectMyGenericClass.setMyField("Hello");
//        System.out.println(objectMyGenericClass.getMyField());
//        objectMyGenericClass.setMyField(123);
//        System.out.println(objectMyGenericClass.getMyField());

        MyMap<Object, Object> objectObjectMyMap = new MyMap<>();
        objectObjectMyMap.put("name", "lllllll");
        System.out.println(objectObjectMyMap.get("name"));

        MyMap<Object, Object> objectObjectMyMap1 = new MyMap<>();
        objectObjectMyMap1.put(123, 123);
        System.out.println(objectObjectMyMap1.get(123));

        objectObjectMyMap1.put("123", "123");
        System.out.println(objectObjectMyMap1.get("123"));
    }
}
