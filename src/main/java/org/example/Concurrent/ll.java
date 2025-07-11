package org.example.Concurrent;

public class ll {
    public static void main(String[] args) {
        System.out.println(method());
    }
    public static int method() {
        int i = 0;
        try {
            int b = 1 / 0;
            System.out.println("b:" + b);
            System.out.println("try:"+ ++i);
            return 1;
        } catch (Exception e) {
            System.out.println("catch:");
            return 2;
        } finally {
            System.out.println("finally:" + ++i);
            return 3;
//            System.out.println("finally");
        }
    }
}
