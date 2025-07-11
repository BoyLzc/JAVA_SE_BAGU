package org.example;

public class StringTest {
    public static class Address implements Cloneable{
        private String name;
        // 省略构造函数、Getter&Setter方法
        @Override
        public Address clone() {
            try {
                return (Address) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
        public Address(String name) {
            this.name = name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    public static class Person implements Cloneable {
        private Address address;
        // 省略构造函数、Getter&Setter方法
        public Person(Address address) {
            this.address = address;
        }
        // 浅拷贝
//        @Override
//        public Person clone() {
//            try {
//                Person person = (Person) super.clone();
//                return person;
//            } catch (CloneNotSupportedException e) {
//                throw new AssertionError();
//            }
//        }
        @Override // 深拷贝
        public Person clone() {
            try {
                Person person = (Person) super.clone();
                person.setAddress(person.getAddress().clone());
                return person;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError();
            }
        }
        public void setAddress(Address address) {
            this.address = address;
        }
        public Address getAddress() {
            return address;
        }
    }

    public class Car {
        private String name;
        private int score;
        public Car(String name, int score){
            this.name = name;
            this.score = score;
        }
    }

    public static void main(String[] args) {
//        Person person1 = new Person(new Address("武汉"));
//        Person person1Copy = person1.clone();
        // 浅拷贝 true
        // 深拷贝 false
//        System.out.println(person1.getAddress() == person1Copy.getAddress());
        // 浅拷贝会创建新对象
//        System.out.println(person1 == person1Copy);


        String aa = "ab"; // 放在常量池中
        String bb = "ab"; // 从常量池中查找
//        当创建 String 类型的对象时，虚拟机会在常量池中查找有没有已经存在的值和要创建的值相同的对象，
//        如果有就把它赋给当前引用。如果没有就在常量池中重新创建一个 String 对象。
        System.out.println(aa == bb);// true

        String a = new String("ab"); // a 为一个引用
        String b = new String("ab"); // b为另一个引用,对象的内容一样
        System.out.println(a == b);// false
        System.out.println(a.equals(b));// true
        System.out.println(42 == 42.0);// true

        String s1 = "abc";
        String s2 = new String("abc");
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));
    }
}
