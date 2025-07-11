package org.example.Concurrent.ThreadLoclDemo.FatherAndSon;

public class InheritableThreadLocalDemo {
    // 在子线程中基础父线程的值 此变量无法共享
    public static InheritableThreadLocal<Integer> sharedData = new InheritableThreadLocal<>();
    public static void main(String[] args) throws InterruptedException {
        sharedData.set(0);
        sharedData.set(sharedData.get() + 1);
        System.out.println("sharedData in main thread:" + sharedData.get());
        MyThread myThread = new MyThread();
        myThread.start();
        Thread.sleep(2000);
        System.out.println("sharedData in main thread:" + sharedData.get());
    }

    public static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("sharedData in child thread:" + sharedData.get());
            sharedData.set(sharedData.get() + 2);
            System.out.println("sharedData in child thread after inc:" + sharedData.get());
        }
    }
}
