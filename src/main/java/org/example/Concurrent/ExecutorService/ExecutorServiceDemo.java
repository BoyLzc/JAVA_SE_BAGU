package org.example.Concurrent.ExecutorService;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 提交一个异步任务
        Future<Integer> future = executorService.submit(() -> {
            // 模拟异步操作
            Thread.sleep(1000);
            return 42;
        });
        Integer result = future.get();
        System.out.println("异步任务结果：" + result);

        executorService.shutdown();
    }
}
