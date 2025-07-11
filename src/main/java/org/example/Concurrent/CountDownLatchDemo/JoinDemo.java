package org.example.Concurrent.CountDownLatchDemo;

// 指定线程的执行顺序
public class JoinDemo {
    public static void main(String[] args) throws InterruptedException {
        // demo1
/*        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("thread1 running");
            }
        });
        thread1.start();
        thread1.join(); // 将 thread1 线程加入到当前线程中 即阻塞主线程，等子线程执行完再继续执行
        System.out.println("Main 1 running");*/

        // demo2
        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is Running");
            }
        }, "T1");

        final Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread1.join(); // 阻塞当前线程，等待thread1执行完毕
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " is Running");
            }
        }, "T2");

        final Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    thread2.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + " is Running");
            }
        }, "T3");

        thread1.start();
        thread3.start();
        thread2.start();
    }
}
