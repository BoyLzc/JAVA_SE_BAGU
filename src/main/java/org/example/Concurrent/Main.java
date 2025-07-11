package org.example.Concurrent;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
/*        Thread childThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    System.out.println("I'am child thread");
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        childThread.setDaemon(true); // 设置子线程为守护线程
        childThread.start(); // 将子线程从 new 状态 转变为 runnable 状态(Ready + Running)*/
        System.out.println("I'am main thread");
        Thread.sleep(1000); // 主线程休眠等待10s
        // 当主线程结束后，守护线程会自动结束(JVM自动退出)
        // 使用 Runnable接口 创建线程
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("I'am runnable thread");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        };
        // 使用 Callable接口 创建线程
/*        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("[" + System.currentTimeMillis() + "] 子线程开始执行");
                Thread.sleep(5000);  // 子线程休眠3秒（比主线程更长）
                System.out.println("[" + System.currentTimeMillis() + "] 子线程执行完成");
                return "子线程返回值";
            }
        };
        // 使用 FutureTask接口 获取线程
        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        System.out.println("[" + System.currentTimeMillis() + "] 开启子线程");
        thread.start();
        // 主线程立即尝试获取结果（会阻塞）
        System.out.println("[" + System.currentTimeMillis() + "] 主线程调用get()前");
        String result = futureTask.get();  // 这里会产生阻塞 get要等子线程拿到结果，主线程才能继续执行下去
        System.out.println("[" + System.currentTimeMillis() + "] 主线程获得结果: " + result);
        System.out.println("123");
        System.out.println("123");
        System.out.println("123");*/

        // 线程池
/*        ExecutorService executor = Executors.newSingleThreadExecutor();
        Callable<String> callable1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(6000);
                System.out.println("Entered Callable");
                return "Hello from Callable";
            }
        };
        System.out.println("Submitting Callable");
        Future<String> future = executor.submit(callable1); // 此刻已经开始执行子线程
        Thread.sleep(3000);

        System.out.println("Waiting for result");
        Thread.sleep(10000);
        System.out.println(future.get()); // 本质上是主线程执行此语句
        executor.shutdown();*/

        // wait、notify、notifyAll
        // 定义共享锁对象
        final Object lock = new Object();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while(true) {
                        System.out.println("I'am runnable thread");
                        try {
                            Thread.sleep(1000);
                            lock.notify(); // 通知等待线程
                            // 如果仅通知，主线程永远无法获取锁
                            lock.wait(); // 子线程释放锁并等待
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
        synchronized (lock) {
            thread1.start();
            lock.wait(); // 主线程释放锁并等待
            System.out.println("123");
            System.out.println("123");
            System.out.println("主线程被唤醒");
            lock.notify(); // 主线程通知等待线程
            lock.wait(); // 释放锁
        }


    }
}
