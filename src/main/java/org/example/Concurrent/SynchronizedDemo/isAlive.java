package org.example.Concurrent.SynchronizedDemo;

public class isAlive {
    public static void main(String[] args) throws InterruptedException {
        // demo1
/*        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("running...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println(thread.isAlive());
        thread.join();
        System.out.println(thread.isAlive());*/
        // demo2
        Thread thread = new Thread(() -> {
            System.out.println("t1 begin");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 end");
        });
        Thread thread2 = new Thread(() -> {
            synchronized (thread) {
                System.out.println("t2 begin");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("t2 end");
                System.out.println("t1 isAlive:" + thread.isAlive());
            }
        });
        thread.start();
        thread2.start();
    }
}
