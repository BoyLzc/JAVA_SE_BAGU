package org.example.Concurrent.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolThreadExecute {
    public static void main(String[] args) {
        // 创建线程池
        // 线程池内部使用队列存储任务，线程执行顺序按照任务的提交顺序执行
        // 如果多个线程同时执行，无法保证先后顺序，因为可能先提交的后执行了，
        // 但是可以定义一个只有一个线程的线程池，然后依次将T1、T2、T3依次执行
        ExecutorService executor = Executors.newSingleThreadExecutor();
        // 创建并启动线程T1
        executor.submit(new MyThread("T1"));
        executor.submit(new MyThread("T2"));
        executor.submit(new MyThread("T3"));
        executor.shutdown();
    }
    public static class MyThread implements Runnable {
        private String name;

        public MyThread(String name) {
            this.name = name;
        }

        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println("线程" + name + "执行完毕");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
