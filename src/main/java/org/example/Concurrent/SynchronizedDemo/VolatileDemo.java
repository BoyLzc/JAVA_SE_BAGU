package org.example.Concurrent.SynchronizedDemo;

public class VolatileDemo {
    // volatile 关键字，无法保证原子性
    public volatile int number = 0;
    // 并发场景下，++必须要保证原子性 使用 synchronized 类锁
    public synchronized void add(){
        number++;
    }
    // 使用 volatile 保证变量的可见性
    public volatile boolean flag = false;

    public void changeFlag() {
        flag = true;
        System.out.println("flag 已被修改为 true");
    }

    public volatile int starValue = 10;
    public static void main(String[] args) throws InterruptedException {
        // demo1 volatile无法保证原子性操作
/*        VolatileDemo volatileDemo = new VolatileDemo();
        // 创建十个线程
        for(int j = 0; j < 10; j ++) {
            Thread thread = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    volatileDemo.add();
                }
            });
            thread.start();
            thread.join();
        }
        // 确保所有子线程执行完毕再输出最终结果
        // activeCount() 返回当前线程组的线程数
        // 仍有子线程运行时，自旋主线程，让出cpu时间片
*//*        while(Thread.activeCount() > 2) {
            Thread.yield(); // 自旋当前正在执行线程（主线程），以允许其他线程获得执行机会，以此提高并发竞争
        }*//*
        System.out.println(Thread.currentThread().getName() + "final number result = " + volatileDemo.number);*/

        // demo2 volatile关键字修饰的字段，在多线程环境下的可见性
/*        VolatileDemo example = new VolatileDemo();
        // 线程1：等待 flag 变为 true
        new Thread(() -> {
            while (example.flag == false) {
                // 等待 flag 被其他线程修改
            }
            System.out.println(Thread.currentThread().getName() + ": 检测到 flag 变为 true，退出循环");
        }).start();

        // 确保主线程执行 changeFlag() 前，等待线程先启动
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 主线程修改 flag 的值
        example.changeFlag();*/

        // demo3
        VolatileDemo volatileDemo = new VolatileDemo();
        new Thread(() -> {
            while(volatileDemo.starValue < 20){
                System.out.println(Thread.currentThread().getName() + ": starValue = " + volatileDemo.starValue);
                // 循环等待
                volatileDemo.starValue ++;
            }
        }).start();
        while(volatileDemo.starValue < 20){
            // 循环等待
            System.out.println(Thread.currentThread().getName() + ": starValue = " + volatileDemo.starValue);
        }
    }
}
