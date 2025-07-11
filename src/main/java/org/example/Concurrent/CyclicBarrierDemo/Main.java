package org.example.Concurrent.CyclicBarrierDemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        // damo1
        // 1. 初始化CyclicBarrier（3个线程 + 屏障触发动作）
/*        CyclicBarrier barrier = new CyclicBarrier(3, () -> {
            System.out.println("\n所有线程已完成本阶段计算，开始下一阶段...\n");
        });

        // 2. 创建并启动工作线程
        for (int i = 1; i <= 3; i++) {
            new Thread(new Worker(i, barrier)).start();
        }*/

        // demo2
        // 此处设置 2 即 需要 两个线程 await来打破内存屏障
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            System.out.println("屏障打破");
        });
        Thread thread = new Thread(new Worker(1, barrier), "T1");
        thread.start();
        barrier.await(); // 主线程 await + thread 线程的 await await的数量大于 2 才能继续执行下面的代码

        Thread thread2 = new Thread(new Worker(2, barrier), "T2");
        thread2.start();
        barrier.await(); // 主线程等待

        Thread thread3 = new Thread(new Worker(3, barrier), "T3");
        thread3.start();
        barrier.await(); // 主线程等待
    }

    static class Worker implements Runnable {
        private final int id;
        private final CyclicBarrier barrier;

        public Worker(int id, CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            // demo1
/*            try {
                for (int phase = 1; phase <= 2; phase++) {
                    // 模拟计算任务
                    int workTime = ThreadLocalRandom.current().nextInt(1000, 3000);
                    Thread.sleep(workTime);
                    System.out.printf("线程-%d 完成第%d阶段计算，耗时%dms\n", id, phase, workTime);

                    // 等待其他线程达到屏障 三个子线程都走到这里，才会继续执行代码，有一个没有走到，则无法继续执行
                    barrier.await();
                }
                System.out.printf("线程-%d 已完成所有阶段任务\n", id);
            } catch (InterruptedException | BrokenBarrierException e) {
                Thread.currentThread().interrupt();
            }*/

            // demo2
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " is Running.");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                // 等待其他线程完成
                try {
                    System.out.println(Thread.currentThread().getName() + " is waiting.");
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
