package org.example.Concurrent.SynchronizedDemo;

public class MyThread2 implements Runnable{
    @Override
    public void run() {
        print();
    }
    // 类锁与对象锁
    // 对象锁：多个线程使用不同实例时，不会相互阻塞，线程安全粒度为单个对象(并发性更高)
//    public synchronized void print(){
    // 类锁：多个线程共享同一个实例，会相互阻塞(避免过度使用类锁)
    public synchronized static void print(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis());
    }
}
