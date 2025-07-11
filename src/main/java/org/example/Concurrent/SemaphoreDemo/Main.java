package org.example.Concurrent.SemaphoreDemo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // demo1
/*        // 1. 创建信号量（模拟3个数据库连接池）
        // 初始三个许可
        Semaphore dbSemaphore = new Semaphore(3);

        // 2. 模拟10个客户端请求
        for (int i = 1; i <= 10; i++) {
            new Thread(new Client(i, dbSemaphore)).start();
        }*/
        // demo2 控制线程执行顺序
        // 初始化一个许可
        Semaphore semaphore = new Semaphore(1);
//        semaphore.acquire();
//        semaphore.acquire(); 拿到两个许可，不进行释放，线程无法继续往下执行
        System.out.println("主线程开始执行...");
        semaphore.acquire(); // 主线程尝试获取许可 获取成功后，才可以执行以下代码

        Thread thread1 = new Thread(new Client(1, semaphore), "T1");
        thread1.start(); // T1 启动，但由于主线程已获取许可，T1无法获取许可(总共只有一个许可的情况下)
        // T1子线程会释放许可 主线程又可以往下执行
        semaphore.acquire(); // 本质上，此处又一次拿到许可，如果不释放，则无法执行下面代码

        Thread thread2 = new Thread(new Client(2, semaphore), "T2");
        thread2.start();

        semaphore.acquire();

        Thread thread3 = new Thread(new Client(3, semaphore), "T3");
        thread3.start();

        System.out.println("所有线程已完成");
    }

    static class Client implements Runnable {
        private final int id;
        private final Semaphore semaphore;

        public Client(int id, Semaphore semaphore) {
            this.id = id;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
/*            try {
                // 尝试获取许可（带超时设置）
                // 非阻塞尝试获取许可（设置请求许可数量为1，设置超时时间为10秒）
                // 同一时间最多有 3/1 个线程可以获取许可
                if (semaphore.tryAcquire(1, 10, TimeUnit.SECONDS)) {
                    try {
                        System.out.printf("客户端-%d 获取到数据库连接\n", id);
                        // 模拟数据库操作耗时
                        Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
                        System.out.printf("客户端-%d 释放数据库连接\n", id);
                    } finally {
                        semaphore.release(); // 确保释放许可
                    }
                } else { // 没有获取许可，则放弃请求
                    System.out.printf("客户端-%d 等待超时，放弃请求\n", id);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }*/
            try {
//                semaphore.acquire(); // 必须获取许可才能执行逻辑
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " is Running.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                // 释放许可证，表示该线程已完成
                System.out.println(Thread.currentThread().getName() + " is Released.");
                semaphore.release();
            }
        }
    }
}
