package org.example.Concurrent.CountDownLatchDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // demo1
/*        // 1. 初始化计数器（3个子任务）
        CountDownLatch latch = new CountDownLatch(3);

        // 2. 创建并启动工作线程
        new Thread(new Worker("数据库加载", 1000, latch)).start();
        new Thread(new Worker("配置文件读取", 1500, latch)).start();
        new Thread(new Worker("网络资源初始化", 2000, latch)).start();

        // 3. 主线程等待所有任务完成
        System.out.println("主线程等待所有子任务完成...");
        latch.await(); // 阻塞直到计数器归零
        System.out.println("所有子任务已完成，开始执行主线程业务逻辑");*/

        // demo2 控制线程顺序
        CountDownLatch countDownLatch1 = new CountDownLatch(1);
        CountDownLatch countDownLatch2 = new CountDownLatch(1);
        CountDownLatch countDownLatch3 = new CountDownLatch(1);
        Thread thread = new Thread(new Worker("任务1", 1000, countDownLatch1), "T1");
        Thread thread2 = new Thread(new Worker("任务2", 1000, countDownLatch2), "T2");
        Thread thread3 = new Thread(new Worker("任务3", 1000, countDownLatch3), "T3");
        // 启动一个 然后await等待其执行完毕再进行下一个
        thread.start();
        countDownLatch1.await(); // 进行阻塞 只有countDownLatch1减为0时，才能执行以下代码
        thread2.start();
        countDownLatch2.await();
        thread3.start();
        countDownLatch3.await();
    }

    static class Worker implements Runnable {
        private final String taskName;
        private final int workTime;
        private final CountDownLatch latch;

        public Worker(String taskName, int workTime, CountDownLatch latch) {
            this.taskName = taskName;
            this.workTime = workTime;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                System.out.println("我是线程" + Thread.currentThread().getName() + "正在执行任务："+ taskName + "开始执行...");
                // 模拟任务执行时间（随机增加0-500ms波动）
                Thread.sleep(workTime + ThreadLocalRandom.current().nextInt(500));
                System.out.println("我是线程" + Thread.currentThread().getName() + taskName + "执行完成！");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                latch.countDown(); // 任务完成，计数器减1
            }
        }
    }
}
