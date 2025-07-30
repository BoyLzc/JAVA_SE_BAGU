package org.example.StreamDemo;

import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        // 1. 创建 Stream
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 2. filter：过滤出偶数0.
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("偶数: " + evenNumbers);

        // 3. map：将每个数字平方
        List<Integer> squaredNumbers = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println("平方数: " + squaredNumbers);

        // 4. sorted：对流中的元素排序
        List<Integer> sortedNumbers = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("排序后的数字: " + sortedNumbers);

        // 5. distinct：去除重复元素
        List<Integer> uniqueNumbers = Arrays.asList(1, 2, 2, 3, 4, 4, 5).stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("去重后的数字: " + uniqueNumbers);

        // 6. limit：获取前3个元素
        List<Integer> limitedNumbers = numbers.stream()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("前3个元素: " + limitedNumbers);

        // 7. skip：跳过前5个元素
        List<Integer> skippedNumbers = numbers.stream()
                .skip(5)
                .collect(Collectors.toList());
        System.out.println("跳过前5个元素后的结果: " + skippedNumbers);

        // 8. flatMap：将多个列表合并为一个流
        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5)
        );
        List<Integer> flattenedList = listOfLists.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println("合并后的列表: " + flattenedList);

        // 9. reduce：求和
        Optional<Integer> sum = numbers.stream()
                .reduce(Integer::sum);
        System.out.println("所有数字的和: " + sum.orElse(0));

        // 10. collect：将流收集为集合
        Set<Integer> numberSet = Arrays.asList(1, 1, 2, 3, 3, 4).stream()
                .collect(Collectors.toSet());
        System.out.println("收集为Set: " + numberSet);

        // 11. anyMatch：检查是否有任意一个元素满足条件
        boolean hasEven = numbers.stream()
                .anyMatch(n -> n % 2 == 0);
        System.out.println("是否存在偶数: " + hasEven);

        // 12. allMatch：检查是否所有元素都满足条件
        boolean allPositive = numbers.stream()
                .allMatch(n -> n > 0);
        System.out.println("是否所有数字都大于0: " + allPositive);


        // 13. noneMatch：检查是否没有元素满足条件
        boolean noNegative = numbers.stream()
                .noneMatch(n -> n < 0);
        System.out.println("是否没有负数: " + noNegative);

        // 14. findFirst：获取第一个元素
        Optional<Integer> first = numbers.stream()
                .findFirst();
        System.out.println("第一个元素: " + first.orElse(-1));

        // 15. forEach：遍历流中的元素
        System.out.println("遍历流中的元素:");
        numbers.forEach(System.out::println);
    }
}
