package org.example.Concurrent.ThreadLoclDemo;

public class ThreadLoclDemo {
    // 1. 定义ThreadLocal变量（每个线程独立存储账户余额）
    private static final ThreadLocal<Integer> accountBalance = ThreadLocal.withInitial(() -> 1000);
    public static void main(String[] args) {
        // 2. 模拟两个用户同时操作自己的账户
        new Thread(() -> {
            accountBalance.set(accountBalance.get() + 500); // 线程A存款500
            System.out.println(Thread.currentThread().getName() + "账户余额: " + accountBalance.get());
            accountBalance.remove(); // 使用后清理防止内存泄漏
        }, "用户A").start();

        new Thread(() -> {
            accountBalance.set(accountBalance.get() - 300); // 线程B取款300
            System.out.println(Thread.currentThread().getName() + "账户余额: " + accountBalance.get());
            accountBalance.remove();
        }, "用户B").start();
    }
}
