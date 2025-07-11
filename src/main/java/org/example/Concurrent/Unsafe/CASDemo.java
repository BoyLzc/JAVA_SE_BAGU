// 使用Unsafe类实现CAS(Compare-And-Swap)操作的计数器示例
package org.example.Concurrent.Unsafe;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class CASDemo {
    // Unsafe实例，用于执行底层CAS操作
    private static Unsafe unsafe;

    // 静态初始化块：通过反射获取Unsafe实例
    static {
        try {
            // 1. 获取Unsafe类的theUnsafe字段（单例实例）
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            // 2. 设置可访问（因为theUnsafe是private的）
            field.setAccessible(true);
            // 3. 获取Unsafe实例（静态字段所以传null）
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 计数器类
    private static class Counter {
        // volatile保证可见性（但不保证原子性）
        private volatile int value;

        public Counter(int initialValue) {
            this.value = initialValue;
        }

        // 使用CAS实现线程安全的递增操作
        public void increment() {
            int current;  // 当前值
            int next;     // 新值
            do {
                // 1. 读取当前值
                current = value;
                // 2. 计算新值
                next = current + 1;
                // 3. CAS更新：比较并交换（当前值==内存值时才更新）
                //    如果失败则循环重试
            } while (!unsafe.compareAndSwapInt(this, valueOffset, current, next));
        }
    }

    // value字段在Counter对象中的内存偏移量（CAS操作需要）
    private static final long valueOffset;

    // 静态初始化块：计算字段偏移量
    static {
        try {
            // 获取value字段在Counter类中的内存偏移地址
            valueOffset = unsafe.objectFieldOffset(Counter.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 初始化计数器
        Counter counter = new Counter(0);

        // 创建10个线程并发递增计数器
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                // 每个线程执行1000次递增
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }

        // 等待所有线程完成
        for(Thread thread : threads) {
            thread.join(); // 阻止当前线程（等待所有子线程完成）
        }

        // 输出最终结果（正确值应为10000）
        System.out.println("Final value: " + counter.value);
    }
}
