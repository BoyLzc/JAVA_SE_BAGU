package org.example.Concurrent.ReentrantLockDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    // 公平锁: 每个线程按申请锁的顺序获取锁 吞吐量较低 但每个线程都能拿到
    private final Lock lock = new ReentrantLock(true);
    // 默认非公平锁: 每个线程不按照申请锁的顺序获取锁，而是直接尝试获取，获取不到则进入等待队列。
//    private final Lock lock = new ReentrantLock();
    private int count;
    // 本质上 count += n 非原子性
    // 1、读取count 2、计算count +n 3、写入count
    // 多线程并发时，这些步骤可能交叉执行，导致部分修改丢失
    public void add(int n) {
        lock.lock();
        try {
            count += n;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter sharedCounter = new Counter();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    System.out.println("Thread " + Thread.currentThread().getName() + "拿到锁");
                    sharedCounter.add(1); // 操作同一个 counter
                }
            });
            threads[i].start();
        }
        for(Thread thread : threads) {
            thread.join(); // 主线程在此等待每个子线程完成 确保线程同步
        }
        System.out.println("Final count: " + sharedCounter.count);
    }
}
