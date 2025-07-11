package org.example.Concurrent.SynchronizedDemo;

public class SynchronizedDemo {
    // synchronized 锁
    // 同步方法，对象锁
    public synchronized void doSth(){
        System.out.println("123");
    }
    // 同步方法，类锁
    public synchronized static void doSth2(){
        System.out.println("123");
    }
    // 同步代码块，类锁
    public void doSth3(){
        synchronized (SynchronizedDemo.class){
            System.out.println("123");
        }
    }
    // 同步代码块，对象锁
    public void doSth4(){
        synchronized (this){
            System.out.println("123");
        }
    }

    // 可重入锁 m1 与 m2 都是锁的 SynchronizedDemo 对象
    public synchronized void method1(){
        method2();
        System.out.println("invoke method1");
    }

    public synchronized void method2(){
        System.out.println("invoke method2");
    }
    // 不管是上述哪一种用法，最终锁的都是对象
    public static void main(String[] args) {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        synchronizedDemo.method1();
    }
}
