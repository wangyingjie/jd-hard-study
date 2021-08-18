package com.jd.msg;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author wangyingjie5
 * @date 2021/8/18 11:20
 */
@Data
@AllArgsConstructor
public class Consumer implements Runnable {


    private Queue<String> queueMsg;
    private int maxSize;
    private Lock lock;
    private Condition condition;

    @Override
    public void run() {
        int i = 0;
        while (true) {
            i++;
            lock.lock(); // synchronized
            try {
                while (queueMsg.isEmpty()) {
                    System.out.println("消费队列已空，先等待!");
                    // 阻塞线程并释放锁  await
                    condition.await(); // wait
                }

                System.out.println("消费消息：" + queueMsg.remove());

                // 唤醒阻塞状态下的线程 signal
                condition.signal();// notify or notifyAll
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
