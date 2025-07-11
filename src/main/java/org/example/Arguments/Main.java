package org.example.Arguments;

public class Main {
    // java属于值传递
    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
    // 交换的仅仅是拷贝的地址 不影响实参
    public static void swap(Person person1, Person person2) {
        System.out.println("person1:" + person1);
        System.out.println("person2:" + person2);
        Person temp = person1;
        person1 = person2;
        person2 = temp;
        System.out.println("person1:" + person1);
        System.out.println("person2:" + person2);
        System.out.println("person1:" + person1.getName());
        System.out.println("person2:" + person2.getName());
    }
    // 传递引用类型参数的传递 （其实也是值传递 值是 地址）
    public static void change(int[] array) {
        System.out.println("array:" + array);
        // 将数组的第一个元素变为0
        array[0] = 0;
    }
    // java 只有值传递 参数是基本类型的话，传递的就是基本类型字面量值的拷贝
    // 参数是引用类型的话，传递的就是实参所引用的对象在堆中地址值的拷贝
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 20;
        swap(num1, num2);
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);

        // 修改数组元素
        int[] arr = { 1, 2, 3, 4, 5 };
        System.out.println(arr[0]);
        change(arr);
        System.out.println(arr[0]);

        Person xiaoZhang = new Person("小张");
        Person xiaoLi = new Person("小李");

//        System.out.println("xiaoZhang:" + xiaoZhang);
//        System.out.println("xiaoLi:" + xiaoLi);
        swap(xiaoZhang, xiaoLi);
//        System.out.println("xiaoZhang:" + xiaoZhang);
//        System.out.println("xiaoLi:" + xiaoLi);

        System.out.println("xiaoZhang:" + xiaoZhang.getName());
        System.out.println("xiaoLi:" + xiaoLi.getName());
    }
}
