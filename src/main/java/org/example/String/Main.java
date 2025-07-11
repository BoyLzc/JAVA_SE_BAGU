package org.example.String;

public class Main {
    public static void main(String[] args) {
        String s1 = "hello";
        // 尽量不要在循环内部 使用 + 拼接字符串
        // 因为每一次都会创建 StringBuilder对象
        for(int i = 0; i < 3; i++) {
            s1 = s1 + "world";
        }
        String s2 = "hello2";
        // 使用StringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s2);
        for(int i = 0; i < 3; i++) {
            stringBuilder.append("world");
        }
        stringBuilder.toString();
        System.out.println(s1);
        System.out.println(stringBuilder);
    }
}
