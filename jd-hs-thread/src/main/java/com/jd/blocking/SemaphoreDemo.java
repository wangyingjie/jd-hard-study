package com.jd.blocking;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 10; i++) {
            new Car(semaphore, i).start();
        }
    }

    static class Car extends Thread {

        Semaphore semaphore;
        int num;

        public Car(Semaphore semaphore, int num) {
            this.semaphore = semaphore;
            this.num = num;
        }

        @Override
        public void run() {
            try {
                // 获取令牌
                semaphore.acquire();
                System.out.println("第 " + num + "线程占用一个令牌");
                Thread.sleep(5000);
                // 释放令牌
                semaphore.release();
                System.out.println("第 " + num + "线程释放一个令牌");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
