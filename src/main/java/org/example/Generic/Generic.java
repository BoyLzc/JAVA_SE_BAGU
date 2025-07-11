package org.example.Generic;

// 泛型
public class Generic {
    //此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
    //在实例化泛型类时，必须指定T的具体类型
    public static class GenericClass<T>{
        private T key;
        public GenericClass(T key) {
            this.key = key;
        }
        public T getKey(){
            return key;
        }
    }
    // 泛型方法
    public static <E> void printArray(E[] inputArray)
    {
        for (E element : inputArray ){
            System.out.printf( "%s ", element);
        }
        System.out.println();
    }

    //
    public static void main(String[] args) {
        GenericClass<Integer> genericClass = new GenericClass<Integer>(123);
        System.out.println(genericClass.key);
        System.out.println((genericClass.key.getClass()));

        String[] stringArray = {"1","2","3"};
        Integer[] intArray = {1,2,3};
        printArray(stringArray);
        printArray(intArray);
    }
}
