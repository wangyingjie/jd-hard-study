package com.jd.msg;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        int maxSize = 5;

        Producer producer = new Producer(queue, maxSize, lock, condition);

        Consumer consumer = new Consumer(queue, maxSize, lock, condition);

        new Thread(producer, "producer===").start();

        new Thread(consumer, "consumer===").start();

    }

}
