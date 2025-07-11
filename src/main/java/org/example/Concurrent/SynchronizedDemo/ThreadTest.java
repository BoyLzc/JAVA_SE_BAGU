package org.example.Concurrent.SynchronizedDemo;

public class ThreadTest {
    public static void main(String[] args) {
        for(int i = 0; i < 10; i++) {
            // 如果使用的是 MyThread 类锁 则会出现线程同步问题
            // 如果使用的是 对象锁 则不会出现线程同步问题
//            MyThread myThread = new MyThread();
//            Thread t1 = new Thread(myThread);
//            t1.start();

            MyThread2 myThread2 = new MyThread2();
            Thread t1 = new Thread(myThread2);
            t1.start();
        }
    }
}
