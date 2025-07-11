package org.example.Concurrent.ThreadLoclDemo.FatherAndSon;

public class Main {
    public static ThreadLocal<Integer> sharedData = new ThreadLocal<>();
    public static void main(String[] args) throws InterruptedException {
        sharedData.set(0);
        MyThread myThread = new MyThread();
        myThread.start();
        sharedData.set(sharedData.get() + 1); // 主线程自增
        System.out.println("sharedData in main thread:" + sharedData.get());
        Thread.sleep(3000);
    }

    public static class MyThread extends Thread {
        // ThreadLocl 全局变量 线程隔离 主线程不会影响子线程 即， 会抛异常
        @Override
        public void run() {
            System.out.println("sharedData in child thread:" + sharedData.get());
            sharedData.set(sharedData.get() + 1);
            System.out.println("sharedData in child thread after inc:" + sharedData.get());
        }
    }
}
