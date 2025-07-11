package org.example.Concurrent.CompletableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Demo1 {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 2, 3);

        List<CompletableFuture<Integer>> futures = nums.stream()
                .map(value -> CompletableFuture.supplyAsync(() -> {
                    // 每个异步任务要执行的操作
                    return value;
                })).collect(Collectors.toList());

        CompletableFuture<Integer> sumFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApplyAsync(v -> {
                    // 所有异步计算任务完成后，将它们的结果进行合并
                    int sum = futures.stream()
                            .mapToInt(CompletableFuture::join)
                            .sum();
                    return sum;
                });

        int sum = sumFuture.join();
        System.out.println(sum);
    }
}
