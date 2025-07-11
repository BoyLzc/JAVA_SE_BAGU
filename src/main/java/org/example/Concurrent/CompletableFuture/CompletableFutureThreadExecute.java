package org.example.Concurrent.CompletableFuture;

import org.example.Concurrent.SynchronizedDemo.MyThread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureThreadExecute {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // demo1 异步按顺序执行任务
        // 写法1(代码比较冗余 每个任务后进行阻塞 虽然使用线程池 但实际仍是穿行执行)
/*        CompletableFuture<Void> future1 = CompletableFuture.
                runAsync(new MyThread("T1"));

        CompletableFuture<Void> future2 = CompletableFuture.
                runAsync(new MyThread("T2"));

        CompletableFuture<Void> future3 = CompletableFuture.
                runAsync(new MyThread("T3"));
        // 保证主线程等待所有异步任务完成 如果没有此操作，则主线程结束，jvm不会执行异步线程
        future1.join(); // 阻塞当前线程（主线程），直至异步任务T1完成
        future2.join();
        future3.join();*/

        // 写法2 链式调用写法 无中间阻塞 仅最终阻塞一次
/*        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(new MyThread("T1"))
                .thenRun(new MyThread("T2"))
                .thenRun(new MyThread("T3"));
        voidCompletableFuture.join(); // .get()也可以）唯一阻塞点，主线程等待整个任务链完成
        System.out.println("456");*/

        // demo2 并行
        // 并行执行T1和T2，两者都完成后执行T3
/*        CompletableFuture<Void> future1 = CompletableFuture.
                runAsync(new MyThread("T1"));

        CompletableFuture<Void> future2 = CompletableFuture.
                runAsync(new MyThread("T2"));

        CompletableFuture<Void> future3 = CompletableFuture.
                runAsync(new MyThread("T3"));

        // T1和T2并行执行
        // 两者都完成后才执行T3
        // 最终等待整个流程完成
        // 写法1
        future1.thenCombine(future2, (unused1, unused2) -> null)
                .thenRun(() -> future3.join())
                .join(); // 等待整个流程完成
        // 写法2
        CompletableFuture.allOf(
                future1,
                future2
        ).thenCompose(unused -> future3).join();*/

        // demo3 异步任务
/*        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务开始执行");
            return 42;
        });
        CompletableFuture<String> resultFuture = future.thenApply
        (result -> {
            System.out.println("异步任务结果：" + result);
            return "结果是:" + result;
        });
        System.out.println(resultFuture.get());*/

        // demo4 合并异步任务结果
/*        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 20);
        CompletableFuture<Integer> future2 = future.thenCombine(future1, (a, b) -> a * b);
        System.out.println("合并结果:" + future2.get());*/

        // demo5 异常处理
/*        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            if (true) {
                throw new RuntimeException("异常");
            }
            return 42;
        });
        future.exceptionally(ex -> {
            System.out.println("异常处理:" + ex.getMessage());
            return 10;
        }).thenAccept(result -> {
            System.out.println("结果:" + result);
        });*/

        // demo6 链式操作 避免回调地狱
/*        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 10;
        }).thenApplyAsync(result -> {
            return result * 2;
        }).thenApplyAsync(result -> {
            return result * 3;
        });
        System.out.println("最终结果：" + future.get());*/

        // demo7 handle()来处理结果和异常
/*        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> 10);
        // 使用 handle() 处理结果和异常
        future.handle((result, ex) -> {
            if (ex != null) {
                System.out.println("异常处理:" + ex.getMessage());
                return 0;
            }
            return result * 2;
        }).thenAccept(result -> System.out.println("结果:" + result));*/

        // demo8
/*        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 20);
        // 等待多个任务完成
        CompletableFuture<Void> future3 = CompletableFuture.allOf(future1, future2);
        future3.thenRun(() -> {
            System.out.println("所有任务完成");
            try {
                System.out.println("任务1的结果为：" + future1.get());
                System.out.println("任务2的结果为：" + future2.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).join();*/

        // demo9 使用 ExecutorService 配合 CompletableFuture 进行并发执行
/*        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            return 5;
        }, executorService);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            return 10;
        }, executorService);
        CompletableFuture<Integer> future = future1.thenCombine
                (future2, (a, b) -> a + b);
        System.out.println("结果:" + future.get());
        executorService.shutdown();*/
    }
    public static class MyThread implements Runnable {
        private String name;

        public MyThread(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + "线程:" + name + "执行完毕");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
