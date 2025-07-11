package org.example.Enum;

public class EnumExample {
    public static void main(String[] args) {
        try {
            Season ss = Season.valueOf("SPRING"); // 含法参数
            System.out.println(ss); // 输出 SPRING
            Season lzc = Season.valueOf("LZC"); // 非法参数 会抛出异常
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        double x = 10;
        double y = 5;
        System.out.println(Operation.ADD.apply(x, y));
        System.out.println(Operation.SUBTRACT.apply(x, y));
        System.out.println(Operation.MULTIPLY.apply(x, y));
        System.out.println(Operation.DIVIDE.apply(x, y));
    }
}
