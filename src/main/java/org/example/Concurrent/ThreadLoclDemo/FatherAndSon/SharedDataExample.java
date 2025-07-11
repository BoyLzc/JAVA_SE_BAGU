package org.example.Concurrent.ThreadLoclDemo.FatherAndSon;

import java.util.concurrent.ConcurrentHashMap;

public class SharedDataExample {
    public static void main(String[] args) throws InterruptedException {
        // 使用ConcurrentHashMap作为共享数据 避免多线程操作并发问题
        ConcurrentHashMap<String, String> sharedData = new ConcurrentHashMap<>();
        sharedData.put("key", "value");
        MyThread myThread = new MyThread(sharedData);
        myThread.start();
        Thread.sleep(2000);
        System.out.println("sharedData in main thread:" + sharedData.get("key"));
    }
    public static class MyThread extends Thread {
        ConcurrentHashMap<String, String> sharedData;
        public MyThread(ConcurrentHashMap<String, String> data) {
            this.sharedData = data;
        }
        public void run() {
            sharedData.put("key", "new value");
            System.out.println("sharedData in child thread:" + sharedData.get("key"));
        }
    }
}
