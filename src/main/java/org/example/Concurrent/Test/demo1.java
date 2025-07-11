package org.example.Concurrent.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 顺序打印 1 -100
public class demo1 {
    // demo1 各线程争抢资源打印(非顺序)
/*    private static int count = 1;
    public static void main(String[] args) {
        for (int i = 0; i < 3; i ++) {
            new Thread(new Print(i)).start();
        }
    }
    private static class Print implements Runnable {
        private final int index;
        public Print(int index) {
            this.index = index;
        }
        public void run() {
            while(true) {
                // 加一个类锁 该类所有对象在同一时间只有一个对象可以访问以下代码块
                synchronized (demo1.class) {
                    if (count >= 1001) {
                        return;
                    }
                    System.out.println("Thread" + ":" + index + " " + count);
                    count ++;
                }
            }
        }
    }*/
    // demo2 各线程按顺序打印(synchronized代码块或 volatile + 锁)
/*    private static final Object LOCK = new Object(); // 线程共用的同步锁，保证原子性
    private static int count = 0; // 确保所有线程能立即看到最新值
    private static final int MAX = 30; // 打印上线
    public static void main(String[] args) {
        for (int i = 0; i < 3; i ++) {
            new Thread(new Seq(i)).start();
        }
    }
    public static class Seq implements Runnable {
        private final int index;
        public Seq(int index) {
            this.index = index;
        }
        @Override
        public void run() {
            while (count < MAX) {
                synchronized (LOCK) {
                    // 进入同步块时，会强制刷新线程本地缓存，
                    // 读取主内存最新值，退出同步块时，会立即将修改刷新回主内存
                    try {
                        // 循环判断当前线程对应的数字是否是当前计数器的值
                        while (count % 3 != index) {
                            System.out.println("线程" + index + " is waiting");
                            LOCK.wait(); // 不满足条件则等待
                        }
                        if (count <= MAX) {
                            System.out.println("线程" + index + "执行" + count);
                        }
                        count ++;
                        LOCK.notifyAll(); // 唤醒其他线程
                        System.out.println("线程" + index + "释放锁");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }*/
    // demo3 各线程按顺序打印（ReentrantLock 性能更好 精确控制线程）
/*    private static final int WORRER_COUNT = 3; // 固定三个线程数量
    private static int countIndex = 0; // 当前要打印的数字
    private static final ReentrantLock LOCK = new ReentrantLock(); // 全局锁 为保证打印数字的修改与判断的原子性
    public static void main(String[] args) throws InterruptedException {
        final List<Condition> conditions = new ArrayList<>(); // 线程条件变量
        final List<Thread> workers = new ArrayList<>(); // 线程
        for (int i = 0; i < WORRER_COUNT; i++) {
            // 为每一个线程分配一个 condition
            Condition condition = LOCK.newCondition();
            conditions.add(condition);
            Worker worker = new Worker(i, conditions);
            workers.add(worker);
            worker.start();
        }
        // 主线程 等待所有工作线程结束
        for (Thread worker : workers) {
            worker.join();
        }
        System.out.println("任务完毕");
    }

    public static class Worker extends Thread {
        int index;
        List<Condition> conditions;

        public Worker(int index, List<Condition> conditions) {
            super("Thread" + index);
            this.index = index;
            this.conditions = conditions;
        }
        // 按顺序，控制下一个线程
        private void signalNext(){
            // 下一个线程
            // 0 -> (0 + 1) % 3 = 1
            // 1 -> (1 + 1) % 3 = 2
            // 2 -> (2 + 1) % 3 = 0
            int nextIndex = (index + 1) % conditions.size();
            conditions.get(nextIndex).signal(); // 精准唤醒
        }

        public void run() {
            while(true) {
                try {
                    // 锁住 保证操作期间，只有一个线程
                    LOCK.lock();
                    System.out.println("线程 " + index + "获取锁，开始操作");
                    if (countIndex % 3 != index) { // 要打印的数字 要 对应好 线程
                        // 0 % 3 == 0, 3 % 3 == 0
                        // 1 % 3 == 1, 4 % 3 == 1
                        // 2 % 3 == 2, 5 % 3 == 2
                        System.out.println("线程 " + index + "不符合顺序，释放锁并等待");
                        conditions.get(index).await(); // 不满足条件的线程会释放锁并等待
                    }
                    if (countIndex > 20) { // 如果循环次数大于100，则退出循环
                        // 唤醒下一个线程，保证程序正常退出
                        signalNext();
                        System.out.println("线程 " + index + "结束" + "， 唤醒下一个线程");
                        // 退出循环 线程运行结束
                        return;
                    }
                    System.out.println("线程 " + index + " " + countIndex);
                    // 计数器 + 1
                    countIndex ++;
                    // 通知下一个线程干活
                    signalNext();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.println("操作结束 " + "线程 " + index + "释放锁");
                    LOCK.unlock(); // 释放锁
                }
            }
        }
    }*/
    // demo4 各线程按顺序打印 1-100 (ABC)（yield 自旋 + volatile）
    private static volatile int count = 0; // volatile 确保可见性与有序性
    private static final int MAX = 20;

    public static void main(String[] args) {
        for (int i = 0; i < 3; i ++) {
            new Thread(new OtherWorker(i)).start();
        }
    }
    public static class OtherWorker implements Runnable {
        private final int index;
        public OtherWorker(int index) {
            this.index = index;
        }
        @Override
        public void run() {
            while (count < MAX) {
                while(count % 3 != index) {
                    // 不符合条件的线程会让出cpu，开始自旋（仍处于运行状态，不会阻塞）
                    Thread.yield();
                }
                if (count > MAX) {
                    return;
                }
                char ascii = (char) ('A' + count % 3);
//                System.out.println("Thread " + index + " " + count);
                System.out.println("Thread " + index + " " + ascii);
                count ++;
            }
        }
    }
}
