package org.example;

public class EqualsSignal {
    public static void main(String[] args) {
        Integer i1 = 33;
        Integer i2 = 33;
        System.out.println(i1 == i2);// 输出 true

        // 没有缓存
        Float i11 = 333f;
        Float i22 = 333f;
        System.out.println(i11 == i22);// 输出 false

        // 没有缓存
        Double i3 = 1.2;
        Double i4 = 1.2;
        System.out.println(i3 == i4);// 输出 false

        Integer i5 = 40;
        Integer i6 = new Integer(40);
        System.out.println(i5 == i6);
        System.out.println(i5.equals(i6));
    }
}

