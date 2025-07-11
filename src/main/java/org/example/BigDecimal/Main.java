package org.example.BigDecimal;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        float a = 2.0f - 1.9f;
        float b = 1.8f - 1.7f;
        System.out.println(a);// 0.100000024
        System.out.println(b);// 0.099999905
        System.out.println(a == b);// false

        // 浮点数比较
        BigDecimal a1 = new BigDecimal("1.0");
        BigDecimal b1 = new BigDecimal("0.9");
        BigDecimal c1 = new BigDecimal("0.8");
        BigDecimal x = a1.subtract(b1); // 0.1
        BigDecimal y = b1.subtract(c1); // 0.1
        System.out.println(x);
        System.out.println(y);
        // 比较大小 a小于b 返回 -1， a等于b 返回 0， a大于b 返回 1
        System.out.println(x.compareTo(y));// 0
        System.out.println();
        System.out.println(a1.add(b1));
        System.out.println(a1.subtract(b1));
        System.out.println(a1.multiply(b1));
//        System.out.println(a1.divide(b1)); 无法除尽 抛出
        System.out.println(a1.divide(b1, 2, BigDecimal.ROUND_HALF_UP)); // 保留两位小数
        // 保留几位小数
        BigDecimal m = new BigDecimal("1.25554");
        BigDecimal n = m.setScale(3, RoundingMode.HALF_DOWN);
        BigDecimal n2 = m.setScale(3, RoundingMode.HALF_UP);
        System.out.println(n);
        System.out.println(n2);

        // 等值比较
        BigDecimal a2 = new BigDecimal("1.0");
        BigDecimal b2 = new BigDecimal("2.0");
        BigDecimal c = new BigDecimal("1.0000");
        // equals 会比较值和精度， == 不会比较值和精度
//        System.out.println(a2.equals(b2));
//        System.out.println(a2.compareTo(b2) == 0);
        System.out.println(a2.compareTo(b2));
        System.out.println(a2.compareTo(c));

    }
}
