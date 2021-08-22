package com.jd.blocking;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo1 implements Runnable{

    // 控制线程的执行
    static CountDownLatch downLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            new Thread(new CountDownLatchDemo1()).start();
        }

        // 条件达到了这放行
        downLatch.countDown();
    }


    @Override
    public void run() {
        try {
            // 控制线程不执行
            downLatch.await();
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
