package org.example.hashCodeDemo;

public class Main {
    public static void main(String[] args) {
        User hollis = new User("Hollis");
        User hollis2 = new User("Hollis");
        User hollis3 = hollis;
        System.out.println(hollis.equals(hollis2));
        System.out.println(hollis.equals(hollis3));
        System.out.println(hollis == hollis2);
        System.out.println(hollis == hollis3);
        System.out.println(hollis.hashCode());
        System.out.println(hollis2.hashCode());
        System.out.println(hollis3.hashCode());
        int a = 10;
        int b = 10;
        Integer c = a;
        Integer d = b;
        c.equals(d);
        System.out.println(c == d);
        System.out.println(c.hashCode());
        System.out.println(d.hashCode());
    }
}
