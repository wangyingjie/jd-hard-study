package com.jd.blocking;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {

    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);

        try {
            for (int i = 0; i < 10; i++) {
                // 阻塞方法
                queue.put("put" + i);

                String take = queue.take();
                System.out.println(take);
                // 非阻塞方法，如果队列满了则直接抛异常
                // queue.add("add");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
