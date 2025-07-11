package org.example.Concurrent.ThreadLoclDemo.FatherAndSon;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

// 阿里的开源api 主要用于父进程与子进程间的参数传递
public class TransmittableThreadLocalDemo {
    // 1. 继承式线程变量（父线程→子线程自动传递）
    private static final InheritableThreadLocal<String> inheritableParam = new InheritableThreadLocal<>();

    // 2. 对比：普通线程变量（无法传递）
    private static final ThreadLocal<String> normalParam = new ThreadLocal<>();

    // 3. 父子线程传递
    // 1. 创建TransmittableThreadLocal（替代InheritableThreadLocal）
    private static final TransmittableThreadLocal<String> transmittableParam = new TransmittableThreadLocal<>();


    public static void main(String[] args) throws InterruptedException {
        // 父线程设置参数
        inheritableParam.set("父线程参数-可继承");
        normalParam.set("父线程参数-不可继承");

        // 场景1：直接创建子线程（自动继承）
/*        new Thread(() -> {
            // 子线程继承父线程的初始值，但是仍可以对其进行修改，子线程可以修改自己的副本而不影响父线程
            System.out.println("子线程获取inheritableParam(修改之前): " + inheritableParam.get());
            inheritableParam.set("父线程参数-可继承2");
            // inheritableThreadLocal 可以继承，每个线程单独存储
            System.out.println("子线程获取inheritableParam(修改之后): " + inheritableParam.get());
            // ThreadLocal 不会继承，每个线程单独存储
            System.out.println("子线程获取normalParam: " + normalParam.get());
        }).start();
        System.out.println("主线程获取inheritableParam(子线程进行修改之后): " + inheritableParam.get());
        System.out.println("主线程获取normalParam: " + normalParam.get());*/

        // 场景2：
        // 1. 初始化线程池（使用 TTL 包装）
        ExecutorService ttlExecutor = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(1));
        ExecutorService normalExecutor = Executors.newFixedThreadPool(1);
        // 此处设置线程池中线程数量为1，是为了测试 inheritable与transmittable的特性
        // inheritable 会在创建线程时，复制主线程的参数，然后不会再改变
        // transmittable 会在创建线程时，复制主线程的参数，然后会随着父线程的改变而改变(动态更新)
        // 2. 设置初始值
        inheritableParam.set("初始值-Inheritable");
        transmittableParam.set("初始值-Transmittable");

        // 3. 提交第一个任务
        System.out.println("=== 第一次提交任务 ===");
        submitTasks(normalExecutor, ttlExecutor);

        // 4. 修改值
        Thread.sleep(1000);
        inheritableParam.set("修改后值-Inheritable");
        transmittableParam.set("修改后值-Transmittable");

        // 5. 提交第二次任务 transmittableThreadLocal 会在每次提交任务时 捕获更新的值
        System.out.println("\n=== 第二次提交任务（父线程值已修改） ===");
        submitTasks(normalExecutor, ttlExecutor);

        // 6. 关闭线程池
        normalExecutor.shutdown();
        ttlExecutor.shutdown();
        // demo3
/*        AtomicReference<String> sharedData = new AtomicReference<>();
        AtomicReference<String> sharedData2 = new AtomicReference<>();
        inheritableParam.set("初始值-Inheritable");
        transmittableParam.set("初始值-Transmittable");
        Thread thread = new Thread(() -> {
            System.out.println("子线程获取inheritableParam(修改之前): " + inheritableParam.get());
            System.out.println("子线程获取transmittableParam(修改之前): " + transmittableParam.get());
            sharedData.set(inheritableParam.get());
            sharedData2.set(transmittableParam.get());
        });
        thread.start();
        thread.join();
        inheritableParam.set("修改后值-Inheritable");
        transmittableParam.set("修改后值-Transmittable");
        System.out.println("父线程修改后，子线程的值为：");
        System.out.println(sharedData.get());
        System.out.println(sharedData2.get());*/
    }

    private static void submitTasks(ExecutorService normalPool, ExecutorService ttlPool) {
        // 使用 InheritableThreadLocal 的线程池任务
        normalPool.submit(() -> {
            System.out.println("[Inheritable] 当前线程值: " + inheritableParam.get() + " - 来自线程: " + Thread.currentThread().getName());
        });

        ttlPool.submit(() -> {
            System.out.println("[Transmittable] 当前线程值: " + transmittableParam.get() + " - 来自线程: " + Thread.currentThread().getName());
        });
    }
}
