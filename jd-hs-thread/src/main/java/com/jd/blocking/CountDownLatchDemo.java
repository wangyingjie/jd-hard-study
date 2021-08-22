package com.jd.blocking;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch downLatch = new CountDownLatch(3);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "--->begin");
            downLatch.countDown();
            System.out.println(Thread.currentThread().getName() + "-->end");
        }, "t1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "--->begin");
            downLatch.countDown();
            System.out.println(Thread.currentThread().getName() + "-->end");
        }, "t2").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "--->begin");
            downLatch.countDown();
            System.out.println(Thread.currentThread().getName() + "-->end");
        }, "t3").start();

        downLatch.await();//阻塞main线程

        System.out.println("main is over");
    }

}
