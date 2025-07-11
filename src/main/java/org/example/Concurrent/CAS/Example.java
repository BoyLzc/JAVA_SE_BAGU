package org.example.Concurrent.CAS;

import java.util.concurrent.atomic.AtomicStampedReference;

public class Example {
    public static void main(String[] args) throws InterruptedException {
        String initialRef = "lzc123";
        int initialStamp = 0;
        // k 为数据值 v 为时间戳（版本号）
        AtomicStampedReference<String> reference = new AtomicStampedReference<>
                (initialRef, initialStamp);
        // 子线程也更改
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            String newRef = "lzc789";
            int newStamp = 2;
            // 子线程先修改
            reference.compareAndSet
                    (initialRef, newRef, initialStamp, newStamp);
        }).start();

//        Thread.sleep(2000);
        System.out.println("Initial Ref:" + reference.getReference());
        System.out.println("Initial Stamp:" + reference.getStamp());
        Thread.sleep(3000);
        String newRef = "lzc456";
        int newStamp = 1;
        // 两次打印结果不一致，因为线程2已经将其值与版本号进行更改
        System.out.println("Initial Ref:" + reference.getReference());
        System.out.println("Initial Stamp:" + reference.getStamp());
        // 初始值与版本号（期望值）与 reference 存储的数据不一致，所以主线程不发生更改
        System.out.println(initialRef);
        System.out.println(initialStamp);
        // 原始数据值 新数据值 原始版本号 新版本号
        boolean updated = reference.compareAndSet
                (initialRef, newRef, initialStamp, newStamp);
        System.out.println("Updated:" + updated);
        System.out.println("Current Ref:" + reference.getReference());
        System.out.println("Current Stamp:" + reference.getStamp());
    }
}
