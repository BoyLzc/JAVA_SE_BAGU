package org.example.Concurrent.SynchronizedDemo;

public class MyThread implements Runnable{
    @Override
    public void run() {
//        synchronized (MyThread.class){ // 类锁
        synchronized (this){ // 对象锁
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis());
        }
    }
}
